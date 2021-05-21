package com.bhirawa.demoproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
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

    //we can also use @Param from spring to specify, instead with ?1 etc.
    @Query(value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age", nativeQuery = true)
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName,
            @Param("age")Integer age);

    //deletion
    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.id = :id")
    int deleteStudentById(@Param("id") Long id);

}
