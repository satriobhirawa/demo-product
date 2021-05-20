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
			studentRepository.saveAll(List.of(bhirawa,satrio));

			//find student by ID
		};
	}
}
