package com.example.demo.restControler;


import com.example.demo.entities.City;
import com.example.demo.entities.Departement;
import com.example.demo.exceptions.FunctionalException;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departement")
public class DepartementControler {

    @Autowired
    DepartmentService departementSvc;

    @GetMapping("/all")
    public ResponseEntity<List<Departement>> getAllDepartments() {
        List<Departement> departements = departementSvc.getAllDepartments();
        return ResponseEntity.ok(departements);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Departement>> add(@RequestBody Departement departement) throws FunctionalException {
        departementSvc.addDepartment(departement);
        List<Departement> departements = departementSvc.getAllDepartments();
        return ResponseEntity.ok(departements);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<List<Departement>> delete(@PathVariable int id) {
        List<Departement> departements = departementSvc.deleteDepartment(id);
        return ResponseEntity.ok(departements);
    }

    @PutMapping("/update")
    public ResponseEntity<Departement> update(@RequestBody Departement department) throws Exception {
        Departement departement = departementSvc.updateDepartment(department);
        return ResponseEntity.ok(departement);
    }

    @GetMapping("/getBiggestCities")
    public ResponseEntity<List<City>> getBiggestCities(@RequestParam int nbCities, @RequestParam int idDepartement) {
        List<City> cities = departementSvc.getXBiggestCities(nbCities, idDepartement);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/getCityByName")
    public ResponseEntity<List<Departement>> getDepartementByName(@RequestParam String departementName) {
        List<Departement> departements = departementSvc.getDepartementByName(departementName);
            return ResponseEntity.ok(departements);
    }

    @GetMapping("/getDepartementById")
    public ResponseEntity<Departement> getDepartementById(@RequestParam Integer idDepartementName) {
        Optional<Departement> departement = departementSvc.getDepartementById(idDepartementName);
        if (departement.isPresent()) {
            return ResponseEntity.ok(departement.get());
        }
        return ResponseEntity.notFound().build();
    }

}
