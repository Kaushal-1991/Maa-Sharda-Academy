package com.academy.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.dto.StudentCountDto;
import com.academy.entity.Student;

public interface StudentReposistory extends JpaRepository<Student, Long> {

	Student findByEmail(String email);
	
	   @Query("""
		   SELECT new com.academy.dto.StudentCountDto(
		       COUNT(s),
		       SUM(CASE WHEN s.musicOption = 'GUITAR' THEN 1 ELSE 0 END),
		       SUM(CASE WHEN s.musicOption = 'HARMONIUM' THEN 1 ELSE 0 END),
		       SUM(CASE WHEN s.musicOption = 'CLASSICAL_MUSIC' THEN 1 ELSE 0 END),
		       SUM(CASE WHEN s.musicOption = 'LIGHT_MUSIC' THEN 1 ELSE 0 END)
		  )
		  FROM Student s
	   """)
       StudentCountDto getStudentCounts();
}
