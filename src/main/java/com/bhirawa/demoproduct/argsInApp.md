before Faker added.

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

			//delete by ID with custom method and return affected row
			System.out.println(studentRepository.deleteStudentById(3L));



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

pagination and sorting before student_id_card created (testing purpose VERSION 1)

            //sorting(studentRepository);
			//pagination + sorting
			//example page 0 : first page, size of data per page 5
			PageRequest pageRequest = PageRequest.of(0,
					5,
					Sort.by("firstName").ascending());
			Page<Student> page = studentRepository.findAll(pageRequest);
			System.out.println(page);
create student entity only with 

            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@yahoo.com", firstName, lastName);
            Student student = new Student(firstName,
            lastName,
            email,
            faker.number().numberBetween(17,55));

            //generateRandomStudent(studentRepository);
			StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
			studentIdCardRepository.save(studentIdCard);

testing many to one, book student relationship

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
			studentIdCardRepository.save(studentIdCard);
			//studentIdCardRepository.findById(1L).ifPresent(System.out::println);

testing lazy fetch

            //testing Fetch Lazy on book
            studentRepository.findById(1L)
            .ifPresent(s-> {
            System.out.println("Fetch book Lazy...");
            List<Book> books = student.getBooks();
            books.forEach( book ->
            System.out.println(s.getFirstName() + " borrows " + book.getBookName() )
            );
            });