package com.bhirawa.demoproduct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProductApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {
			Student bhirawa = new Student(
				"Bhirawa", "Satrio", "bhir@yahoo.com", 29
			);
			Student satrio = new Student(
					"Satrio", "Nugroho", "satr@yahoo.com", 30
			);
			Student nugroho = new Student(
					"Nugroho", "Zakaria", "nugr@yahoo.com", 31
			);
			studentRepository.saveAll(List.of(bhirawa,satrio,nugroho));
			studentRepository
					.findStudentByEmail("bhir@yahoo.com")
					.ifPresentOrElse(System.out::println, ()-> System.out.println("Student with that email not found"));

			//find by first name and age with custom query
			studentRepository.selectStudentWhereFirstNameAndAgeGreaterOrEqual(
					"Bhirawa",
					28
			).forEach(System.out::println);

			//find by first name and age with native query
			studentRepository.selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
					"Satrio",
					29
			).forEach(System.out::println);

		//find student by ID
/*			System.out.println("Find by ID");
			studentRepository.findById(2L).
					ifPresentOrElse(System.out::println, () -> {
					System.out.println("Student with id 2 not found!");
			});

			//find all student
			System.out.println("Find all student");
			List <Student> students = studentRepository.findAll();
			students.forEach(System.out::println);*/

			//delete student
			/*studentRepository.deleteById(1L);
			System.out.println(studentRepository.count());*/

		};
	}
}
