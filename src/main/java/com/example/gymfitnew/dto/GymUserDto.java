package com.example.gymfitnew.dto;

import java.util.Set;

import com.example.gymfitnew.entity.GymRoles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymUserDto {
	private String username;
	private String email;
	private String name;
	private String mobileNumber;
	private String password;
	private String confirmPassword;
	private Set<GymRoles> gymRoles;
	
}
