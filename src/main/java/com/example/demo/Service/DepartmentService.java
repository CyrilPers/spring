package com.example.demo.Service;

import com.example.demo.Repositories.DepartmentRepository;
import com.example.demo.Entities.City;
import com.example.demo.Entities.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    public List<City> getXBiggestCities(int nbCities, int idDepartement) {
        return repo.getXBiggestCities(nbCities, idDepartement);
    }

    public Departement addDepartment(Departement departement) {
        return repo.save(departement);
    }

    public List<Departement> deleteDepartment(int idDepartment)  {
        repo.deleteById(idDepartment);
        return repo.findBy();
    }

    public Departement updateDepartment(Departement departement) throws Exception {
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
}
