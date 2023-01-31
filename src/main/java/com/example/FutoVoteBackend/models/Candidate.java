package com.example.FutoVoteBackend.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String lastName;

	@JsonProperty
	private String level;

	@JsonProperty
	@OneToOne
	@JoinColumn(name = "position_id", nullable = false)
	private Position position;

	@JsonProperty
	private String photo ;

	@JsonProperty
	private String manifesto;

	@JsonProperty
	@Column(name="num_of_votes")
	private String numOfVotes;

}
