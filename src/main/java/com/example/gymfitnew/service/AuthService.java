package com.example.gymfitnew.service;

import com.example.gymfitnew.dto.JwtAuthResponse;
import com.example.gymfitnew.dto.LoginDto;

public interface AuthService {
	JwtAuthResponse login(LoginDto loginDto);

}
