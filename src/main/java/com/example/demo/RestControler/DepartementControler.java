package com.example.demo.RestControler;


import com.example.demo.Entities.City;
import com.example.demo.Entities.Department;
import com.example.demo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementControler {

    @Autowired
    DepartmentService departementSvc;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments()  {
        List<Department> departments = departementSvc.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Department>> add(@RequestBody Department department)  {
        departementSvc.addDepartment(department);
        List<Department> departments = departementSvc.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<List<Department>> delete(@PathVariable int id)  {
        List<Department> departments = departementSvc.deleteDepartment(id);
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> update(@RequestBody Department department)   {
        Department departement = departementSvc.updateDepartment(department);
        return ResponseEntity.ok(departement);
    }

    @GetMapping("/getBiggestCities")
    public ResponseEntity<List<City>> getBiggestCities(@RequestParam int nbCities, @RequestParam int idDepartement)  {
        List<City> cities = departementSvc.getXBiggestCities(nbCities, idDepartement);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/getCitiesBetween")
    public ResponseEntity<List<City>> getCitiesBetween(@RequestParam int min, @RequestParam int max, @RequestParam int idDepartement)  {
        List<City> cities = departementSvc.getCitiesBetween(min, max, idDepartement);
        return ResponseEntity.ok(cities);
    }

}
