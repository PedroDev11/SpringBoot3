package com.mvc.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mvc.thymeleafdemo.model.Student;

@Controller
public class StudentController {
    // Inject countries from properties file
    @Value("${countries}")
    private List<String> countries;

    // Inject languages from properties file
    @Value("${languages}")
    private List<String> languages;

    // Inject systems from properties file
    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        // Create a student object
        Student theStudent = new Student();
        
        // Add student object to the model
        model.addAttribute("student", theStudent);
        
        // Add the list of countries to the model
        model.addAttribute("countries", countries);

        // Add the list of languages to the model
        model.addAttribute("languages", languages);

        // Add the list of systems to the model
        model.addAttribute("systems", systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {
        // Log the input data
        System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
        return "student-confirmation";
    }
}
