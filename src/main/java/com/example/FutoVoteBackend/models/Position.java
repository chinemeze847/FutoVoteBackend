package com.example.FutoVoteBackend.models;

import javax.persistence.*;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "positions")
@Data
public class Position
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty
	private String positionName;

	@JsonProperty
	private String description;
}
