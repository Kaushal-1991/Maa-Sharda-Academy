package com.academy.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.academy.dto.StudentCountDto;
import com.academy.dto.StudentDto;
import com.academy.entity.Student;
import com.academy.exceptions.AcademyException;
import com.academy.reposistory.StudentReposistory;
import com.academy.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentReposistory studentReposistory;

	@Override
	public StudentDto register(StudentDto studentDto) {
		Student student = studentReposistory.findByEmail(studentDto.getEmail());
		if(student != null) {
			throw new AcademyException("Email already registered", HttpStatus.CONFLICT);
		}
		Student savedStudent = studentReposistory.save(studentDto.toEntity());
		
		return savedStudent.toDto();
	}

	@Override
	public List<StudentDto> findAll() {
		List<Student> findAll = studentReposistory.findAll();
		return findAll.stream().map(Student::toDto).collect(Collectors.toList());
	}

	@Override
	public StudentCountDto getStudentCounts() {
		System.out.println("=======>"+ studentReposistory.getStudentCounts());
		return studentReposistory.getStudentCounts();
	}

}
