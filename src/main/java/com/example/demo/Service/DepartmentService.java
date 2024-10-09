package com.example.demo.Service;

import com.example.demo.Dao.DepartmentDao;
import com.example.demo.Entities.City;
import com.example.demo.Entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao dao;

    public List<City> getXBiggestCities(int nbCities, int idDepartement) {
        List<City> cities = dao.getXBiggestCities(nbCities, idDepartement);
        return cities;
    }

    public List<City> getCitiesBetween(int min, int max, int idDepartement) {
        List<City> cities = dao.getCitiesBetween(min, max, idDepartement);
        return cities;
    }

    public List<Department> addDepartment(Department department) {
        List<Department> departements = dao.addDepartment(department);
        return departements;
    }

    public List<Department> deleteDepartment(int idDepartment) {
        List<Department> departements = dao.deleteDepartment(idDepartment);
        return departements;
    }

    public List<Department> updateDepartment(int id, Department department) {
        List<Department> departements = dao.updateDepartment(id, department);
        return departements;
    }
}
