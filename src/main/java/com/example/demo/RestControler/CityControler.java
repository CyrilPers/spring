package com.example.demo.RestControler;

import com.example.demo.City;
import com.example.demo.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class CityControler {

    @Autowired
    private CityService villeSvc;

    @GetMapping
    public List<City> getVilles() {
        return villeSvc.extractALl();
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody City cityToAdd) {
        List<City> cities = villeSvc.extractALl();
        boolean exists = cities.stream().anyMatch(city -> city.getName().equals(cityToAdd.getName()));
        if (exists)
            return ResponseEntity.badRequest().body("La ville existe déjà");
        else
            cities.add(cityToAdd);
        return ResponseEntity.ok("City insérée avec succès");
    }
}
