package com.example.FutoVoteBackend.services.position_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FutoVoteBackend.dto.PositionDto;
import com.example.FutoVoteBackend.exception.PositionNotFoundException;
import com.example.FutoVoteBackend.models.Position;
import com.example.FutoVoteBackend.repositories.PositionRepository;
import com.example.FutoVoteBackend.services.position_service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionRepository positionRepository;

	@Override
	public Position createPosition(PositionDto pos) {
		Position position = new Position();
		position.setPositionName(pos.getPosition());
		return positionRepository.save(position);
	}

	@Override
	public List<Position> getAllPositions() {
		return positionRepository.findAll();
	}

	@Override
	public Position updatePosition(PositionDto pos)
	{
		Position position = positionRepository.findByPositionName(pos.getPosition())
				.orElseThrow(() -> new PositionNotFoundException("position not found"));

		position.setDescription(pos.getDesc());
		position.setPositionName(pos.getPosition());
		positionRepository.save(position);

		return position;
	}

}
