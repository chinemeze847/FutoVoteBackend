package com.example.FutoVoteBackend.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@JsonProperty
	String firstName;

	@JsonProperty
	String lastName;

	@JsonProperty
	String matricNo;

	@JsonProperty
	String password;

	@JsonProperty
	Boolean hasVoted;
}
