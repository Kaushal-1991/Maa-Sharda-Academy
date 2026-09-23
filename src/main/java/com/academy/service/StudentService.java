package com.academy.service;

import java.util.List;

import com.academy.dto.StudentCountDto;
import com.academy.dto.StudentDto;

public interface StudentService {
	StudentDto register(StudentDto studentDto);
	List<StudentDto> findAll();
	StudentCountDto getStudentCounts();
	void deleteStudent(Long id); 
}
