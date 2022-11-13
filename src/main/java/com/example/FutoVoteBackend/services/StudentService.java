package com.example.FutoVoteBackend.services;

import java.util.List;

import com.example.FutoVoteBackend.dto.StudentLoginDto;
import com.example.FutoVoteBackend.dto.StudentRequestDto;
import com.example.FutoVoteBackend.models.Student;

public interface StudentService {

	Student createStudent(StudentRequestDto request);
	Student findStudent(StudentLoginDto request);

	List<Student> getAllStudents();
}
