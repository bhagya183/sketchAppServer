package com.bhagyashri.sketchapp.dao;

import com.bhagyashri.sketchapp.entity.Artist;

public class ArtistDAO {
	
	    private String first_name;
	    private String last_name;

	    public ArtistDAO(Artist artist) {
	        this.first_name = artist.getFirst_name();
	        this.last_name = artist.getLast_name();
	    }
	    
	    // Getters and setters

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

	   
	    

}
