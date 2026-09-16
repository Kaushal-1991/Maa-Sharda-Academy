package com.academy.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.academy.dto.LoginRequest;
import com.academy.dto.LoginResponse;
import com.academy.dto.RegisterRequest;
import com.academy.entity.User;
import com.academy.exceptions.AcademyException;
import com.academy.jwt.JwtService;
import com.academy.reposistory.UserRepository;
import com.academy.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtService jwtService) {

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@Override
	public String register(RegisterRequest request) {

		if (userRepository.findByEmail(request.getEmail()).isPresent()) {

			throw new RuntimeException("Email already registered");
		}

		User user = new User();

		user.setEmail(request.getEmail());

		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setRole("USER");

		userRepository.save(user);

		return "User registered successfully";
	}

	@Override
	public LoginResponse login(LoginRequest request) {

		if (request == null) {
			throw new AcademyException("Login request cannot be null", HttpStatus.BAD_REQUEST);
		}

		if (request.getEmail() == null || request.getEmail().isBlank()) {
			throw new AcademyException("Email is required", HttpStatus.BAD_REQUEST);
		}

		if (request.getPassword() == null || request.getPassword().isBlank()) {
			throw new AcademyException("Password is required", HttpStatus.BAD_REQUEST);
		}

		try {

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		} catch (BadCredentialsException ex) {
			throw new AcademyException("Invalid email or password", HttpStatus.UNAUTHORIZED);
		}

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new AcademyException("Invalid email or password", HttpStatus.UNAUTHORIZED));

		String accessToken = jwtService.generateToken(user.getEmail(), user.getRole());
		String refressToken = jwtService.generateRefreshToken(user.getEmail());
		

		return new LoginResponse(accessToken,refressToken,"Bearer",3600000);
	}
}
