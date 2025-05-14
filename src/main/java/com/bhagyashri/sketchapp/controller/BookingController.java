package com.bhagyashri.sketchapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhagyashri.sketchapp.dao.AddBookingDAO;
import com.bhagyashri.sketchapp.service.BookingService;

@Controller
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@PostMapping("")
	public ResponseEntity<?> createBooking(@RequestBody AddBookingDAO addBookingDAO){
		
		return ResponseEntity.ok(this.bookingService.createBooking(addBookingDAO));
	}
}
