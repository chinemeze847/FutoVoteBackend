package com.example.FutoVoteBackend.filters;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.FutoVoteBackend.auth.StudentUserDetails;
import com.example.FutoVoteBackend.auth.StudentUserDetailsService;
import com.example.FutoVoteBackend.utils.JwtUtils;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter
{
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private StudentUserDetailsService studentUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException
	{
		String bearerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
		String username = null;
		String token = null;
		if (bearerAuth != null && bearerAuth.startsWith("Bearer")) {
			token = bearerAuth.substring(7);
			username = jwtUtils.getUsernameFromToken(token);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			log.info("The user name is {}",username);
			StudentUserDetails userDetails = (StudentUserDetails)studentUserDetailsService.loadUserByUsername(username);
			log.info("The userDetails name : {} {}", userDetails.getUsername(), userDetails.getPassword());
			Boolean isTokenValid = jwtUtils.validateToken(token, userDetails);

			log.info("is token valid {}", isTokenValid);
			if (isTokenValid) {
				UsernamePasswordAuthenticationToken userToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}

