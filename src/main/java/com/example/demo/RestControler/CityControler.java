package com.example.demo.RestControler;

import com.example.demo.Entities.City;
import com.example.demo.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public  ResponseEntity<List<City>> getCities()  {
        List<City> cities = citySvc.extractCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<City>> getCity(@PathVariable int id)  {
        Optional<City> city = citySvc.extractCity(id);
        if (city.isPresent()) {
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{cityName}")
    public Optional<City> getCity(@PathVariable String cityName)   {
        return citySvc.extractCity(cityName);
    }

    @PostMapping("/add")
    public ResponseEntity<List<City>> addCity(@RequestBody City cityToAdd) {
        citySvc.insertCity(cityToAdd);
        List<City> cities = citySvc.extractCities();
        return ResponseEntity.ok(cities);
    }


    @PutMapping("/update")
    public ResponseEntity<City> updateCity(@RequestBody City cityToUpdate) throws Exception {
        City city = citySvc.updateCity(cityToUpdate);
        return ResponseEntity.badRequest().body(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<City>> deleteCity(@PathVariable int id) {
        citySvc.deleteCity(id);
        List<City> cities = citySvc.extractCities();
        return ResponseEntity.ok(cities);
    }
}
