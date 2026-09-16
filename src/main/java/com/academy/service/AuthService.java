package com.academy.service;

import com.academy.dto.LoginRequest;
import com.academy.dto.LoginResponse;
import com.academy.dto.RegisterRequest;

public interface AuthService {
	public LoginResponse login(LoginRequest request);
	public String register(RegisterRequest request);
}
