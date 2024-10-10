package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
			// createCourseAndReviews(appDAO);
			// retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("Removing the course and reviews with id: " + id);
		// This will delete the course and associated reviews
		appDAO.deleteCourse(id);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		// Remember that implementation makes use of JOIN FETCH on the background
		// so it'll actually retrieve the course and the reviews
		Course course = appDAO.findCourseAndReviews(id);
		System.out.println("Course: " + course);
		System.out.println("Reviews: " + course.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		System.out.println("Creating a new course");
		Course theCourse = new Course("Pacman");

		System.out.println("Creating a couple of reviews");
		theCourse.addReview(new Review("Great course"));
		theCourse.addReview(new Review("Course very awesome"));
		theCourse.addReview(new Review("Amazing course"));

		System.out.println("Saving the course...");
		System.out.println("Course and Reviews: " + theCourse);
		System.out.println("Reviews: " + theCourse.getReviews());
		appDAO.save(theCourse);
	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting the course with id: " + id);
		appDAO.deleteCourse(id);
	}

	private void updateCourseById(AppDAO appDAO) {
		int id = 10;
		System.out.println("Finding the Course with id: " + id);
		Course tempCourse = appDAO.findCourseById(id);

		System.out.println("Updating the Course");
		tempCourse.setTitle("Learning Spring Boot with Chat");
		appDAO.updateCourse(tempCourse);
		System.out.println("Done!");
	}

	private void updateInstructorById(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);
		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("Updating instructor");
		tempInstructor.setLastName("TESTER");
		appDAO.updateInstructor(tempInstructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding Instructor with id: " + id);
		// The code will still retrieve Instructor and courses, because we added JOIN FETCH in our query
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("TempInstructor: " + tempInstructor);
		System.out.println("Courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		Instructor tempInstructor = appDAO.findInstructorById(id);
		System.out.println("TempInstructor: " + tempInstructor);

		// Get the courses associated with the instructor id
		System.out.println("Finding courses for instructor with id: " + id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		// Associate courses to the Instructor, (Associate the objects)
		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);
		Instructor tempInstructor = appDAO.findInstructorById(id);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("Courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// Create the instructor
		Instructor tempInstructor =
				new Instructor("Pablo", "Valladares", "pablo@gmail.com");

		// Create Instructor Detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://youtube.com", "video");

		// Create Instructor Courses
		Course tempCourse = new Course("Python - The Ultimate course");
		Course tempCourse1 = new Course("Java - The Master Class");
		Course tempCourse2 = new Course("Java - Learn java from scratch");

		// Associate courses to Instructor
		tempInstructor.add(tempCourse);
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// Associate the objects with setters
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor (cascade)
		System.out.println("Saving the instructor with courses: " + tempInstructor);
		System.out.println("Courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}

	// bidirectional
	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Finding the Instructor Detail with ID: " + theId);

		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("Instructor Detail: " + tempInstructorDetail);
		System.out.println("Instructor: " + tempInstructorDetail.getInstructor());
	}

	// Unidirectional
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
				new Instructor("Pedro", "Rojas", "pedro@gmail.com");

		// Create Instructor Detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://youtube.com", "watch");

		// Associate the objects with setters
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor (cascade)
		System.out.println("Saving the instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
	}

}
