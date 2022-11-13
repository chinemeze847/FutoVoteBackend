package com.example.FutoVoteBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentLoginDto
{
	private String matricNo;

	private String password;
}

