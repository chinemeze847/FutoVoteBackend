package com.example.FutoVoteBackend.services;

import java.util.List;

import com.example.FutoVoteBackend.models.Candidate;
import com.example.FutoVoteBackend.models.Student;

public interface CandidateService  {
	List<Candidate> getAllCandidates();
}
