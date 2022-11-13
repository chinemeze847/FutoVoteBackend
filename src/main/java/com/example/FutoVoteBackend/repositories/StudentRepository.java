package com.example.FutoVoteBackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FutoVoteBackend.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByMatricNo(String matricNo);
}
