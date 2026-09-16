package com.academy.dto;

import com.academy.entity.Student;
import com.academy.enums.MusicOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private MusicOption musicOption;
	private String address;
	
	public Student toEntity() {
	  return Student.builder()
			        .id(id)
			        .name(name)
			        .email(email)
			        .phone(phone)
			        .musicOption(musicOption)
			        .address(address)
			        .build();
	}
}
