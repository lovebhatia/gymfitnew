package com.example.gymfitnew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymfitnew.entity.CustomWorkout;
import com.example.gymfitnew.entity.WorkoutProgram;

@Repository
public interface CustomWorkoutRepository extends JpaRepository<CustomWorkout, Long> {
	List<CustomWorkout> findByWorkoutProgram(WorkoutProgram workoutProgram);
	
	interface CustomWorkoutProjection {
		int getId();
		String getCustomWorkoutName();
		String getImageName();
		int getSequence();
		
	}
}
