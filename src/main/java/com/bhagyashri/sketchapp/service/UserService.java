package com.bhagyashri.sketchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bhagyashri.sketchapp.dao.RegisterUserDAO;
import com.bhagyashri.sketchapp.entity.User;
import com.bhagyashri.sketchapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User registerUser(RegisterUserDAO registerUserDAO) {
		
		// Check if email is already exists or not
		if(this.userRepository.findByEmail(registerUserDAO.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already Register with this email Id");
		}
		
		User user = new User();
		
		user.setFirst_name(registerUserDAO.getFirst_name());
		user.setLast_name(registerUserDAO.getLast_name());
		user.setEmail(registerUserDAO.getEmail());
		user.setPassword(passwordEncoder.encode(registerUserDAO.getPassword()));
		user.setMobile(registerUserDAO.getMobile());
		user.setRoles(registerUserDAO.getRoles());
		
		this.userRepository.save(user);
		
		return user;
	}
	
	
}
