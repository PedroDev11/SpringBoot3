package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // Set up mapping to InstructorDetail entity
    // JoinColumn we'll map our column in the Instructor class and we'll have that map over to
    // the primary key in the instructor detail
    // instructor_detail_id defined in instructor table, in database, foreign key is configured
    // to reference id field in instructor_detail table
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    /* OneToOne -> fetchType: Eager by default
    OneToMany -> fetchType: Lazy by default
    ManyToOne -> fetchType: Eager by default
    ManyToMany -> fetchType: Lazy by default */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor() {

    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Add convenience methods for bidirectional relationship -> makes things a little easier for us
    // also help to set up that bidirectional relationship between instructor and courses
    public void add(Course tempCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        // Adding this course to the list of courses for this given instructor
        courses.add(tempCourse);

        // Set up thi bidirectional relationship -> Hey course, here's your new instructor
        tempCourse.setInstructor(this);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
