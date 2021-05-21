package com.bhirawa.demoproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository <Student, Long> {
    //not a must, but nice to have to gain full control of query
    //?1 means only passing one param and at first position on the method below
    @Query("SELECT s FROM Student s WHERE s.email =?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(
            String firstName, Integer age);

    @Query(value = "SELECT * FROM student WHERE first_name = ?1 AND age >= ?2", nativeQuery = true)
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            String firstName, Integer age);

}
