package com.bhagyashri.sketchapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String first_name;
	
	private String last_name;
	
	private String specialization;
	
	private String availability;
	
	private String image_url;
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore  // Add this to prevent infinite recursion
	private List<Booking> booking;
	
	@OneToMany(mappedBy="artist")
	private List<Sketch> sketch;
	
	@CreatedDate
	private LocalDateTime created_at;
	
	@LastModifiedDate
	private LocalDateTime updated_at;

	public Integer getId() {
		return id;
	}

	public List<Booking> getBookings() {
		return booking;
	}

	public void setBookings(List<Booking> bookings) {
		this.booking = bookings;
	}

	public List<Sketch> getSketches() {
		return sketch;
	}

	public void setSketches(List<Sketch> sketches) {
		this.sketch = sketches;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public List<Sketch> getSketch() {
		return sketch;
	}

	public void setSketch(List<Sketch> sketch) {
		this.sketch = sketch;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", specialization="
				+ specialization + ", availability=" + availability + ", created_at=" + created_at + ", updated_at="
				+ updated_at + "]";
	}
	
	
}
