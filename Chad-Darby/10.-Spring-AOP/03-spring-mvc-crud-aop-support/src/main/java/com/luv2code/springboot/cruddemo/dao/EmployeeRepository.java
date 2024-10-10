package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // Add a method to sort by lastname
    // Spring Data JPA will parse the method name, looks for a specific format and pattern
    // Creates appropriate query, behind the scenes
    public List<Employee> findAllByOrderByLastNameAsc();

}
