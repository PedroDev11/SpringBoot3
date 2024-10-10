package com.employees.cruddemo.service;

import java.util.List;

import com.employees.cruddemo.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee theEmployee);
    void deleteById(int theId);
}
