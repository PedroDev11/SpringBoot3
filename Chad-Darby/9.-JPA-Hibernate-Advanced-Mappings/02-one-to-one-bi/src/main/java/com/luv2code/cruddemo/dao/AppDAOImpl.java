package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
