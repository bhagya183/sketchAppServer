package com.bhagyashri.sketchapp.dao;

public class AddressDAO {
	
	private String city;
	private String state;
	private String country;
	private String zip;
	
	  public AddressDAO(String city, String state, String country, String zip) {
	        this.city = city;
	        this.state = state;
	        this.country = country;
	        this.zip = zip;
	    }
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
