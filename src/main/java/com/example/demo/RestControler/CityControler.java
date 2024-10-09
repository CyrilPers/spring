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
    public  ResponseEntity<List<City>> getCities() throws Exception {
        List<City> cities = citySvc.extractCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable int id) throws Exception {
        City city = citySvc.extractCity(id);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/{cityName}")
    public City getCity(@PathVariable String cityName) throws Exception  {
        return citySvc.extractCity(cityName);
    }

    @PostMapping("/add")
    public ResponseEntity<List<City>> addCity(@RequestBody City cityToAdd) throws Exception {
        List<City> cities = citySvc.insertCity(cityToAdd);
        return ResponseEntity.ok(cities);
    }


    @PutMapping("/{id}")
    public ResponseEntity<List<City>> updateCity(@PathVariable int id, @RequestBody City cityToUpdate) throws Exception {
        List<City> cities = citySvc.updateCity(id, cityToUpdate);
        return ResponseEntity.badRequest().body(cities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<City>> deleteCity(@PathVariable int id) throws Exception {
        List<City> cities = citySvc.deleteCity(id);
        return ResponseEntity.ok(cities);
    }
}
