package com.rest.demo.rest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.demo.entity.Student;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    // Define a field, load the field with data, do it only once (ArrayList)
    List<Student> theStudents;

    // Define @PostContructor to load the student data... only once
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Pepe", "Rojas"));
        theStudents.add(new Student("Pedro", "Valladares"));
        theStudents.add(new Student("Alberto", "RV"));
    }

    // Define endpoint for /students retrieving a list of them
    @GetMapping("/students")
    public List<Student> getStudents() {
        // Creating list for every request, not good, new ArrayList<>(); insted of use @PostContructor
        return theStudents;
    }

    // Define endpoint for /students/{studentId}
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // Check the studentId again list size
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student ID not found -> " + studentId);            
        }

        // index into the list
        return theStudents.get(studentId);
    }
}
