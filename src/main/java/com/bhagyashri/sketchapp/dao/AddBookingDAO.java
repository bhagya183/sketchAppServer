package com.bhagyashri.sketchapp.dao;

public class AddBookingDAO {
	
	private String status;
	
	private Integer total_price;
	
	private Integer user_id;
	
	private Integer artist_id;
	
	private String sketchDetails;

	public String getSketchDetails() {
		return sketchDetails;
	}

	public void setSketchDetails(String sketchDetails) {
		this.sketchDetails = sketchDetails;
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

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(Integer artist_id) {
		this.artist_id = artist_id;
	}

	@Override
	public String toString() {
		return "AddBookingDAO [status=" + status + ", total_price=" + total_price + ", user_id=" + user_id
				+ ", artist_id=" + artist_id + "]";
	}
	
	
}
