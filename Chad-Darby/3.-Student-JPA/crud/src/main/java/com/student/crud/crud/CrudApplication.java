package com.student.crud.crud;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.student.crud.crud.dao.StudentDAO;
import java.util.List;
import com.student.crud.crud.entity.Student;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	/* Executed after the Spring Beans have been loaded 
	Then we inject the StudentDAO */
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		// Java lambda expression
		return runner -> {
			// createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			// readStudent(studentDAO, 1);
			// queryForStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		int rowsDeleted = studentDAO.deleteAll();
		System.out.println("Rows deleted: " + rowsDeleted);
	}

	/* private void deleteStudent(StudentDAO studentDAO) {
		System.out.println("Deleting student...");
		int studentID = 3;
		studentDAO.delete(studentID);
	} */

	/* private void updateStudent(StudentDAO studentDAO) {
		// Retrieve student base on the ID
		int studentID = 1;
		System.out.println("Retrieving student with ID: " + studentID);
		Student myStudent = studentDAO.findById(studentID);

		// Change lastname
		System.out.println("Updating the student...");
		myStudent.setLastName("Scooby");
		
		// Update the student 
		studentDAO.update(myStudent);

		// Display the updated student
		System.out.println("Updated student: " + myStudent);
	} */

	/* private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Rojas");

		// Display list of students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	} */

	/* private void queryForStudents(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// Display list of students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	} */

	/* private void readStudent(StudentDAO studentDAO, Integer theId) {	
		// Retrive student based on the id
		System.out.println("Retrieving student with ID: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// Display student
		System.out.println("Found the student: " + myStudent);
	} */

	/* private void createStudent(StudentDAO studentDAO) {
		// Create the student object 
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Pepe", "Rojas", "pepe@gmail.com");

		// Save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		System.out.println("Saved student. Generated ID: " + tempStudent.getId());
	} */

	private void createMultipleStudents(StudentDAO studentDAO) {

		// Create multiple students
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("Pedro", "Rojas", "pedro@gmail.com");
		Student tempStudent2 = new Student("Alberto", "Rojas", "alberto@gmail.com");
		Student tempStudent3 = new Student("Valladares", "Rojas", "valladares@gmail.com");

		// Save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	} 
}
