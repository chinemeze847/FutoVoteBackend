package com.example.FutoVoteBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequestDto {
	private String firstName;

	private String lastName;

	private String matricNo;

	private String password;
}
