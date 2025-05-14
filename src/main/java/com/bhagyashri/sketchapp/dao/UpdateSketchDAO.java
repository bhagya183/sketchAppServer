package com.bhagyashri.sketchapp.dao;

public class UpdateSketchDAO {
	
private String sketch_name;
	
	private String description;
	
	private  Integer price;
	
	private String category;

	public String getSketch_name() {
		return sketch_name;
	}

	public void setSketch_name(String sketch_name) {
		this.sketch_name = sketch_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "UpdateSketchDAO [sketch_name=" + sketch_name + ", description=" + description + ", price=" + price
				+ ", category=" + category + "]";
	}
	
	
}
