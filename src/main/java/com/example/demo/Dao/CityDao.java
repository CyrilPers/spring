package com.example.demo.Dao;

import com.example.demo.Entities.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<City> getAllCities() throws Exception {
        try {
            TypedQuery<City> query = em.createQuery("SELECT c FROM City c", City.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public City getCityById(int id) throws Exception {
        try {
        return em.find(City.class, id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public City getCityByName(String name) throws Exception {
        try {
        return em.find(City.class, name);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public List<City> modifyCity(int idCity, City city) throws Exception {
        try {
            City cityFromDb = em.find(City.class, idCity);
            if (cityFromDb != null) {
                cityFromDb.setName(city.getName());
                city.setNbHabitants(city.getNbHabitants());
                em.merge(cityFromDb);
                return getAllCities();

            }
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public List<City> deleteCity(int idVille) throws Exception {
        try {
            City cityToDelete = em.find(City.class, idVille);
            if (cityToDelete == null) {
                throw new Exception("City not found");
            }
            em.remove(cityToDelete);
            return getAllCities();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public List<City> insertCity(City city) throws Exception {
        try {
            em.persist(city);
            return getAllCities();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
