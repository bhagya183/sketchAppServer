package com.bhagyashri.sketchapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;

	    private String profilePictureUrl;
	    private String first_name;
	    private String last_name;
	    private String email;
	    private String mobile;

	    @OneToOne
	    @JsonIgnore
	    @JoinColumn(name = "user_id")
	    private User user;

	    public UserProfile() {
	    }

	    public UserProfile(String profilePictureUrl, String first_name, String last_name, String email, String mobile, User user) {
	        this.profilePictureUrl = profilePictureUrl;
	        this.first_name = first_name;
	        this.last_name = last_name;
	        this.email = email;
	        this.mobile = mobile;
	        this.user = user;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getProfilePictureUrl() {
			return profilePictureUrl;
		}

		public void setProfilePictureUrl(String profilePictureUrl) {
			this.profilePictureUrl = profilePictureUrl;
		}

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	    
	    

	  

}
