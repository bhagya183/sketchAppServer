package com.bhagyashri.sketchapp.dao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddSketchDAO {
	
	@NotNull
	@Size(min = 3, message = "Sketch name Should at least contain 3 characters")
	private String sketch_name;
	
	
	@NotNull
	private  Integer price;
	

	private String image_url;
	

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSketch_name() {
		return sketch_name;
	}

	public void setSketch_name(String sketch_name) {
		this.sketch_name = sketch_name;
	}


	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AddSketchDAO [sketch_name=" + sketch_name + ", price=" + price + ", image_url=" + image_url + "]";
	}

	
	
	
	
}
