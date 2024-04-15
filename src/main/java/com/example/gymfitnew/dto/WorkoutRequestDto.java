package com.example.gymfitnew.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutRequestDto {
	 private String workout;
	    private List<ExerciseRequestDto> exercises;
	    private Date createdTime;
		private String imageName;
		private String showSequence;

	    // getters and setters
	}




