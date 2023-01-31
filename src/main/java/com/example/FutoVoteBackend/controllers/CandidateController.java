package com.example.FutoVoteBackend.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FutoVoteBackend.dto.CandidateDto;
import com.example.FutoVoteBackend.models.Candidate;
import com.example.FutoVoteBackend.services.candidate_service.CandidateService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/candidates")
public class CandidateController
{
	@Autowired
	CandidateService candidateService;

	@PostMapping("/add")
	public ResponseEntity<?> createCandidate(@RequestBody CandidateDto request){
		return new ResponseEntity<>(candidateService.addCandidate(request), HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<Candidate>> getPost(){
		List<Candidate> candidates = candidateService.getAllCandidates();
		return ResponseEntity.ok(candidates);
	}

	@GetMapping("/{position}")
	public ResponseEntity<List<Candidate>> getCandidatesForPosition(@PathVariable String position){
		List<Candidate> candidates = candidateService.getCandidatesForPosition(position);
		return ResponseEntity.ok(candidates);
	}

}
