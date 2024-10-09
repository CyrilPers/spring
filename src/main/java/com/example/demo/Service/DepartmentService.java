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

    public List<City> getXBiggestCities(int nbCities, int idDepartement) throws Exception {
        return dao.getXBiggestCities(nbCities, idDepartement);
    }

    public List<City> getCitiesBetween(int min, int max, int idDepartement) throws Exception {
        return dao.getCitiesBetween(min, max, idDepartement);
    }

    public List<Department> addDepartment(Department department) throws Exception {
        return dao.addDepartment(department);
    }

    public List<Department> deleteDepartment(int idDepartment) throws Exception {
        return dao.deleteDepartment(idDepartment);
    }

    public List<Department> updateDepartment(int id, Department department) throws Exception {
        return dao.updateDepartment(id, department);
    }

    public List<Department> getAllDepartments() throws Exception {
        return dao.getAllDepartments();
    }
}
