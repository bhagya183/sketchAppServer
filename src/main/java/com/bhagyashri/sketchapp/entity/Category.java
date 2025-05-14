package com.bhagyashri.sketchapp.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String category_name;
	private String description;
	
	@OneToMany(mappedBy = "category")
	private List<Sketch> sketch;
	
	
	
	public List<Sketch> getSketch() {
		return sketch;
	}
	public void setSketch(List<Sketch> sketch) {
		this.sketch = sketch;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", category_name=" + category_name + ", description=" + description + "]";
	}
	
	
}
