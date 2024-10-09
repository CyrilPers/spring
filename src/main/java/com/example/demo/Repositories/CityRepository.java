package com.example.demo.Repositories;

import com.example.demo.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findBy();

    Optional<City> findByCityName(String cityName);

    Optional<City> findByCityId(Integer cityId);

    City save(City city);

    void deleteById(Integer id);

}
