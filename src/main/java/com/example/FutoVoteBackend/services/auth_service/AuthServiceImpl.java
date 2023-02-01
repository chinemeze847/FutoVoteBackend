package com.example.FutoVoteBackend.services.auth_service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.FutoVoteBackend.auth.StudentUserDetails;
import com.example.FutoVoteBackend.auth.StudentUserDetailsService;
import com.example.FutoVoteBackend.dto.AuthRequest;
import com.example.FutoVoteBackend.dto.AuthResponse;
import com.example.FutoVoteBackend.exception.InvalidCredentialsException;
import com.example.FutoVoteBackend.utils.JwtUtils;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService
{
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private StudentUserDetailsService studentUserDetailsService;
	@Autowired
	private JwtUtils jwtUtils;
	@Override
	public AuthResponse authenticateUser(AuthRequest request)
	{
		log.info("Authenticate User with Credentials {}", request);

		StudentUserDetails student = (StudentUserDetails) studentUserDetailsService.loadUserByUsername(request.getMatricNo());

		log.info("student Username :  {}", student.getUsername());
		log.info("student password :  {}", student.getPassword());

		String jwtToken = null;
		UsernamePasswordAuthenticationToken userToken =
				new UsernamePasswordAuthenticationToken(
						student.getUsername(),
						student.getPassword());

		log.info("user token  : {} ", userToken);
		try {
			log.info("Authenticating User");
			authenticationManager.authenticate(userToken);
			log.info("Authentication Successful");
		} catch (Exception e) {
			log.error("Error Occured while authenticating {}", e.getMessage());
			throw new InvalidCredentialsException("Invalid Credentials");
		}

		log.info("Generating Token");
		jwtToken = jwtUtils.generateToken(request.getMatricNo());
		log.info("Token Generated");
		AuthResponse authRepsonse = new AuthResponse();
		authRepsonse.setStatus("Success");
		authRepsonse.setMessage("Login Successful");
		authRepsonse.setAccessToken(jwtToken);
		return authRepsonse;
	}

}

