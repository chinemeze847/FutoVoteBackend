package com.example.FutoVoteBackend.models;

import javax.persistence.*;

import lombok.*;


import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String lastName;


	@JsonProperty
	private String matricNo;

	@JsonProperty
	private String email;

	@Column(name = "is_enabled")
	private Boolean isEnabled = false;

	@Column(name = "is_locked")
	private Boolean isLocked = false;

	@JsonProperty
	private Boolean hasVoted = false;

}
