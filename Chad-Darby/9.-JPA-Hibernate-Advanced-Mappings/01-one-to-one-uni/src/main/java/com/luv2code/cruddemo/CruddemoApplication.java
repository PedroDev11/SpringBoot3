package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// CommandLineRunner executed after the spring beans have been loaded
	// Inject the AppDAO this method here is annotated with the Bean annotation, so Spring will inject
	// the AppDAO automatically, there's no need for auto wired
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
			// findInstructor(appDAO);
			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting the Instructor with the ID: " + theId);

		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor with the id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("The associate instructor only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// Create the instructor
		Instructor tempInstructor =
				new Instructor("Pepe", "Rojas", "pepe@gmail.com");

		// Create Instructor Detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://youtube.com", "video");

		// Associate the objects with setters
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor (cascade)
		System.out.println("Saving the instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
	}

}
