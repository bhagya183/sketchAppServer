package com.bhagyashri.sketchapp.service;

import com.bhagyashri.sketchapp.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.bhagyashri.sketchapp.repository.UserProfileRepository;
import com.bhagyashri.sketchapp.repository.UserRepository;


@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public UserProfile getProfileByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(user -> user.getUserProfile())
            .orElseThrow(() -> new RuntimeException("User profile not found for email: " + email));
    }

    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getProfileById(Integer id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile createProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }

    public UserProfile updateProfile(Integer id, UserProfile updatedProfile) {
        return userProfileRepository.findById(id).map(profile -> {
            profile.setFirst_name(updatedProfile.getFirst_name());
            profile.setLast_name(updatedProfile.getLast_name());
            profile.setEmail(updatedProfile.getEmail());
            profile.setMobile(updatedProfile.getMobile());
            profile.setProfilePictureUrl(updatedProfile.getProfilePictureUrl());
            return userProfileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Profile not found with id " + id));
    }

    public void deleteProfile(Integer id) {
        userProfileRepository.deleteById(id);
    }
}
