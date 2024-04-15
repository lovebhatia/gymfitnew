package com.example.gymfitnew.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.gymfitnew.entity.Workout;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
	List<WorkoutProjection> findAllBy();
	
	
	interface WorkoutProjection {
        Long getId();
        String getWorkout();
        Date getcreatedTime();
        String getShowSequence();
        String getImageName();
    }
	
}
