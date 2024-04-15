package com.example.gymfitnew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDto {
	private String workout_name;
	private String image_name;
	private String showSequence;
}
