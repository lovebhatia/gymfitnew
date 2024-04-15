package com.example.gymfitnew.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.gymfitnew.entity.GymRefreshToken;
import com.example.gymfitnew.entity.GymUser;

@Repository
public interface GymRefreshTokenRepository extends JpaRepository<GymRefreshToken, Long> {
	  GymRefreshToken findByRefreshToken(String token);
	  @Transactional
	  int deleteByGymUser(GymUser gymUser);
}
