package com.example.FutoVoteBackend.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String lastName;

	@JsonProperty
	private String Description;

	@JsonProperty
	private Integer numOfVotes;
}
