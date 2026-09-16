package com.academy.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ConvertPassword {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String encodedPassword = passwordEncoder.encode("Admin@12345");

		System.out.println(encodedPassword);
	}
}
