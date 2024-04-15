package com.example.gymfitnew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymfitnew.entity.Exercise;
import com.example.gymfitnew.entity.Workout;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
		//List<Exercise> findByWorkout(Workout workout);
}
