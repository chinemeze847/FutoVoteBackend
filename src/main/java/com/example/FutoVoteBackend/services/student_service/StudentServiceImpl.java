package com.example.FutoVoteBackend.services.student_service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.FutoVoteBackend.dto.Mail;
import com.example.FutoVoteBackend.dto.StudentLoginDto;
import com.example.FutoVoteBackend.dto.StudentRequestDto;
import com.example.FutoVoteBackend.exception.EmailConfirmedException;
import com.example.FutoVoteBackend.exception.StudentNotFoundException;
import com.example.FutoVoteBackend.models.Candidate;
import com.example.FutoVoteBackend.models.Student;
import com.example.FutoVoteBackend.models.token.ConfirmationToken;
import com.example.FutoVoteBackend.repositories.CandidateRepository;
import com.example.FutoVoteBackend.repositories.StudentRepository;
import com.example.FutoVoteBackend.services.token_service.ConfirmationTokenService;
import com.example.FutoVoteBackend.services.mail_service.MailService;
import com.example.FutoVoteBackend.utils.ApplicationConstants;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

	public static final String ACTIVATION_LINK = "http://localhost:9090/api/students/confirm-email?token=";

	StudentRepository studentRepository;
	CandidateRepository candidateRepository;
	private final MailService mailService;

	ConfirmationTokenService confirmationTokenService;

	@Value("${mail-service.from}")
	private String emailFrom;


	public StudentServiceImpl(StudentRepository studentRepository,
			CandidateRepository candidateRepository, MailService mailService,
			ConfirmationTokenService confirmationTokenService)
	{
		this.studentRepository = studentRepository;
		this.candidateRepository = candidateRepository;
		this.mailService = mailService;
		this.confirmationTokenService = confirmationTokenService;
	}


	@Override
	public String createStudent(StudentRequestDto request) {
		Student student = new Student();
		student.setFirstName(request.getFirstName());
		student.setLastName((request.getLastName()));
		student.setMatricNo(request.getMatricNo());
		student.setEmail(request.getEmail());

		try {
			//Generating Token
			String token = UUID.randomUUID().toString();
			ConfirmationToken confirmationToken = new ConfirmationToken(
					token,
					LocalDateTime.now(),
					LocalDateTime.now().plusMinutes(15),
					null,
					student
			);
			String link = ACTIVATION_LINK + token;

		log.info("Saving user to Database");
		studentRepository.save(student);

		log.info("Saving Comfirmation Token");
		confirmationTokenService.saveConfirmationToken(confirmationToken);

		Mail mail = new Mail();
		mail.setContent(buildEmail(request.getFirstName(), link));
		mail.setTo(request.getEmail());
		mail.setFrom(emailFrom);
		mail.setSubject(ApplicationConstants.WELCOME_MESSAGE);
		mailService.sendEmail(mail);



		return ApplicationConstants.SIGN_UP_SUCCESS_MESSAGE;
		} catch (ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.FOUND, "Email already Exist", e);
		} catch (MessagingException e) {
			log.error("Email failed to send {}", e.getMessage());

		}
		return ApplicationConstants.SIGN_UP_NOTSUCCESS_MESSAGE;
	}

	@Override
	public Optional<Student> findStudent(StudentLoginDto request) {
		Optional<Student> optionalStudent = studentRepository.findByMatricNo(request.getMatricNo());

		if(optionalStudent.isPresent()){
				return optionalStudent;
		}

		return Optional.of(new Student());
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}


	public String voteCandidate(String matricNo, String firstName ){

		Optional<Student> optionalStudent = studentRepository.findByMatricNo(matricNo);

		if(optionalStudent.isPresent()){
			if(optionalStudent.get().getHasVoted() == true)
				return "You have voted already !!!.";
		}
		else{
			return "Student not found";
		}

		Optional<Candidate> OptionalCandidate = candidateRepository.findByFirstName(firstName);

		if(OptionalCandidate.isPresent()){
			Candidate candidate = OptionalCandidate.get();
			candidate.setNumOfVotes(candidate.getNumOfVotes() + 1);
			candidateRepository.save(candidate);
			Student student = optionalStudent.get();
			student.setHasVoted(true);
			studentRepository.save(student);
			return "Successful";
		}
		return "Candidate Not Found";
	}

	@Override
	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);

		if (confirmationToken.getConfirmedAt() != null) {
			throw new EmailConfirmedException("Email has been confirmed");
		}
		if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
			return "Token has expired";
		}
		confirmationTokenService.setConfirmedAt(token);
		activateUser(confirmationToken.getStudent().getEmail());
		return "Email Confirmed";
	}

	@Override
	public boolean activateUser(String email) {
		Optional<Student> studentOptional = studentRepository.findByEmail(email);
		if (studentOptional.isPresent()) {
			Student student = studentOptional.get();
			student.setIsEnabled(true);
			return true;
		} else throw new StudentNotFoundException(String.format("User with %s not found", email));
	}

	public String buildEmail(String name, String link) {

		String header = "<div" + "style= color: aliceblue; background-color: black; padding: 10px; margin: 10;" + ">"
				+ " <h3>Confirm Your Email</h3>" + " </div>";
		String greeting = "<p style=" + "margin: 10px;" + ">" + "Hi," + name + " </p>";

		String message = "<p style=" + "margin: 10px;" + "> Thank You for Registering to Futo Voting APP.</p>" + " <p style=" + "margin: 10px;"
				+ " > Please click the link below to activate your account </p>\n" +
				" </p>";
		String activateLink = "<a href=" + "'" + link + "'" + " style=" + "margin: 10px;font-weight: 500; color: blue;" + ">Click here to Activate your account</a>";

		return header + "\n" + greeting + "\n" + message + "\n" + activateLink;
	}
}
