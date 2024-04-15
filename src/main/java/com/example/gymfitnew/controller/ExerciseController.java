package com.example.gymfitnew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gymfitnew.entity.CustomWorkout;
import com.example.gymfitnew.entity.Exercise;
import com.example.gymfitnew.entity.Workout;
import com.example.gymfitnew.repository.CustomWorkoutRepository;
import com.example.gymfitnew.repository.ExerciseRepository;
import com.example.gymfitnew.repository.WorkoutRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/exercise")
@AllArgsConstructor
public class ExerciseController {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private CustomWorkoutRepository customWorkoutRepository;
	
	@Autowired 
	private WorkoutRepository workoutRepository;
	
	
	

}
