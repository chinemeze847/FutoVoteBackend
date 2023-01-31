package com.example.FutoVoteBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VoteRequestDto
{
	private String firstName;

	private String matricNo;
}
