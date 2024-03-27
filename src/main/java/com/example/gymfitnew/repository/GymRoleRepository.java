package com.example.gymfitnew.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gymfitnew.model.GymRoles;

public interface GymRoleRepository extends JpaRepository<GymRoles, Long> {
	  Optional<GymRoles> findByName(String name);

}
