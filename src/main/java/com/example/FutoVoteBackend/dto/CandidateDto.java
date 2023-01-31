package com.example.FutoVoteBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.example.FutoVoteBackend.models.Position;

@Data
@AllArgsConstructor
public class CandidateDto
{
	private String firstName;

	private String lastName;

	private String level;

	private String position;

	private String photo;

	private String manifesto;
}
