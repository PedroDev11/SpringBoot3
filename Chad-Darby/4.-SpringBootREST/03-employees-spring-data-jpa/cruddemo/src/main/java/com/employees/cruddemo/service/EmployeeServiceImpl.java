package com.employees.cruddemo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employees.cruddemo.dao.EmployeeRepository;
import com.employees.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    /* Delegating the methods, because we got these methods for free since we extends of 
    JpaRepository with Spring Data JPA */
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        /* "Optional" is different pattern, so intead of having a rigth code to check for nulls,
        you can make use of "Optional" to see if a given value is present */
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        /* Check if the value is present, then return the employee found */
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // We didn't find the employee
            throw new RuntimeException("Employee with ID -> " + theId + " Not found");
        }
        return theEmployee;
    }

    /* Remove @Transactional annotation since JpaRepository provides this functionality, 
    remember @Transactional allows to perform data in DB */
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

}
