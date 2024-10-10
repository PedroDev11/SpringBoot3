package com.luv2code.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        // Get the employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // Add to the spring model
        model.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee theEmployee = new Employee();

        model.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model model) {
        Employee theEmployee = employeeService.findById(theId);

        model.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/proccessDelete")
    public String deleteEmployee(@RequestParam("employeeId") int theId, Model model) {
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }

}
