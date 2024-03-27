package com.example.gymfitnew.service;

import com.example.gymfitnew.dto.JwtAuthResponse;

public interface GymRefreshTokenService {
	JwtAuthResponse createRefreshOrAccessToken(String refreshToken);
}
