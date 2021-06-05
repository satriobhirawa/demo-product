package com.bhirawa.demoproduct;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProductApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										StudentIdCardRepository studentIdCardRepository){
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@yahoo.com", firstName, lastName);
			Student student = new Student(firstName,
					lastName,
					email,
					faker.number().numberBetween(17,55));

			student.addBook(new Book("Clean Code", LocalDateTime.now().minusDays(4)));
			student.addBook(new Book("Craps Code", LocalDateTime.now()));
			student.addBook(new Book("Spring", LocalDateTime.now().minusYears(1)));
			//generateRandomStudent(studentRepository);
			StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
			//refactor student id card in student and saving as student
			student.setStudentIdCard(studentIdCard);

			student.enrollToCourse(new Course("Database", "IT"));
			student.enrollToCourse(new Course("Networking", "IT"));

			studentRepository.save(student);
			//studentIdCardRepository.save(studentIdCard);

			//testing Fetch Lazy on book
			studentRepository.findById(1L)
					.ifPresent(s-> {
						System.out.println("Fetch book Lazy...");
						List<Book> books = student.getBooks();
						books.forEach( book ->
								System.out.println(s.getFirstName() + " borrows " + book.getBookName() )
						);
					});

			//studentIdCardRepository.findById(1L).ifPresent(System.out::println);

		};
	}

	private void sorting(StudentRepository studentRepository) {
		//Sort by firstname ASC
		//Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
		//OR
		Sort sort = Sort.by("firstName").ascending()
				.and(Sort.by("age").descending());
		studentRepository.findAll(sort).forEach(student -> System.out.println(student.getFirstName() + " " + student.getAge()));
	}

	//use faker to populate the database Student
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
