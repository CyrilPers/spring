package com.example.demo.service;

import com.example.demo.dto.DepartementFromApiDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.exceptions.FunctionalException;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.entities.City;
import com.example.demo.entities.Departement;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    private RestClient restClient;
    @Autowired
    private RestTemplate restTemplate;

    public List<City> getXBiggestCities(int nbCities, int idDepartement) {
        return repo.getXBiggestCities(nbCities, idDepartement);
    }

    public Departement addDepartment(Departement departement) throws FunctionalException {
        checkDepartment(departement);
        return repo.save(departement);
    }

    private void checkDepartment(Departement departement) throws FunctionalException {
        if (departement.getCode().length() > 1 || departement.getName().length() < 4)
            throw new FunctionalException("Le code département fait au maximum 3 caractères et au minimum 2");
        if (departement.getName().length() < 3)
            throw new FunctionalException("Le nom du département doit contenir au moins 3 lettres");
        Departement dpt = repo.findByCode(departement.getCode());
        if (dpt != null)
            throw new FunctionalException("Le code département doit être unique");
    }

    public List<Departement> deleteDepartment(int idDepartment)  {
        repo.deleteById(idDepartment);
        return repo.findBy();
    }

    public Departement updateDepartment(Departement departement) throws Exception {
        checkDepartment(departement);
        Departement departementFromDb = repo.findById(departement.getId()).orElse(null);
        if (departementFromDb != null) {
            departementFromDb.setName(departement.getName());
            departementFromDb.setCode(departement.getCode());
            departementFromDb.setCities(departement.getCities());
            return repo.save(departementFromDb);
        } else {
            throw new Exception("Departement inconnu");
        }
    }

    public List<Departement> getAllDepartments()  {
        return repo.findBy();
    }

    public List<Departement> getDepartementByName(String cityName) {
        return repo.findByName(cityName);
    }

    public Optional<Departement> getDepartementById(int idDepartement) {
        return repo.findById(idDepartement);
    }

    public String getDepartementName (String codeDepartement) {

        String url = "https://geo.api.gouv.fr/departements/" + codeDepartement;
        ResponseEntity<DepartementFromApiDto> departement = restTemplate.getForEntity(url, DepartementFromApiDto.class);
        DepartementFromApiDto departementFromApiDto = departement.getBody();
        return departementFromApiDto.getNom();
    }
}
