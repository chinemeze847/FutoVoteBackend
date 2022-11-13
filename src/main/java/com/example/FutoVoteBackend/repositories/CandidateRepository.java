package com.example.FutoVoteBackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FutoVoteBackend.models.Candidate;

@Repository
public interface CandidateRepository  extends JpaRepository<Candidate, Long>
{
	Optional<Candidate> findByFirstName(String firstname);
}
