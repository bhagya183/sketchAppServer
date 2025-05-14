package com.bhagyashri.sketchapp.dao;

public class UpdatePasswordDAO {
	
	private String password;
	private String confirmPassword;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "UpdatePasswordDAO [password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
	
	
}
