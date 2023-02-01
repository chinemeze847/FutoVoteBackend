package com.example.FutoVoteBackend.models;

import com.example.FutoVoteBackend.auth.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	UserRole role;

	@ManyToMany(mappedBy = "roles")
	List<Student> students;

}
