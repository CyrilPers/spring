package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ville")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nom")
    private String name;
    @Column(name = "nb_habs")
    private Integer nbHabitant;
    @ManyToOne
    @JoinColumn(name = "id_dept")
    private Departement departement;
    @ManyToOne
    @JoinColumn(name = "ID_REGION")
    private Region region;

    public City() {
    }
}
