package com.example.gymfitnew.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.gymfitnew.dto.GymUserDto;
import com.example.gymfitnew.dto.JwtAuthResponse;
import com.example.gymfitnew.dto.LoginDto;
import com.example.gymfitnew.dto.RefreshAccessTokenRequest;
import com.example.gymfitnew.entity.GymRoles;
import com.example.gymfitnew.repository.GymRoleRepository;
import com.example.gymfitnew.securityConfig.CustomUserDetailsService;
import com.example.gymfitnew.service.AuthService;
import com.example.gymfitnew.service.GymRefreshTokenService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	private CustomUserDetailsService customUserDetailsService;
	private GymRoleRepository gymRoleRepository;
	private GymRefreshTokenService gymRefreshTokenService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
		JwtAuthResponse jwtAuthResponse = authService.login(loginDto);
		System.out.println("in login api");
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody GymUserDto user) throws Exception {
		Set<GymRoles> strRoles = user.getGymRoles();
		System.out.println(strRoles);
		Set<GymRoles> setGymRoles = new HashSet<GymRoles>();
		strRoles.forEach(role -> {
			GymRoles gymRoles = gymRoleRepository.findByName(role.getName())
					.orElseThrow(() -> new RuntimeException("Role not found"));
			setGymRoles.add(gymRoles);
		});
		/*
		if(strRoles == null) {
			GymRoles gymUserRole = gymRoleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			setGymRoles.add(gymUserRole);
		} else {
			strRoles.forEach(role -> {
				GymRoles gymRoles = gymRoleRepository.findByName(role)
						.orElseThrow(() -> new RuntimeException("Role not found"));
				setGymRoles.add(gymRoles);
			});
		}
		*/
		//user.setRoles(setGymRoles);
		user.setGymRoles(setGymRoles);	
		return ResponseEntity.ok(customUserDetailsService.save(user));
	}
	
	@PostMapping("/accessRefresh")
	public ResponseEntity<JwtAuthResponse> getAccessRefreshToken(@RequestBody RefreshAccessTokenRequest refreshToken) {
		System.out.println(refreshToken.getRefreshToken());
		JwtAuthResponse jwtAuthResponse = gymRefreshTokenService.createRefreshOrAccessToken(refreshToken.getRefreshToken());
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}
}
