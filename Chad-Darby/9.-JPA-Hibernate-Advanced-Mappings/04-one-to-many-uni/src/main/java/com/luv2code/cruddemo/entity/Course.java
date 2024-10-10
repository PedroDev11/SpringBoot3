package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    /* JoinColumn is the actual column in our course table that'll map or associate to the instructor table
     so we can find the given instructor for this given course. So it basically references the column that's
     in our course table that allow us to map back and find the associated instructor */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    public  Course() {

    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // Add a convenience method
    public void addReview(Review theReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(theReview);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}

/* JOIN TABLE -> a table that provides a mapping between two tables, It has foreign keys for each
* table to define the mapping relationship. This join table maintains the relationship between courses
* and students */
/* JOIN TABLE ANNOTATION -> tells hibernate witch join table that you'll use
* JOIN COLUMNS -> Refers to "course_id" column in "course_student" join table
* INVERSE JOIN COLUMNS -> Refers to "student_id" column in "course_student" join table to find the
* appropriate students for this course. In this context, we're defining the relationship in the Course
* class, The Student class is on the "other sie". So It's considered the "inverse". "Inverse" refers
* to the "other side" of the relationship.
* Hibernate use this information to find relationship between course and students */
