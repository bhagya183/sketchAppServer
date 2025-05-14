package com.bhagyashri.sketchapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhagyashri.sketchapp.dao.CheckOTPDAO;
import com.bhagyashri.sketchapp.dao.SendEmailDAO;
import com.bhagyashri.sketchapp.dao.UpdatePasswordDAO;
import com.bhagyashri.sketchapp.service.UpdatePasswordService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/updatePassword")
public class UpdateUserController {

	@Autowired
	private UpdatePasswordService updatePasswordService;
	
	@PutMapping("")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDAO updatePasswordDAO, HttpSession session){
		
		String email = (String) session.getAttribute("email");
		
		return ResponseEntity.ok(
					this.updatePasswordService.updatePassword(updatePasswordDAO, email)
				);
	}
	
	@PostMapping("/email")
	public ResponseEntity<?> send(@RequestBody SendEmailDAO sendEmailDAO, HttpSession session){
		
		session.setAttribute("email", sendEmailDAO.getEmail());
		
		return ResponseEntity.ok(
					this.updatePasswordService.sendEmail(sendEmailDAO)
				);
				
	}
	
	//Check OTP
	@PostMapping("/check")
	public ResponseEntity<?> checkOTP(@RequestBody CheckOTPDAO checkOTPDAO){
		return ResponseEntity.ok(
					this.updatePasswordService.checkOTP(checkOTPDAO)
				);
	}
}
