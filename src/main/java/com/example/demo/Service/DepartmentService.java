package com.example.demo.Service;

import com.example.demo.Repositories.DepartmentRepository;
import com.example.demo.Entities.City;
import com.example.demo.Entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository dao;

    public List<City> getXBiggestCities(int nbCities, int idDepartement) {
        return dao.getXBiggestCities(nbCities, idDepartement);
    }

    public List<City> getCitiesBetween(int min, int max, int idDepartement) {
        return dao.findByDepartmentIdAndNbHabitantsBetween(idDepartement, min, max);
    }

    public Department addDepartment(Department department) {
        return dao.save(department);
    }

    public List<Department> deleteDepartment(int idDepartment)  {
        dao.deleteById(idDepartment);
        return dao.findBy();
    }

    public Department updateDepartment(Department department) {
        return dao.save(department);
    }

    public List<Department> getAllDepartments()  {
        return dao.findBy();
    }
}
