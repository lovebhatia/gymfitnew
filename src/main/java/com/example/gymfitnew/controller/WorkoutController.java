package com.example.gymfitnew.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gymfitnew.dto.WorkoutPerUserDto;
import com.example.gymfitnew.model.GymUser;
import com.example.gymfitnew.model.WorkoutPerUser;
import com.example.gymfitnew.repository.GymUserRepository;
import com.example.gymfitnew.repository.WorkoutPerUserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/workout")
@AllArgsConstructor
public class WorkoutController {
	
	@Autowired
	private GymUserRepository gymUserRepository;
	
	@Autowired
	private WorkoutPerUserRepository workoutPerUserRepository;
	
	@PostMapping("/save/{id}")
	public ResponseEntity<?> saveWorkoutPerUser(@RequestBody WorkoutPerUserDto workoutPerUserDto, 
			@PathVariable(value = "id") Long userId ) {
		System.out.println("in api");
		GymUser gymUser = gymUserRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
		WorkoutPerUser workoutPerUser = new WorkoutPerUser();
		workoutPerUser.setWorkoutName(workoutPerUserDto.getWorkoutName());
		workoutPerUser.setGymUser(gymUser);
		workoutPerUserRepository.save(workoutPerUser);
		return ResponseEntity.ok().build();
	}
	
}
