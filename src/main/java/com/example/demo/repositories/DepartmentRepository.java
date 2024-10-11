package com.example.demo.repositories;

import com.example.demo.entities.City;
import com.example.demo.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Departement, Integer> {

    List<Departement> findBy();

    List<Departement> findByName(String departementName);

    Optional<Departement> findById(Integer idDepartement);

    Departement save(Departement city);

    void deleteById(Integer id);

    @Query("SELECT c FROM Departement d JOIN City c ON c.departement = d WHERE d.id = :idDepartement ORDER BY c.nbHabitant DESC limit :nbCities")
    List<City> getXBiggestCities(int nbCities, int idDepartement);

    Departement findByCode(String code);
}
