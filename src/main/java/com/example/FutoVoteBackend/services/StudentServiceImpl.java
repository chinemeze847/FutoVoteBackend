package com.example.FutoVoteBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FutoVoteBackend.dto.StudentLoginDto;
import com.example.FutoVoteBackend.dto.StudentRequestDto;
import com.example.FutoVoteBackend.models.Candidate;
import com.example.FutoVoteBackend.models.Student;
import com.example.FutoVoteBackend.repositories.CandidateRepository;
import com.example.FutoVoteBackend.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CandidateRepository candidateRepository;

	@Override
	public Student createStudent(StudentRequestDto request) {
		Student student = new Student();
		student.setFirstName(request.getFirstName());
		student.setLastName((request.getLastName()));
		student.setMatricNo((request.getMatricNo()));
		student.setPassword(request.getPassword());
		studentRepository.save(student);
		return student;
	}

	@Override
	public Student findStudent(StudentLoginDto request) {
		Optional<Student> student = studentRepository.findByMatricNo(request.getMatricNo());

		return student.get();
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public String voteCandidate(String matricNo, String firstName ){

		Optional<Student> student = studentRepository.findByMatricNo(matricNo);

		if(student.isPresent()){
			if(student.get().getHasVoted() == true)
				return "You have voted already !!!.";
		}

		Optional<Candidate> OptionalCandidate = candidateRepository.findByFirstName(firstName);

		if(OptionalCandidate.isPresent()){
			Candidate candidate = OptionalCandidate.get();
			candidate.setNumOfVotes(candidate.getNumOfVotes() + 1);
			return "Successful";
		}
		return "Candidate Not Found";
	}

}
