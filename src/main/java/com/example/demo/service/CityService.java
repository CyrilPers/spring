package com.example.demo.service;

import com.example.demo.exceptions.FunctionalException;
import com.example.demo.repositories.CityRepository;
import com.example.demo.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository repo;

    public CityService() {
    }

    public Page<City> extractCities(int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        return repo.findBy(pagination);
    }

    public Optional<City> extractCity(String name) {
        return repo.findByName(name);
    }

    public Optional<City> extractCity(int idCity)  {
        return repo.findById(idCity);
    }

    public City insertCity(City city) throws FunctionalException {
        checkCity(city);
        return repo.save(city);
    }

    private void checkCity(City city) throws FunctionalException {
        if (city.getNbHabitant() < 10)
            throw new FunctionalException("La ville doit avoir au moins 10 habitants");
        if (city.getName().length() < 3)
            throw new FunctionalException("Le nom de la ville doit contenir plus de 2 lettres");
        if (city.getDepartement().getCode().length() != 2)
            throw new FunctionalException("Le code du département doit être de 2 caractères");
        City existingCityInDpt = repo.findByNameAndDepartementId(city.getName(), city.getDepartement().getId());
        if (existingCityInDpt != null)
            throw new FunctionalException("Le nom de la ville doit être unique dans le département");
    }

    public City updateCity(City city) throws Exception {
        checkCity(city);
        Optional<City> cityFromDb = repo.findById(city.getId());
        if (cityFromDb.isPresent()) {
            cityFromDb.get().setName(city.getName());
            cityFromDb.get().setNbHabitant(city.getNbHabitant());
            return repo.save(cityFromDb.orElse(null));
        } else {
            throw new Exception("Ville inconnue");
        }
    }

    public void deleteCity(int idVille) {
        repo.deleteById(idVille);
    }

    public List<City> findCitiesStartBy(String startBy) {
        return repo.findByNameStartingWith(startBy);
    }

    public List<City> findCitiesWithMinHabitants(int min) {
        return repo.findBynbHabitantGreaterThanEqual(min);
    }

    public List<City> findCitiesWithMinAndMaxHabitants(int min, int max) {
        return repo.findByNbHabitantGreaterThanEqualAndNbHabitantLessThanEqual(min, max);
    }

    public List<City> findCitiesWithMinHabitantsAndDpt(int min, int dpt) {
        return repo.findBynbHabitantGreaterThanEqualAndDepartementId(min, dpt);
    }

    public List<City> findCitiesWithMinAndMaxHabitantsInDepartement(int min, int max, int idDepartement) {
        return repo.findByDepartementIdAndNbHabitantBetween(idDepartement, min, max);
    }


    public Page<City> findCitiesByPage(int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        return repo.findAllOrderByNbHabitantDesc(pagination);
    }

    public List<City> extractAllCities() {
        return repo.findBy();
    }
}