package com.academy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.dto.StudentCountDto;
import com.academy.dto.StudentDto;
import com.academy.response.ApiResponse;
import com.academy.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<StudentDto>> register(@RequestBody StudentDto studentDto) {

		StudentDto savedStudent = studentService.register(studentDto);

		ApiResponse<StudentDto> response = new ApiResponse<>(true, "Student registered successfully", savedStudent);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/getAll")
	public ResponseEntity<ApiResponse<List<StudentDto>>> findAll() {

		List<StudentDto> students = studentService.findAll();

		ApiResponse<List<StudentDto>> response = new ApiResponse<>(true, "Students fetched successfully", students);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/count")
    public ResponseEntity<StudentCountDto> getStudentCounts() {
        return ResponseEntity.ok(
                studentService.getStudentCounts()
        );
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id){
		studentService.deleteStudent(id);
		return ResponseEntity.status(HttpStatus.OK).body("Student Deleted Sucessfully !!!");
	}
}