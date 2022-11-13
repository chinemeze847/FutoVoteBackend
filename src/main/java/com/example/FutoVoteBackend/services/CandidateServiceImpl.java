package com.example.FutoVoteBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.FutoVoteBackend.models.Candidate;
import com.example.FutoVoteBackend.repositories.CandidateRepository;

public class CandidateServiceImpl implements CandidateService{

	@Autowired
	CandidateRepository candidateRepository;

	@Override
	public List<Candidate> getAllCandidates()
	{
		return candidateRepository.findAll();
	}
}
