package com.student.crud.crud.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.student.crud.crud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/* Repository annotation will give us support for component scanning and translate JDBC exceptions */
@Repository
public class StudentDAOImpl implements StudentDAO {
    /* Define field for entity manager */
    private EntityManager entityManager;

    /* Inject entity manager using constructor injection */
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /* Implement save method
    @Transactional annotation performa an update, saving an object in the DB -> will kinda handle
    the transaction management for us*/
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id); // Entity class
    }

    @Override
    public List<Student> findAll() {
        /* Create query -> Name of JPA Entity (Entity class), All JPQL syntax is based 
        on entity name and entity fields */
        TypedQuery<Student> theQuery = entityManager.createQuery(
            "FROM Student ORDER BY firstName", Student.class); 
        
        // Return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery(
            "FROM Student WHERE lastName =: theData ", Student.class);
        
        // Set query parameters
        theQuery.setParameter("theData", lastName);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent); // Update the entity
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // Retrieve the student
        Student myStudent = entityManager.find(Student.class, id);
        
        // Deleting the student
        entityManager.remove(myStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
