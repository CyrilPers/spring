package com.example.demo.Service;

import com.example.demo.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private List<City> cities =  new ArrayList<>();

    public CityService() {
        cities.add(new City(1, "Montpellier", 200000));
        cities.add(new City(2, "Paris", 100000));
        cities.add(new City(3, "Lyon", 300000));
        cities.add(new City(4, "Marseille", 400000));
        cities.add(new City(5, "Nice", 500000));
        cities.add(new City(6, "Toulouse", 600000));
        cities.add(new City(7,"Bordeaux", 700000));
        cities.add(new City(8,"Rennes", 800000));
        cities.add(new City(9,"Nantes", 900000));
    }

    public List<City> extractALl() {
        return cities;
    }

    public void add(City cityToAdd) {
        cities.add(cityToAdd);
    }
}