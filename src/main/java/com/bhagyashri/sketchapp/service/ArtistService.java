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
import com.bhagyashri.sketchapp.dao.AddArtistDAO;
import com.bhagyashri.sketchapp.entity.Artist;
import com.bhagyashri.sketchapp.entity.Booking;
import com.bhagyashri.sketchapp.entity.Sketch;
import com.bhagyashri.sketchapp.exception.StorageException;
import com.bhagyashri.sketchapp.repository.ArtistRepository;
import com.bhagyashri.sketchapp.repository.BookingRepository;
import com.bhagyashri.sketchapp.repository.SketchRepository;

@Service
public class ArtistService {

	@Autowired
	public ArtistRepository artistRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private SketchRepository sketchRepository;
	
	private final Path rootLocation;
	
	public ArtistService(FileProperties fileProperties) {
		this.rootLocation = Paths.get(fileProperties.getUploadDir());
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize directory.");
		}
	}
	
	public Artist createArtist(AddArtistDAO addArtistDAO) {
		
		Booking booking = this.bookingRepository.findById(addArtistDAO.getBooking_id()).orElse(null);
		
		Sketch sketch = this.sketchRepository.findById(addArtistDAO.getSketch_id()).orElse(null);
		
		Artist artists = new Artist(); 
		
		artists.setFirst_name(addArtistDAO.getFirst_name());
		artists.setLast_name(addArtistDAO.getLast_name());
		artists.setAvailability(addArtistDAO.getAvailability());
		artists.setSpecialization(addArtistDAO.getSpecialization());
		//artists.setBooking(booking);
		//artists.setSketches(sketch);
		
		
		this.artistRepository.save(artists);
		
		return artists;
		
	}
	
	// Read All Artist
	public List<Artist> readAllArtist(){
		
		List<Artist> artists = new ArrayList<Artist>();
		
		artists = this.artistRepository.findAll();
		
		System.out.println("Fetched artists: " + artists);
		
		return artists;
	}
	
	// Read By Id
	public Artist readArtistById(Integer id) {
		
		Artist artist = new Artist();
		
		artist = this.artistRepository.findById(id).orElse(null);
		
		return artist;
		
	}
	
	// To Delete Sketch
	public String deleteArtist(Integer id) {
		
		Artist artist = this.readArtistById(id);
		
		this.artistRepository.delete(artist);
		
		return "Sketch Data Deleted.";
	}
	
	// Upload File
	public String storeFile(Integer id, MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("File is empty");
			}
			
			Path destinationFile = this.rootLocation.resolve(file.getOriginalFilename());
			
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
			
			Artist artist = this.artistRepository.findById(id).orElseThrow(
				() -> new StorageException("Artist with id " + id + " not found")
			);
			
			String fileUploadDir = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/artists/download/")
				.path(file.getOriginalFilename())
				.toUriString();
			
			artist.setImage_url(fileUploadDir);
			this.artistRepository.save(artist);
			
			return "File Upload Successfully Done.";
		} catch (IOException e) {
			throw new StorageException("File could not be saved.");
		}
	}
	
	// Download Image
	public Resource loadAsResource(String fileName) {
		Path file = rootLocation.resolve(fileName);
		try {
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() && resource.isReadable()) {
				return resource;
			} else {
				throw new StorageException("Could not read file");
			}
		} catch (MalformedURLException e) {
			throw new StorageException("Could not read file");
		}
	}
}
