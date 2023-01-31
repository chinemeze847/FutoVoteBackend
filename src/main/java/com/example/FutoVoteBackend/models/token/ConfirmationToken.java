package com.example.FutoVoteBackend.models.token;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.FutoVoteBackend.models.Student;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,LocalDateTime confirmedAt, Student student) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.confirmedAt = confirmedAt;
		this.student = student;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	private String token;

	private LocalDateTime createdAt;

	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	@ManyToOne
	@JoinColumn(nullable = false, name = "student_id")
	private Student student;
}
