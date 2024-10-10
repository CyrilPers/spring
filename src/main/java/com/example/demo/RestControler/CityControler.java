package com.example.demo.RestControler;

import com.example.demo.Entities.City;
import com.example.demo.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityControler {

    @Autowired
    private CityService citySvc;

    @GetMapping("/all")
    public  Page<City> getCities(@RequestParam int page, @RequestParam int size)  {
        return citySvc.extractCities(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<City>> getCity(@PathVariable int id)  {
        Optional<City> city = citySvc.extractCity(id);
        if (city.isPresent()) {
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cityName/{cityName}")
    public Optional<City> getCity(@PathVariable String cityName)   {
        return citySvc.extractCity(cityName);
    }

    @PostMapping("/add")
    public ResponseEntity<City> addCity(@RequestBody City cityToAdd) {
       City city = citySvc.insertCity(cityToAdd);
        return ResponseEntity.ok(city);
    }


    @PutMapping("/update")
    public ResponseEntity<List<City>> updateCity(@RequestBody City cityToUpdate) throws Exception {
        citySvc.updateCity(cityToUpdate);
        List<City> cities = citySvc.extractAllCities();
        return ResponseEntity.badRequest().body(cities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<City>> deleteCity(@PathVariable int id) {
        citySvc.deleteCity(id);
        List<City> cities = citySvc.extractAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/startBy/{startBy}")
    public ResponseEntity<List<City>> findCity(@PathVariable String startBy) {
        List<City> cities = citySvc.findCitiesStartBy(startBy);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/minHabitant/{min}")
    public ResponseEntity<List<City>> findCity(@PathVariable int min) {
        List<City> cities = citySvc.findCitiesWithMinHabitants(min);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/findMinMax")
    public ResponseEntity<List<City>> findCityWithMinAndMaxHabitants(@RequestParam int min, @RequestParam int max) {
        List<City> cities = citySvc.findCitiesWithMinAndMaxHabitants(min, max);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/findMinByDpt")
    public ResponseEntity<List<City>> findCityWithMinHabitantsInDepartement(@RequestParam int min, @RequestParam int dpt) {
        List<City> cities = citySvc.findCitiesWithMinHabitantsAndDpt(min, dpt);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/findMinMaxByDpt")
    public ResponseEntity<List<City>> findCityWithMinAndMaxHabitantsInDepartement(@RequestParam int min, @RequestParam int max,  @RequestParam int idDpt) {
        List<City> cities = citySvc.findCitiesWithMinAndMaxHabitantsInDepartement(min, max, idDpt);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/pagination")
    public Page<City> findCityWithMaxHabitantsMaxNbCities(@RequestParam int page, @RequestParam int size) {
        return citySvc.findCitiesByPage(page, size);
    }

}
