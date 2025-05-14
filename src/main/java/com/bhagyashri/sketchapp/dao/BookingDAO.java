package com.bhagyashri.sketchapp.dao;

import java.time.LocalDateTime;

import com.bhagyashri.sketchapp.entity.Booking;

public class BookingDAO {
	
	    private Integer id;
	    private String status;
	    private String sketchDetails;
	    private Integer total_price;
	    private LocalDateTime booking_date;
	    private ArtistDAO artist;

	    public BookingDAO(Booking booking) {
	        this.id = booking.getId();
	        this.status = booking.getStatus();
	        this.sketchDetails = booking.getSketchDetails();
	        this.total_price = booking.getTotal_price();
	        this.booking_date = booking.getBooking_date();
	        this.artist = booking.getArtist() != null ? new ArtistDAO(booking.getArtist()) : null;

	    }
	    
	 
	    
	 // Getters and setters

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getSketchDetails() {
			return sketchDetails;
		}

		public void setSketchDetails(String sketchDetails) {
			this.sketchDetails = sketchDetails;
		}

		public Integer getTotal_price() {
			return total_price;
		}

		public void setTotal_price(Integer total_price) {
			this.total_price = total_price;
		}

		public LocalDateTime getBooking_date() {
			return booking_date;
		}

		public void setBooking_date(LocalDateTime booking_date) {
			this.booking_date = booking_date;
		}

		public ArtistDAO getArtist() {
			return artist;
		}

		public void setArtist(ArtistDAO artist) {
			this.artist = artist;
		}

	    
	    

}
