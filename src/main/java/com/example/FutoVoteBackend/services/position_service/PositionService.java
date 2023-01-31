package com.example.FutoVoteBackend.services.position_service;

import java.util.List;

import com.example.FutoVoteBackend.dto.PositionDto;
import com.example.FutoVoteBackend.dto.StudentRequestDto;
import com.example.FutoVoteBackend.models.Position;
import com.example.FutoVoteBackend.models.Student;

public interface PositionService
{
	Position createPosition(PositionDto pos);

	List<Position> getAllPositions();

	Position updatePosition(PositionDto pos);
}
