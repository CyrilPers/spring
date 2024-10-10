package com.example.demo.RestControler;

import com.example.demo.Dto.CityDto;
import com.example.demo.Dto.CityMapper;
import com.example.demo.Entities.City;
import com.example.demo.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/city")
public class CityControler {

    @Autowired
    private CityService citySvc;

    @Autowired
    private CityMapper cityMapper;

    @GetMapping("/all")
    public  Page<City> getCities(@RequestParam int page, @RequestParam int size)  {
        return citySvc.extractCities(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CityDto>> getCity(@PathVariable int id)  {
        Optional<City> city = citySvc.extractCity(id);
        if (city.isPresent()) {
            CityDto dto = cityMapper.toDto(city.get());
            return ResponseEntity.ok(Optional.ofNullable(dto));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cityName/{cityName}")
    public ResponseEntity<Optional<CityDto>>  getCity(@PathVariable String cityName)   {
        Optional<City> city = citySvc.extractCity(cityName);
        if (city.isPresent()) {
            CityDto dto = cityMapper.toDto(city.get());
            return ResponseEntity.ok(Optional.ofNullable(dto));

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<CityDto> addCity(@RequestBody City cityToAdd) {
       City city = citySvc.insertCity(cityToAdd);
       CityDto dto = cityMapper.toDto(city);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/update")
    public ResponseEntity<List<CityDto>> updateCity(@RequestBody City cityToUpdate) throws Exception {
        citySvc.updateCity(cityToUpdate);
        List<City> cities = citySvc.extractAllCities();
        List<CityDto> dtos = cities.stream().map(cityMapper::toDto).toList();
        return ResponseEntity.badRequest().body(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<CityDto>> deleteCity(@PathVariable int id) {
        citySvc.deleteCity(id);
        List<City> cities = citySvc.extractAllCities();
        List<CityDto> dtos = cities.stream().map(cityMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/startBy/{startBy}")
    public ResponseEntity<List<CityDto>> findCity(@PathVariable String startBy) {
        List<City> cities = citySvc.findCitiesStartBy(startBy);
        List<CityDto> dtos = cities.stream().map(cityMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/minHabitant/{min}")
    public ResponseEntity<List<CityDto>> findCity(@PathVariable int min) {
        List<City> cities = citySvc.findCitiesWithMinHabitants(min);
        List<CityDto> dtos = cities.stream().map(cityMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findMinMax")
    public ResponseEntity<List<CityDto>> findCityWithMinAndMaxHabitants(@RequestParam int min, @RequestParam int max) {
        List<City> cities = citySvc.findCitiesWithMinAndMaxHabitants(min, max);
        List<CityDto> dtos = cities.stream().map(cityMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findMinByDpt")
    public ResponseEntity<List<CityDto>> findCityWithMinHabitantsInDepartement(@RequestParam int min, @RequestParam int dpt) {
        List<City> cities = citySvc.findCitiesWithMinHabitantsAndDpt(min, dpt);
        List<CityDto> dtos = cities.stream().map(cityMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findMinMaxByDpt")
    public ResponseEntity<List<CityDto>> findCityWithMinAndMaxHabitantsInDepartement(@RequestParam int min, @RequestParam int max,  @RequestParam int idDpt) {
        List<City> cities = citySvc.findCitiesWithMinAndMaxHabitantsInDepartement(min, max, idDpt);
        List<CityDto> dtos = cities.stream().map(cityMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/pagination")
    public Page<CityDto> findCityWithMaxHabitantsMaxNbCities(@RequestParam int page, @RequestParam int size) {
        Page<City> cities = citySvc.findCitiesByPage(page, size);
        return cities.map(cityMapper::toDto);
    }
}
