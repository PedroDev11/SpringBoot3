package com.employees.cruddemo.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.employees.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOimpl implements EmployeeDAO {
    // Define fields for entityManager
    private EntityManager entityManager;

    // Set up contructor injection -> (theEntityManager) Automatically created by Spring Boot
    @Autowired
    public EmployeeDAOimpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        // Execute query and get results list
        List<Employee> employees = theQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // Get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        /* Save or update employee depending on the way this merge method works, it'll perform 
        a save or update, depending on the actual ID of the entity -> if the ID of the entity is
        equal to zero then it'll actually insert that given entity into the DB, else the ID is 
        not equal to zero then it'll simply perform an update */
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // Find by ID
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // Remove employee
        entityManager.remove(theEmployee);
    }
}
