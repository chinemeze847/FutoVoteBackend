package com.example.FutoVoteBackend.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FutoVoteBackend.dto.PositionDto;
import com.example.FutoVoteBackend.models.Position;
import com.example.FutoVoteBackend.services.position_service.PositionService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/positions")
public class PositionController
{
	@Autowired
	PositionService positionService;

	@PostMapping("/add")
	public ResponseEntity<?> createPosition(@RequestBody PositionDto pos){
		return new ResponseEntity<>(positionService.createPosition(pos), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Position>> getAllPositions(){
		List<Position> positions = positionService.getAllPositions();
		return ResponseEntity.ok(positions);
	}

	@PutMapping("/")
	public ResponseEntity<Position> updatePos(@RequestBody PositionDto position){
		Position pos = positionService.updatePosition(position);
		return ResponseEntity.ok(pos);
	}
}
