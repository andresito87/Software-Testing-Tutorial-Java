package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
    @Test
    void itShouldCheckIfStudentExistsEmail() {
        // given
        String email = "andrespodadera87@gmail.com";
        Student student = new Student(
                "Andres",
                email,
                Gender.FEMALE
        );
        underTest.save(student);

        // when
        boolean expected = underTest.selectExistsEmail(email);
        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentDoesNotExistsEmail() {
        // given
        String email = "andrespodadera@gmail.com";

        // when
        boolean expected = underTest.selectExistsEmail(email);

        // then
        assertThat(expected).isFalse();
    }
}