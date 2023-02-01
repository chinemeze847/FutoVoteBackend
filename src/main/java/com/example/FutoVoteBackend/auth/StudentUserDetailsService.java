package com.example.FutoVoteBackend.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.FutoVoteBackend.models.Student;
import com.example.FutoVoteBackend.repositories.StudentRepository;

@Service
public class StudentUserDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository repository;

	private final String USER_NOT_FOUND = "User with %s not found";

	@Autowired
	public StudentUserDetailsService(StudentRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String matricNo) throws UsernameNotFoundException
	{
		Optional<Student> studentOptional = repository.findByMatricNo(matricNo);

		if (studentOptional.isPresent())
		{
			Student student = studentOptional.get();
			return new StudentUserDetails(student);
		} else throw new UsernameNotFoundException(String.format(USER_NOT_FOUND, matricNo));
	}
}
