package com.example.demo.Dao;

import com.example.demo.Entities.City;
import com.example.demo.Entities.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao {

    @Autowired
    EntityManager em;


    @Transactional
    public List<Department> getAllDepartments() throws Exception {
        try {
            TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public List<City> getXBiggestCities(int nbCities, int idDepartement) throws Exception {
        try {
            TypedQuery<City> query = em.createQuery(
                    "SELECT c FROM Department d JOIN City c ON c.department = d WHERE d.id = :idDepartement ORDER BY c.nbHabitants DESC ", City.class);
            query.setParameter("idDepartement", idDepartement);
            query.setMaxResults(nbCities);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public List<City> getCitiesBetween(int min, int max, int idDepartement) throws Exception {
        try {
            TypedQuery<City> query = em.createQuery(
                    "SELECT c FROM Department  d JOIN City c ON c.department = d WHERE d.id = :idDepartement AND c.nbHabitants BETWEEN :min AND :max", City.class);
            query.setParameter("idDepartement", idDepartement);
            query.setParameter("min", min);
            query.setParameter("max", max);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<Department> addDepartment(Department department) throws Exception {
        try {
        em.persist(department);
        return getAllDepartments();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Department findById(int idDepartement) throws Exception {
        try {
            Department department = em.find(Department.class, idDepartement);
            return department;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public List<Department> deleteDepartment(int idDepartment) throws Exception {
        try {
            Department departmentToDelete = findById(idDepartment);
            if (departmentToDelete == null) {
                throw new Exception("Department not found");
            }
            em.remove(departmentToDelete);
            return getAllDepartments();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<Department> updateDepartment(int id, Department department) throws Exception {
        try {
            Department departmentToUpdate = findById(id);
            if (departmentToUpdate == null) {
                throw new Exception("Department not found");
            }
            departmentToUpdate.setName(department.getName());
            departmentToUpdate.setCities(department.getCities());
            return getAllDepartments();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
