package com.example.gymfitnew.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String workout;
	@Column(name = "created_At", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdTime;
	private String imageName;
	private String showSequence;
	@ManyToMany
	@JoinTable(name = "workout_exercises",
			joinColumns = @JoinColumn(name = "workout_id"),
			inverseJoinColumns = @JoinColumn(name = "exercise_id"))
	@JsonManagedReference
	private List<Exercise> exercises = new ArrayList<Exercise>();
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workout_program_id")
	@JsonBackReference
	private WorkoutProgram workoutProgram;
	*/
	
	@ManyToMany(mappedBy = "listWorkouts")
	@JsonBackReference
	private List<WorkoutProgram> workoutProgram = new ArrayList<WorkoutProgram>();
	
}
