package com.academy.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.entity.Student;

public interface StudentReposistory extends JpaRepository<Student, Long> {

	Student findByEmail(String email);
}
