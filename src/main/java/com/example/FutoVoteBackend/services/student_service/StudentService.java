package com.example.FutoVoteBackend.services.student_service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import com.example.FutoVoteBackend.dto.StudentLoginDto;
import com.example.FutoVoteBackend.dto.StudentRequestDto;
import com.example.FutoVoteBackend.models.Student;

public interface StudentService {

	String createStudent(StudentRequestDto request) throws MessagingException;


	Optional<Student> findStudent(StudentLoginDto request);

	List<Student> getAllStudents();

	String voteCandidate(String matricNo, String firstName );

	String confirmToken(String token);

	boolean activateUser(String email);
}
