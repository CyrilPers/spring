package com.example.demo.Service;

import com.example.demo.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {
    public List<Ville> extractALl() {
        List<Ville> villes = new ArrayList<>();
        Ville ville1 = new Ville("Montpellier", 200000);
        Ville ville2 = new Ville("Paris", 100000);
        Ville ville3 = new Ville("Lyon", 300000);
        Ville ville4 = new Ville("Marseille", 400000);
        Ville ville5 = new Ville("Nice", 500000);
        Ville ville6 = new Ville("Toulouse", 600000);
        Ville ville7 = new Ville("Bordeaux", 700000);
        Ville ville8 = new Ville("Rennes", 800000);
        Ville ville9 = new Ville("Nantes", 900000);
        villes.addAll(List.of(ville1, ville2, ville3, ville4, ville5, ville6, ville7, ville8, ville9));
        return villes;
    }

}
