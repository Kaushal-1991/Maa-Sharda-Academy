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
			        COUNT(CASE WHEN s.musicOption = 'GUITAR' THEN 1 END),
			        COUNT(CASE WHEN s.musicOption = 'HARMONIUM' THEN 1 END),
			        COUNT(CASE WHEN s.musicOption = 'CLASSICAL_MUSIC' THEN 1 END),
			        COUNT(CASE WHEN s.musicOption = 'LIGHT_MUSIC' THEN 1 END)
			    )
			    FROM Student s
			""")
	StudentCountDto getStudentCounts();
}
