package com.example.demo.Service;

import com.example.demo.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    public List<City> extractALl() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Montpellier", 200000));
        cities.add(new City("Paris", 100000));
        cities.add(new City("Lyon", 300000));
        cities.add(new City("Marseille", 400000));
        cities.add(new City("Nice", 500000));
        cities.add(new City("Toulouse", 600000));
        cities.add(new City("Bordeaux", 700000));
        cities.add(new City("Rennes", 800000));
        cities.add(new City("Nantes", 900000));
        return cities;
    }
}