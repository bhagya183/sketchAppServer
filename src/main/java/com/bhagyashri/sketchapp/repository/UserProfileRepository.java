package com.bhagyashri.sketchapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhagyashri.sketchapp.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
	
	 UserProfile findByUserId(Integer userId);
	 
	 Optional<UserProfile> findByEmail(String email);

	 

}


