package com.bhagyashri.sketchapp.dao;

public class AddArtistDAO {
	
	private String first_name;
	
	private String last_name;
	
	private String specialization;
	
	private String availability;
	
	private Integer booking_id;
	
	private Integer sketch_id;

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

	public Integer getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(Integer booking_id) {
		this.booking_id = booking_id;
	}

	public Integer getSketch_id() {
		return sketch_id;
	}

	public void setSketch_id(Integer sketch_id) {
		this.sketch_id = sketch_id;
	}

	@Override
	public String toString() {
		return "AddArtistDAO [first_name=" + first_name + ", last_name=" + last_name + ", specialization="
				+ specialization + ", availability=" + availability + ", booking_id=" + booking_id + ", sketch_id="
				+ sketch_id + "]";
	}
	
	
}
