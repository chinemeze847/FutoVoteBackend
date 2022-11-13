package com.example.FutoVoteBackend.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FutoVoteBackend.dto.StudentRequestDto;
import com.example.FutoVoteBackend.models.Student;
import com.example.FutoVoteBackend.services.StudentService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/register")
	public ResponseEntity<?> createPost(@RequestBody StudentRequestDto request){
		return new ResponseEntity<>(studentService.createStudent(request), HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<Student>> getPost(){
		List<Student> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}
}
