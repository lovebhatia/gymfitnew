package com.example.gymfitnew.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String workoutProgramName;
	@Column(name = "created_At", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdTime;
	private String publishedBy;
	private String level;
	private String mainGoal;
	private String daysPerWeek;
	private String durationRange;
	private String description;
	private double rating;
	private int sequence;
	private String imageName;
	private String icon;
	//@OneToMany(mappedBy = "workoutProgram", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	//private List<CustomWorkout> listCustomWorkouts;
	//@OneToMany(mappedBy = "workoutProgram", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	//private List<Workout> listWorkouts;
	
	@ManyToMany
	@JoinTable(name = "workout_program_workout",
			joinColumns = @JoinColumn(name = "workout_program_id"),
			inverseJoinColumns = @JoinColumn(name = "workout_id"))
	@JsonManagedReference
	private List<Workout> listWorkouts = new ArrayList<Workout>();

}
