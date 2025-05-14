package com.bhagyashri.sketchapp.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bhagyashri.sketchapp.dao.CheckOTPDAO;
import com.bhagyashri.sketchapp.dao.SendEmailDAO;
import com.bhagyashri.sketchapp.dao.UpdatePasswordDAO;
import com.bhagyashri.sketchapp.entity.User;
import com.bhagyashri.sketchapp.repository.UserRepository;

@Service
public class UpdatePasswordService {
	
	Random random = new Random();
	
	int otp =  0;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public String sendEmail(SendEmailDAO sendEmailDAO) {
		
			if(this.userRepository.findByEmail(sendEmailDAO.getEmail()).isPresent()) {
			
			SimpleMailMessage message = new SimpleMailMessage();
			
			otp = random.nextInt(10000);
			
			message.setFrom("bhagyashri18399@gmail.com");
			message.setTo(sendEmailDAO.getEmail());
			message.setSubject("OTP Verification");
			message.setText("OTP is : " + this.otp);
			emailSender.send(message);
			
			return "Email Send Successfully Done!";
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Email is NOT Registered!");
		}
	}
	
	public User updatePassword(UpdatePasswordDAO updatePasswordDAO, String email) {
		User user = new User();
		
		user = this.userRepository.findByEmail(email).orElse(null);
		
		if(updatePasswordDAO.getPassword().equals(updatePasswordDAO.getConfirmPassword())) {
			user.setPassword(passwordEncoder.encode(updatePasswordDAO.getPassword()));
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password And Confirm Password does Not Matched.");
		}
		
		this.userRepository.save(user);
		
		return user;
	}
	
	// for check OTP
	public String checkOTP(CheckOTPDAO checkOTPDAO) {
		
		if(this.otp == checkOTPDAO.getOtp()) {
			return "OTP Matched.";
		}else {
			return "OTP Not Matched!!!";
		}
	}
}
