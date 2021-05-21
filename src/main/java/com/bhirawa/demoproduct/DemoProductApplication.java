package com.bhirawa.demoproduct;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProductApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {
			//use faker to populate the database
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

		};
	}
}
