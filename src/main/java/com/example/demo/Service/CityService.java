package com.example.demo.Service;

import com.example.demo.Repositories.CityRepository;
import com.example.demo.Entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository dao;

    public CityService() {
    }

    public List<City> extractCities() {
        return dao.findBy();
    }

    public Optional<City> extractCity(String name) {
        return dao.findByName(name);
    }

    public Optional<City> extractCity(int idCity)  {
        return dao.findById(idCity);
    }

    public City insertCity(City city)  {
        return dao.save(city);
    }

    public City updateCity(City city)  {
       return dao.save(city);
    }

    public void deleteCity(int idVille) {
        dao.deleteById(idVille);
    }

}