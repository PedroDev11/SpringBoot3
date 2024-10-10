package com.employees.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employees.cruddemo.entity.Employee;

/* JpaRepository -> allow us these CRUD methods for free, no need for implementation class 
Thanks to Spring Data JPA */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
