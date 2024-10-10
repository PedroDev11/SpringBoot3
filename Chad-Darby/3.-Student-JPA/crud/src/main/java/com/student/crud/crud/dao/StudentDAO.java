package com.student.crud.crud.dao;
import java.util.List;

import com.student.crud.crud.entity.Student;

public interface StudentDAO {
    void save(Student theStudent); // Void -> returns nothing

    Student findById(Integer id); // Student -> returns an object

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}
