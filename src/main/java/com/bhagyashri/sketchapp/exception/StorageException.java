package com.bhagyashri.sketchapp.exception;

public class StorageException extends RuntimeException {
	
	//Constructor
	public StorageException(String message) {
		
		//parent class Constructor
		super(message);
	}

	//Constructor with cause
	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
