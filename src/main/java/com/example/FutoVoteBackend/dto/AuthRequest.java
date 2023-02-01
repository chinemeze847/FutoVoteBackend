package com.example.FutoVoteBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest
{
	@JsonProperty
	private String matricNo;

	@JsonProperty
	private String password;
}
