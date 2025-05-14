package com.bhagyashri.sketchapp.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String status;
	
	private Integer total_price;

	private String sketchDetails;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@RestResource(path = "bookingUser", rel= "user")
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name="artist_id")
	@RestResource(path = "bookingArtist", rel="artist")
	@JsonBackReference
	private Artist artist;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@CreatedDate
	private LocalDateTime booking_date;
	
	@LastModifiedDate
	private LocalDateTime updated_at;
	

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

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public String getSketchDetails() {
		return sketchDetails;
	}

	public void setSketchDetails(String sketchDetails) {
		this.sketchDetails = sketchDetails;
	}
	
	

	@Override
	public String toString() {
		return "Booking [id=" + id + ", status=" + status + ", total_price=" + total_price + ", booking_date="
				+ booking_date + ", updated_at=" + updated_at + ", sketchDetails=" + sketchDetails + "]";
	}
	
	
}
