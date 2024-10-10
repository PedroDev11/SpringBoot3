package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Unidirectional
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        // This will also save the details object, 'cause of cascade type all.
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        // This will also retrieve the instructor details object, 'cause of default behavior of
        // @OneToOne fetch type is eager (Retrieve objects on cascade)
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor tempInstructor = findInstructorById(theId);

        // Get the courses
        List<Course> courses = tempInstructor.getCourses();

        // Break association of all courses for the instructor
        // Remove the instructor from the courses
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        entityManager.remove(tempInstructor);
    }

    // bidirectional
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail tempInstructorDetail = findInstructorDetailById(id);

        // Remove the associated object reference (just for avoid cascade remove)
        // Break bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );

        query.setParameter("data", id);

        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        /* Even with Instructor @OneToMany - FetchType.LAZY, the code (this one) will still retrieve
        * Instructor and courses, the JOIN FETCH is similar to EAGER loading.
        * So we'll get the Instructor and the actual courses by making use of this JOIN FETCH
        * THIS WILL JOIN ON COURSE TABLE */
        // "i" is a short name for instructor, so "i" basically means instructor here
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail "
                + "where i.id = :data", Instructor.class
        );
        query.setParameter("data", id);
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        Course tempCourse = entityManager.find(Course.class, id);
        System.out.println("Coursesssss: " + tempCourse);
        entityManager.remove(tempCourse);
    }
}
