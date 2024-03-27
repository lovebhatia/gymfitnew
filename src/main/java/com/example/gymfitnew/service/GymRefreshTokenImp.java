package com.example.gymfitnew.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.gymfitnew.dto.JwtAuthResponse;
import com.example.gymfitnew.model.GymRefreshToken;
import com.example.gymfitnew.repository.GymRefreshTokenRepository;
import com.example.gymfitnew.securityConfig.CustomUserDetailsService;
import com.example.gymfitnew.securityConfig.JwtTokenProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GymRefreshTokenImp implements GymRefreshTokenService {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private GymRefreshTokenRepository gymRefreshTokenRepository;
	

	@Override
	public JwtAuthResponse createRefreshOrAccessToken(String refreshToken) {
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		GymRefreshToken gymRefreshToken =  gymRefreshTokenRepository.findByRefreshToken(refreshToken);
		if(gymRefreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
			List<SimpleGrantedAuthority> list = jwtTokenProvider.getRolesFromToken(refreshToken);
			System.out.println(list.get(0).getAuthority());
			String userName = jwtTokenProvider.getUserName(refreshToken);
			UserDetails userdetails = customUserDetailsService.loadUserByUsername(userName);
			String accessToken = jwtTokenProvider.generateToken(userdetails);
			jwtAuthResponse.setAccessToken(accessToken);
			jwtAuthResponse.setRefreshToken(refreshToken);
			return jwtAuthResponse;
		} else {
			String userName = jwtTokenProvider.getUserName(refreshToken);
			UserDetails userdetails = customUserDetailsService.loadUserByUsername(userName);
			String generatedRefreshToken = jwtTokenProvider.generateRefreshToken(userdetails);
			String accessToken = jwtTokenProvider.generateToken(userdetails);
			jwtAuthResponse.setRefreshToken(generatedRefreshToken);
			jwtAuthResponse.setAccessToken(accessToken);
			gymRefreshTokenRepository.save(gymRefreshToken);
		}
		return jwtAuthResponse;
	}
}
