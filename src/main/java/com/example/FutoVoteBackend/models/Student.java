package com.example.FutoVoteBackend.models;

import java.util.Set;

import javax.persistence.*;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private int id;

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String lastName;


	@JsonProperty
	@Column(name = "username", unique = true)
	private String matricNo;

	@JsonProperty
	private String email;

	@JsonProperty
	private String password;


	@Column(name = "is_enabled")
	private Boolean isEnabled = false;

	@Column(name = "is_locked")
	private Boolean isLocked = false;

	@JsonProperty
	private Boolean hasVoted = false;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private Set<Role> roles;

}
