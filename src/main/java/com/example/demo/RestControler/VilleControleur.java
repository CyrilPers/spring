package com.example.demo.RestControler;

import com.example.demo.Service.VilleService;
import com.example.demo.Ville;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeSvc;

    @GetMapping
    public List<Ville> getVilles() {
        return villeSvc.extractALl();
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville villeToAdd, HttpServletRequest request) throws Exception {
        List<Ville> villes = villeSvc.extractALl();
        boolean exists = villes.stream().anyMatch(ville -> ville.getNom().equals(villeToAdd.getNom()));
        if (exists)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ville existe déjà");
        else
            villes.add(villeToAdd);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Ville insérée avec succès");
    }
}
