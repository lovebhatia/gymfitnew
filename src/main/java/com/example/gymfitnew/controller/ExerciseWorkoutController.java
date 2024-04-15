package com.example.gymfitnew.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gymfitnew.dto.ExerciseRequestDto;
import com.example.gymfitnew.dto.WorkoutRequestDto;
import com.example.gymfitnew.entity.Exercise;
import com.example.gymfitnew.entity.Workout;
import com.example.gymfitnew.repository.ExerciseRepository;
import com.example.gymfitnew.repository.WorkoutRepository;

@RestController
@RequestMapping("/api/exwo")
public class ExerciseWorkoutController {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	@PostMapping("/saveWorkout")
	private ResponseEntity<?> saveWorkoutWithExercise(@RequestBody Workout workout) {
		Workout workout2 =   workoutRepository.save(workout);
		return new ResponseEntity<Workout>(workout2, HttpStatus.OK);
	}
	
	@PostMapping("/workouts/{workoutId}/exercises/{exerciseId}")
    public ResponseEntity<?> addExerciseToWorkout(@PathVariable Long workoutId, @PathVariable Long exerciseId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        workout.getExercises().add(exercise);
        exercise.getWorkouts().add(workout);

        workoutRepository.save(workout);

        return ResponseEntity.status(HttpStatus.CREATED).body("Exercise added to workout successfully");
    }
	
	@PostMapping("/workouts/{workoutId}/exercises")
    public ResponseEntity<?> addExerciseToWorkout(@PathVariable Long workoutId, @RequestBody Exercise exercise) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        exercise.getWorkouts().add(workout);
        workout.getExercises().add(exercise);

        // Save the modified workout which will cascade save the exercise
        workoutRepository.save(workout);

        return ResponseEntity.status(HttpStatus.CREATED).body("Exercise added to workout successfully");
    }
	
	@PostMapping("/workouts")
    public ResponseEntity<?> createWorkoutWithExercises(@RequestBody WorkoutRequestDto workoutRequest) {
        Workout workout = new Workout();
        workout.setWorkout(workoutRequest.getWorkout());
        workout.setImageName(workout.getImageName());
        workout.setShowSequence(workoutRequest.getShowSequence());
        workout.setCreatedTime(new Date());

        for (ExerciseRequestDto exerciseRequest : workoutRequest.getExercises()) {
            Exercise exercise = new Exercise();
            exercise.setExerciseName(exerciseRequest.getExerciseName());
            exercise.setExerciseDescription(exerciseRequest.getDescription());
            exercise.setExerciseDuration(exerciseRequest.getDuration());
            exercise.setClaoriesBurned(exerciseRequest.getCaloriesBurned());
            exercise.setGifName(exerciseRequest.getGifName());
            exercise.setSetRange(exerciseRequest.getSetRange());
            exercise.setShowSequence(exerciseRequest.getShowSequence());
            exercise.setCreatedTime(new Date());
            
            exerciseRepository.save(exercise);

            
            exercise.getWorkouts().add(workout);
            workout.getExercises().add(exercise);
        }
        workoutRepository.save(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body("Workout with exercises saved successfully");
    
	}
}
