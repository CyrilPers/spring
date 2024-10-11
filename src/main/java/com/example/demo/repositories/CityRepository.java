package com.example.demo.repositories;

import com.example.demo.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    Page<City> findBy(Pageable pagination);

    Optional<City> findByName(String cityName);

    Optional<City> findById(Integer cityId);

    City save(City city);

    void deleteById(Integer id);

    List<City> findByNameStartingWith(String startBy);

    List<City> findBynbHabitantGreaterThanEqual(int min);

    City findByNameAndDepartementId(String name, int idDepartement);

    List<City> findByNbHabitantGreaterThanEqualAndNbHabitantLessThanEqual(int min, int max);

    @Query("SELECT c FROM Departement d JOIN City c ON c.departement = d WHERE d.id = :idDepartement AND c.nbHabitant >= :min ORDER BY c.nbHabitant DESC")
    List<City> findBynbHabitantGreaterThanEqualAndDepartementId(int min, int idDepartement);

    @Query("SELECT c FROM Departement d JOIN City c ON c.departement = d WHERE d.id = :idDepartement AND c.nbHabitant between :nbHabitantsMin and :nbHabitantsMax ORDER BY c.nbHabitant DESC")
    List<City> findByDepartementIdAndNbHabitantBetween(Integer idDepartement, Integer nbHabitantsMin, Integer nbHabitantsMax);


    @Query("SELECT c FROM City c ORDER BY c.nbHabitant DESC")
    Page<City> findAllOrderByNbHabitantDesc(Pageable pagination);

    List<City> findBy();
}
