package com.example.demo.RestControler;

import com.example.demo.Entities.City;
import com.example.demo.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityControler {

    @Autowired
    private CityService citySvc;

    @GetMapping("/all")
    public  ResponseEntity<List<City>> getCities() {
        List<City> cities = citySvc.extractCities();
        if (cities == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable int id) {
        City city = citySvc.extractCity(id);
        if (city == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(city);
    }

    @GetMapping("/{cityName}")
    public City getCity(@PathVariable String cityName) {
        return citySvc.extractCity(cityName);
    }

    @PostMapping("/add")
    public ResponseEntity<List<City>> addCity(@RequestBody City cityToAdd) {
        List<City> cities = citySvc.insertCity(cityToAdd);
        if (cities == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(cities);
    }


    @PutMapping("/{id}")
    public ResponseEntity<List<City>> updateCity(@PathVariable int id, @RequestBody City cityToUpdate) {
        List<City> cities = citySvc.updateCity(id, cityToUpdate);
        if (cities == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.badRequest().body(cities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<City>> deleteCity(@PathVariable int id) {
        List<City> cities = citySvc.deleteCity(id);
        if (cities == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(cities);
    }
}
