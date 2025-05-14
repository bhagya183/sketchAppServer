package com.bhagyashri.sketchapp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bhagyashri.sketchapp.dao.AddArtistDAO;
import com.bhagyashri.sketchapp.service.ArtistService;

@Controller
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtistController {
	
	@Autowired
	ArtistService artistService;
	
	@PostMapping("")
	public ResponseEntity<?> createArtist(@RequestBody AddArtistDAO addArtistDAO){
		
		return ResponseEntity.ok(this.artistService.createArtist(addArtistDAO));
	}
	
	@GetMapping("")
	public ResponseEntity<?> readAll(){
		
		return ResponseEntity.ok(this.artistService.readAllArtist());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Integer id){
		return ResponseEntity.ok(this.artistService.readArtistById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSketch(@PathVariable Integer id){
		return ResponseEntity.ok(this.artistService.deleteArtist(id));
	}
	
	// Upload Image
	@PostMapping("/{id}/upload")
	public ResponseEntity<?> uploadImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok(this.artistService.storeFile(id, file));
	}
	
	// Download Image
	@GetMapping("/download/{filename}")
	public ResponseEntity<?> downloadImage(@PathVariable String filename) {
		Resource resource = this.artistService.loadAsResource(filename);
		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"")
			.body(resource);
	}
}
