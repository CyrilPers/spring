package com.example.demo.Repositories;

import com.example.demo.Entities.City;
import com.example.demo.Entities.Department;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findBy();

    Optional<Department> findByName(String departementName);

    Optional<Department> findById(Integer idDepartement);

    Department save(Department city);

    void deleteById(Integer id);

    @Query("SELECT c FROM Department d JOIN City c ON c.department = d WHERE d.id = :idDepartement ORDER BY c.nbHabitants DESC")
    List<City> getXBiggestCities(int nbCities, int idDepartement);

    List<City> findByDepartmentIdAndNbHabitantsBetween(Integer idDepartement, Integer nbHabitantsMin, Integer nbHabitantsMax);


}
