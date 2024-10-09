package com.example.demo.Service;

import com.example.demo.Dao.CityDao;
import com.example.demo.Entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityDao dao;

    public CityService() {
    }

    public List<City> extractCities() throws Exception {
        return dao.getAllCities();
    }

    public City extractCity(String name) throws Exception {
        return dao.getCityByName(name);
    }

    public City extractCity(int idCity) throws Exception {
        return dao.getCityById(idCity);
    }

    public List<City> insertCity(City city) throws Exception {
        return dao.insertCity(city);
    }

    public List<City> updateCity(int idCity, City city) throws Exception {
        return dao.modifyCity(idCity, city);
    }

    public List<City> deleteCity(int idVille) throws Exception {
        return dao.deleteCity(idVille);
    }

}