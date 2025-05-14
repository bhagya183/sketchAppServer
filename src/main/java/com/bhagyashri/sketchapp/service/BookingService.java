package com.bhagyashri.sketchapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhagyashri.sketchapp.dao.AddBookingDAO;
import com.bhagyashri.sketchapp.entity.Artist;
import com.bhagyashri.sketchapp.entity.Booking;
import com.bhagyashri.sketchapp.entity.User;
import com.bhagyashri.sketchapp.repository.ArtistRepository;
import com.bhagyashri.sketchapp.repository.BookingRepository;
import com.bhagyashri.sketchapp.repository.UserRepository;

@Service
public class BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	public Booking createBooking(AddBookingDAO addBookingDAO) {
		
		User user = this.userRepository.findById(addBookingDAO.getUser_id()).orElse(null);
		
		Artist artist = this.artistRepository.findById(addBookingDAO.getArtist_id()).orElse(null);
		
		Booking bookings = new Booking();
		
		bookings.setStatus(addBookingDAO.getStatus());
		bookings.setTotal_price(addBookingDAO.getTotal_price());
		bookings.setArtist(artist);
		bookings.setUser(user);
		bookings.setSketchDetails(addBookingDAO.getSketchDetails());
		
		this.bookingRepository.save(bookings);
		
		return bookings;
	}
}
