package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    // Unidirectional
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);

    // bidirectional
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void updateInstructor(Instructor tempInstructor);
    Course findCourseById(int id);
    void updateCourse(Course tempCourse);
    void deleteCourse(int id);

    // OneToMany Unidirectional
    void save(Course theCourse);
    Course findCourseAndReviews(int id);
}
