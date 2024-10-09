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

    public City updateCity(City city) throws Exception {
        Optional<City> cityFromDb = dao.findById(city.getId());
        if (cityFromDb.isPresent()) {
            cityFromDb.get().setName(city.getName());
            cityFromDb.get().setNbHabitants(city.getNbHabitants());
            return dao.save(cityFromDb.orElse(null));
        } else {
            throw new Exception("Ville inconnue");
        }
    }

    public void deleteCity(int idVille) {
        dao.deleteById(idVille);
    }

}