package com.bhagyashri.sketchapp.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.bhagyashri.sketchapp.entity.UserProfile;
import com.bhagyashri.sketchapp.service.UserProfileService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") // Optional: enable frontend access
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUserProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            UserProfile profile = userProfileService.getProfileByEmail(email);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/profiles")
    public List<UserProfile> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }

    @GetMapping("/profiles/{id}")
    public Optional<UserProfile> getProfileById(@PathVariable Integer id) {
        return userProfileService.getProfileById(id);
    }

    @PostMapping("/profiles")
    public UserProfile createProfile(@RequestBody UserProfile profile) {
        return userProfileService.createProfile(profile);
    }

    @PutMapping("/profiles/{id}")
    public UserProfile updateProfile(@PathVariable Integer id, @RequestBody UserProfile updatedProfile) {
        return userProfileService.updateProfile(id, updatedProfile);
    }

    @DeleteMapping("/profiles/{id}")
    public void deleteProfile(@PathVariable Integer id) {
        userProfileService.deleteProfile(id);
    }
}
