package com.academy.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.academy.dto.StudentCountDto;
import com.academy.dto.StudentDto;
import com.academy.dto.StudentRegisteredEvent;
import com.academy.entity.Student;
import com.academy.enums.RegistrationStatus;
import com.academy.exceptions.AcademyException;
//import com.academy.rabbitmq.StudentNotificationProducer;
import com.academy.reposistory.StudentReposistory;
import com.academy.service.StudentService;
import com.academy.utility.GenrateRegistrationNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentReposistory studentReposistory;

//	@Autowired
//	private final StudentNotificationProducer notificationProducer;

	@Override
	public StudentDto register(StudentDto studentDto) {

		Student existingStudent = studentReposistory.findByEmail(studentDto.getEmail());

		if (existingStudent != null) {
			throw new AcademyException("Email already registered", HttpStatus.CONFLICT);
		}

		Student student = studentDto.toEntity();

		student.setRegistrationStatus(RegistrationStatus.PENDING);
		student.setRegistrationNumber(GenrateRegistrationNumber.generateRegistrationNumber(student.getName()));
		Student savedStudent = studentReposistory.save(student);

//		StudentRegisteredEvent registeredEvent = new StudentRegisteredEvent(savedStudent.getId(),
//				savedStudent.getName(), savedStudent.getEmail());

		// notificationProducer.sendStudentRegisteredEvent(registeredEvent);

		return savedStudent.toDto();
	}

	@Override
	public List<StudentDto> findAll() {
		List<Student> findAll = studentReposistory.findAll();
		return findAll.stream().map(Student::toDto).collect(Collectors.toList());
	}

	@Override
	public StudentCountDto getStudentCounts() {
		System.out.println("=======>" + studentReposistory.getStudentCounts());
		return studentReposistory.getStudentCounts();
	}

	@Override
	public void deleteStudent(Long id) {
		Student student = studentReposistory.findById(id)
				.orElseThrow(() -> new AcademyException("Student is not available", HttpStatus.MOVED_PERMANENTLY));
		studentReposistory.delete(student);
	}

	@Override
	public void registartionStudent(Long id,RegistrationStatus registrationStatus) {
		Student student = studentReposistory.findById(id)
				.orElseThrow(() -> new AcademyException("Student is not available", HttpStatus.MOVED_PERMANENTLY));
		if(RegistrationStatus.COMPLETED.equals(registrationStatus)) {
			student.setRegistrationStatus(RegistrationStatus.COMPLETED);
		}
		
		studentReposistory.save(student);
	}

}
