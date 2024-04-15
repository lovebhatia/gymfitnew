package com.example.gymfitnew.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomWorkout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customWorkoutName;
	private String imageName;
	private int sequence;
	@ManyToMany
    @JoinTable(
            name = "custom_workout_exercise",
           joinColumns = @JoinColumn(name = "workout_id"),
           inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
	private List<Exercise> listExercise;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workout_program_id")
	@JsonBackReference
	private WorkoutProgram workoutProgram;
}
