package com.employees.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.employees.cruddemo.entity.Employee;

/* JpaRepository -> allow us these CRUD methods for free, no need for implementation class 
Thanks to Spring Data JPA */
/* @RepositoryRestResource annotation will use the path wrote instead of use the name of the entity
employees (Employee) */
@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
