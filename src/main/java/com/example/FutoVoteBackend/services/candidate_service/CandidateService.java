package com.example.FutoVoteBackend.services.candidate_service;

import java.util.List;

import com.example.FutoVoteBackend.dto.CandidateDto;
import com.example.FutoVoteBackend.models.Candidate;
import com.example.FutoVoteBackend.models.Student;

public interface CandidateService  {
	List<Candidate> getAllCandidates();

	Candidate addCandidate(CandidateDto request);

	List<Candidate> getCandidatesForPosition(String Pos);
}
