package com.example.demo.Service;

import com.example.demo.Dao.CityDao;
import com.example.demo.Entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityDao dao;

    public CityService() {
    }

    public List<City> extractCities() {
        return dao.getAllCities();
    }

    public City extractCity(String name) {
        return dao.getCityByName(name);
    }

    public City extractCity(int idCity) {
        return dao.getCityById(idCity);
    }

    public List<City> insertCity(City city) {
        return dao.insertCity(city);
    }

    public List<City> updateCity(int idCity, City city) {
        return dao.modifyCity(idCity, city);
    }

    public List<City> deleteCity(int idVille) {
        return dao.deleteCity(idVille);
    }

}