package com.example.FutoVoteBackend.services.token_service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FutoVoteBackend.exception.TokenNotFoundException;
import com.example.FutoVoteBackend.models.token.ConfirmationToken;
import com.example.FutoVoteBackend.repositories.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {

	private final ConfirmationTokenRepository tokenRepository;

	@Autowired
	public ConfirmationTokenService(ConfirmationTokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	public void saveConfirmationToken(ConfirmationToken token) {
		tokenRepository.save(token);
	}


	public ConfirmationToken getToken(String token) {
		Optional<ConfirmationToken> tokenOptional = tokenRepository.findByToken(token);
		return tokenOptional.orElseThrow(() -> new TokenNotFoundException("Token does not exist"));
	}

	public void setConfirmedAt(String token){
		Optional<ConfirmationToken> tokenOptional = tokenRepository.findByToken(token);
		if(tokenOptional.isPresent()){
			ConfirmationToken confirmationToken = tokenOptional.get();
			confirmationToken.setConfirmedAt(LocalDateTime.now());
		}
	}
}

