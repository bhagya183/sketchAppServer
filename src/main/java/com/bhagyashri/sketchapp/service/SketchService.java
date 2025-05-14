package com.bhagyashri.sketchapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bhagyashri.sketchapp.FileProperties;
import com.bhagyashri.sketchapp.dao.AddSketchDAO;
import com.bhagyashri.sketchapp.dao.UpdateSketchDAO;
import com.bhagyashri.sketchapp.entity.Sketch;
import com.bhagyashri.sketchapp.exception.StorageException;
import com.bhagyashri.sketchapp.repository.SketchRepository;


@Service
public class SketchService {
	
	@Autowired
	SketchRepository sketchRepository;
	
	// Final Variable
	private final Path rootlocation;
	
	// Constructor => for initializing the final variable.
	public  SketchService(FileProperties fileProperties) {
		
		this.rootlocation = Paths.get(fileProperties.getUploadDir());
		
		try {
			Files.createDirectories(rootlocation);
		}catch(IOException e) {
			throw new StorageException("Could not initialize directory.");
		}
	}
	
	
	//Add Sketch
	public Sketch createSketch(AddSketchDAO addSketchDAO) {
		
		Sketch sketch = new Sketch();
		sketch.setSketch_name(addSketchDAO.getSketch_name());
		//sketch.setDescription(addSketchDAO.getDescription());
		sketch.setPrice(addSketchDAO.getPrice());
	//	sketch.setCategory(addSketchDAO.getCategory());
		
		this.sketchRepository.save(sketch);
		
		return sketch;
	}
	
	// Read All Sketch
	public List<Sketch> readAllSketch(){
		
		List<Sketch> sketches = new ArrayList<Sketch>();
		
		sketches = this.sketchRepository.findAll();
		
		return sketches;
	}
	
	// Read By Id
	public Sketch readSketchById(Integer id) {
		
		Sketch sketch = new Sketch();
		
		sketch = this.sketchRepository.findById(id).orElse(null);
		
		return sketch;
		
	}
	
	// To Delete Sketch
	public String deleteSketch(Integer id) {
		
		Sketch sketch = this.readSketchById(id);
		
		this.sketchRepository.delete(sketch);
		
		return "Sketch Data Deleted.";
	}
	
	//Update Sketch
	public Sketch updateSketch(Integer id, UpdateSketchDAO updateSketchDAO) { 
		
		Sketch sketch = this.readSketchById(id);
		
		if(updateSketchDAO.getSketch_name() != null) {
			sketch.setSketch_name(updateSketchDAO.getSketch_name());
		}
		
	//	if(updateSketchDAO.getDescription() != null) {
		//	sketch.setDescription(updateSketchDAO.getDescription());
	//	}
		
		if(updateSketchDAO.getPrice() != null) {
			sketch.setPrice(updateSketchDAO.getPrice());
		}
		
	//	if(updateSketchDAO.getCategory() != null) {
		//	sketch.setCategory(updateSketchDAO.getCategory());
	//	}
		
		this.sketchRepository.save(sketch);
		
		return sketch;
		
	}
	
	
		//Upload File
		public String storeFile(Integer id, MultipartFile file) {
		
		try {
			
			if(file.isEmpty()) {
				throw new StorageException("File is empty");
			}
			
			Path destinationFile = this.rootlocation.resolve(file.getOriginalFilename());
			
			try (InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
			
			Sketch sketch = this.sketchRepository.findById(id).orElseThrow(
						() -> {
							throw new StorageException("Product with id " + id + " not found");
						}
					);
			
			String fileUploadDir = ServletUriComponentsBuilder.fromCurrentContextPath().path("/sketches/download/").path(file.getOriginalFilename()).toUriString();
		
			sketch.setImage_url(fileUploadDir);
			
			this.sketchRepository.save(sketch);
			
			return "File Upload Successfully Done.";
			
		}catch(IOException e) {
			throw new StorageException("File could not be saved.");
		}
	}
	
	// Download Image
	public Resource loadAsResource(String fileName) {
		
		Path file = rootlocation.resolve(fileName);
		
		try {
			
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() && resource.isReadable()) {
				return resource;
			}else {
				throw new StorageException("Could not read file");
			}
		}catch(MalformedURLException e) {
			throw new StorageException("Could not read file");
		}
	}
	

}
