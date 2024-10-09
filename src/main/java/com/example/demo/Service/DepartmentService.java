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
    private DepartmentRepository dao;

    public List<City> getXBiggestCities(int nbCities, int idDepartement) {
        return dao.getXBiggestCities(nbCities, idDepartement);
    }

    public List<City> getCitiesBetween(int min, int max, int idDepartement) {
        return dao.findByIdAndNbHabitantsBetween(idDepartement, min, max);
    }

    public Departement addDepartment(Departement departement) {
        return dao.save(departement);
    }

    public List<Departement> deleteDepartment(int idDepartment)  {
        dao.deleteById(idDepartment);
        return dao.findBy();
    }

    public Departement updateDepartment(Departement departement) throws Exception {
        Departement departementFromDb = dao.findById(departement.getId()).orElse(null);
        if (departementFromDb != null) {
            departementFromDb.setName(departement.getName());
            departementFromDb.setCode(departement.getCode());
            departementFromDb.setCities(departement.getCities());
            return dao.save(departementFromDb);
        } else {
            throw new Exception("Departement inconnu");
        }
    }

    public List<Departement> getAllDepartments()  {
        return dao.findBy();
    }

    public List<Departement> getDepartementByName(String cityName) {
        return dao.findByName(cityName);
    }

    public Optional<Departement> getDepartementById(int idDepartement) {
        return dao.findById(idDepartement);
    }
}
