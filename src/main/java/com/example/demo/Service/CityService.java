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

    public List<City> extractCities() throws Exception {
        return dao.findBy();
    }

    public Optional<City> extractCity(String name) throws Exception {
        return dao.findByCityName(name);
    }

    public Optional<City> extractCity(int idCity) throws Exception {
        return dao.findByCityId(idCity);
    }

    public City insertCity(City city) throws Exception {
        return dao.save(city);
    }

    public City updateCity(City city) throws Exception {
       return dao.save(city);
    }

    public void deleteCity(int idVille) throws Exception {
        dao.deleteById(idVille);
    }

}