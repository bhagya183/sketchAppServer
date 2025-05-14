package com.bhagyashri.sketchapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bhagyashri.sketchapp.dao.AddSketchDAO;
import com.bhagyashri.sketchapp.dao.UpdateSketchDAO;
import com.bhagyashri.sketchapp.service.SketchService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/sketches")
public class SketchController {

	@Autowired
	SketchService sketchService;
	
	@PostMapping("")
	public ResponseEntity<?> createSketch(@RequestBody @Valid AddSketchDAO addSketchDAO){
		return ResponseEntity.ok(this.sketchService.createSketch(addSketchDAO));
	}
	
	@GetMapping("")
	public ResponseEntity<?> readAll(){
		
		return ResponseEntity.ok(this.sketchService.readAllSketch());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Integer id){
		return ResponseEntity.ok(this.sketchService.readSketchById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSketch(@PathVariable Integer id){
		return ResponseEntity.ok(this.sketchService.deleteSketch(id));
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSketch( @PathVariable Integer id, @RequestBody UpdateSketchDAO updateSketchDAO){
		return ResponseEntity.ok(this.sketchService.updateSketch(id, updateSketchDAO));
	}
	
	// Upload Image
	@PostMapping("/{id}/upload")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestParam("file") MultipartFile file){
		return ResponseEntity.ok(this.sketchService.storeFile(id, file));
	}
	
	// Download Image
	@GetMapping("/download/{filename}")
	public ResponseEntity<?> download(@PathVariable String filename){
		
		Resource resource = this.sketchService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+filename+"\"").body(resource);
	}
}
