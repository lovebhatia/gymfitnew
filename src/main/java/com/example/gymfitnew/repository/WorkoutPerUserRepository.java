package com.example.gymfitnew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymfitnew.entity.WorkoutPerUser;

@Repository
public interface WorkoutPerUserRepository extends JpaRepository<WorkoutPerUser, Long> {
	
}
