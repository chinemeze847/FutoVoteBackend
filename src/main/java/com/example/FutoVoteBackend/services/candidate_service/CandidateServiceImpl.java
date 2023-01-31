package com.example.FutoVoteBackend.services.candidate_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FutoVoteBackend.dto.CandidateDto;
import com.example.FutoVoteBackend.exception.PositionNotFoundException;
import com.example.FutoVoteBackend.models.Candidate;
import com.example.FutoVoteBackend.models.Position;
import com.example.FutoVoteBackend.repositories.CandidateRepository;
import com.example.FutoVoteBackend.repositories.PositionRepository;
import com.example.FutoVoteBackend.services.candidate_service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	PositionRepository positionRepository;

	@Override
	public List<Candidate> getAllCandidates()
	{
		return candidateRepository.findAll();
	}

	@Override
	public Candidate addCandidate(CandidateDto request) {

		Position position = positionRepository.findByPositionName(request.getPosition())
				.orElseThrow(() -> new PositionNotFoundException("position not found"));


		Candidate candidate = Candidate.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.position(position)
				.level(request.getLevel())
				.photo(request.getPhoto())
				.manifesto(request.getManifesto())
				.build();


		return candidateRepository.save(candidate);
	}

	@Override
	public List<Candidate> getCandidatesForPosition(String pos)
	{
		Position position = positionRepository.findByPositionName(pos)
				.orElseThrow(() -> new PositionNotFoundException("position not found"));

		List<Candidate> candidates = candidateRepository.findAllByPosition(position);

		return candidates;
	}
}
