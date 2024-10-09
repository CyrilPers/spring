package com.example.demo.Repositories;

import com.example.demo.Entities.City;
import com.example.demo.Entities.Departement;
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

    @Query("SELECT c FROM Departement d JOIN City c ON c.departement = d WHERE d.id = :idDepartement ORDER BY c.nbHabitants DESC limit :nbCities")
    List<City> getXBiggestCities(int nbCities, int idDepartement);

    @Query("SELECT c FROM Departement d JOIN City c ON c.departement = d WHERE d.id = :idDepartement AND c.nbHabitants between :nbHabitantsMin and :nbHabitantsMax ORDER BY c.nbHabitants DESC")
    List<City> findByIdAndNbHabitantsBetween(Integer idDepartement, Integer nbHabitantsMin, Integer nbHabitantsMax);

}
