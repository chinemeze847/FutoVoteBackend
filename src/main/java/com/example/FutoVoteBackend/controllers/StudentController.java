package com.example.FutoVoteBackend.controllers;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FutoVoteBackend.dto.StudentLoginDto;
import com.example.FutoVoteBackend.dto.StudentRequestDto;
import com.example.FutoVoteBackend.dto.VoteRequestDto;
import com.example.FutoVoteBackend.models.Student;
import com.example.FutoVoteBackend.services.student_service.StudentService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/students")
public class StudentController {

	StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> createPost(@RequestBody StudentRequestDto request) throws MessagingException {
		return new ResponseEntity<>(studentService.createStudent(request), HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<Student>> getPost(){
		List<Student> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}

	@PostMapping("/login")
	public String loginStudent(@RequestBody StudentLoginDto request){
		Optional<Student> student = studentService.findStudent(request);

		if(student.get() != null)
			return "Successful Login";

		return "Student Not Found";
	}

	public String vote(@RequestBody VoteRequestDto request)
	{
		String voteStatus = studentService.voteCandidate(request.getMatricNo(), request.getFirstName());
		return voteStatus;
	}

	@GetMapping("/test")
	public String testEndpoint(){
		return "successfully Deployed";
	}

	@GetMapping("/confirm-email")
	public String confirmToken(@RequestParam("token") String token) {
		return studentService.confirmToken(token);
	}


}
