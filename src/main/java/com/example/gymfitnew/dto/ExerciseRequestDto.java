package com.example.gymfitnew.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRequestDto {
    private String exerciseName;
    private String description;
    private String duration;
    private String caloriesBurned;
    private Date createdTime;
	private String gifName;
	private String setRange;
	private String showSequence;

    // getters and setters
}