package com.example.FutoVoteBackend.services.auth_service;

import com.example.FutoVoteBackend.dto.AuthRequest;
import com.example.FutoVoteBackend.dto.AuthResponse;

public interface AuthService
{
	AuthResponse authenticateUser(AuthRequest request);
}
