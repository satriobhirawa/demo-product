package com.bhirawa.demoproduct;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class DemoProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProductApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {

			generateRandomStudent(studentRepository);
			//Sort by firstname ASC
			//Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
			//OR
			Sort sort = Sort.by("firstName").ascending()
					.and(Sort.by("age").descending());
			studentRepository.findAll(sort).forEach(student -> System.out.println(student.getFirstName() + " " + student.getAge()));
		};
	}
	//use faker to populate the database
	private void generateRandomStudent(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i = 0; i < 20 ; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@yahoo.com", firstName, lastName);
			Student student = new Student(firstName,
					lastName,
					email,
					faker.number().numberBetween(17,55));
			studentRepository.save(student);
		}
	}
}
