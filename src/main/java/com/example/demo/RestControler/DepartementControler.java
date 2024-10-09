package com.example.demo.RestControler;


import com.example.demo.Entities.City;
import com.example.demo.Entities.Department;
import com.example.demo.Service.DepartmentService;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementControler {

    @AutoConfigureOrder
    DepartmentService departementSvc;

    @PostMapping("/add")
    public ResponseEntity<List<Department>> add(@RequestBody Department department) {
        List<Department> departments = departementSvc.addDepartment(department);
        if (departments == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<List<Department>> delete(@PathVariable int id) {
        List<Department> departments = departementSvc.deleteDepartment(id);
        if (departments == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<List<Department>> update(@PathVariable int id, @RequestBody Department department) {
        List<Department> departments = departementSvc.updateDepartment(id, department);
        if (departments == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/getBiggestCities")
    public ResponseEntity<List<City>> getBiggestCities(@RequestParam int nbCities, @RequestParam int idDepartement) {
        List<City> cities = departementSvc.getXBiggestCities(nbCities, idDepartement);
        if (cities == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/getCitiesBetween")
    public ResponseEntity<List<City>> getCitiesBetween(@RequestParam int min, @RequestParam int max, @RequestParam int idDepartement) {
        List<City> cities = departementSvc.getCitiesBetween(min, max, idDepartement);
        if (cities == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(cities);
    }

}
