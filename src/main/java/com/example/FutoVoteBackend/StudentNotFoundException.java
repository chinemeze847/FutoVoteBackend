package com.example.FutoVoteBackend;

public class StudentNotFoundException extends  RuntimeException{
	public StudentNotFoundException(String message) {
		super(message);
	}
}
