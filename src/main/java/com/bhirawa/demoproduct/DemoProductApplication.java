package com.bhirawa.demoproduct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProductApplication.class, args);
	}
	CommandLineRunner commandLineRunner()
}
