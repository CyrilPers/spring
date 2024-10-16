package com.example.demo.service;

import com.example.demo.exceptions.FunctionalException;
import com.example.demo.repositories.CityRepository;
import com.example.demo.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository repo;

    @Autowired
    DepartmentService departementSvc;

    public CityService() {
    }

    public Page<City> extractCities(int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        return repo.findBy(pagination);
    }

    public Optional<City> extractCity(String name) {
        return repo.findByName(name);
    }

    public Optional<City> extractCity(int idCity) {
        return repo.findById(idCity);
    }


    public List<City> insertCity(City city) throws FunctionalException {
        checkCity(city);
        repo.save(city);
        return repo.findBy();
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

    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteCity(int idVille) {
        Optional <City> cityFromDb = repo.findById(idVille);
        if (cityFromDb.isPresent()) {
            repo.deleteById(idVille);
            return true;
        }
        return false;
    }

    public List<City> findCitiesStartBy(String startBy) throws FunctionalException {
        List<City> cities = repo.findByNameStartingWith(startBy);
        if (cities.isEmpty())
            throw new FunctionalException("Aucune ville dont le nom commence par nom n’a été trouvée");
        return cities;
    }

    public List<City> findCitiesWithMinHabitants(int min) throws FunctionalException {
        List<City> cities = repo.findBynbHabitantGreaterThanEqual(min);
        if (cities.isEmpty())
            throw new FunctionalException("Aucune ville n’a une population supérieure" + min);
        return cities;
    }

    public List<City> findCitiesWithMinAndMaxHabitants(int min, int max) throws FunctionalException {
        List<City> cities = repo.findByNbHabitantGreaterThanEqualAndNbHabitantLessThanEqual(min, max);
        if (cities.isEmpty())
            throw new FunctionalException("Aucune ville n’a une population entre " + min + " et " + max);
        return cities;
    }

    public List<City> findCitiesWithMinHabitantsAndDpt(int min, int dpt) throws FunctionalException {
        List<City> cities = repo.findBynbHabitantGreaterThanEqualAndDepartementId(min, dpt);
        if (cities.isEmpty())
            throw new FunctionalException("Aucune ville n’a une population supérieure à " + min + " et appartient au département " + dpt);
        return cities;
    }

    public List<City> findCitiesWithMinAndMaxHabitantsInDepartement(int min, int max, int idDepartement) throws FunctionalException {
        List<City> cities = repo.findByDepartementIdAndNbHabitantBetween(idDepartement, min, max);
        if (cities.isEmpty())
            throw new FunctionalException("Aucune ville n’a une population entre " + min + " et " + max + " dans le departement département " + idDepartement);
        return cities;
    }


    public Page<City> findCitiesByPage(int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        return repo.findAllOrderByNbHabitantDesc(pagination);
    }

    public List<City> extractAllCities() {
        return repo.findBy();
    }

    public String getCsv(int minHabitants) {

        List<City> cities = repo.findBynbHabitantGreaterThanEqual(minHabitants);

        String CSV_HEADER = "cityName,nbHabitants,codeDepartement, nameDepartement";

        StringBuilder csvContent = new StringBuilder();
        csvContent.append(CSV_HEADER);

        for (City city : cities) {
            String departementName = departementSvc.getDepartementName(city.getDepartement().getCode());
            csvContent.append(city.getName()).append(",")
                    .append(city.getNbHabitant()).append(",")
                    .append(city.getDepartement().getCode()).append(",")
                    .append(departementName);
        }
        return csvContent.toString();
    }
}