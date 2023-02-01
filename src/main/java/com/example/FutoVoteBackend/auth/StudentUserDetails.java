package com.example.FutoVoteBackend.auth;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.FutoVoteBackend.models.Student;

public class StudentUserDetails implements UserDetails {

	private final Set<? extends GrantedAuthority> grantedAuthorities;
	private final String password;
	private final String matricNo;
	private final Boolean isEnabled;

	private final Boolean isLocked;

	public StudentUserDetails(Student student) {
		this.grantedAuthorities = student.getRoles().stream().map(role -> new SimpleGrantedAuthority(String.valueOf(role.getRole()))).collect(
				Collectors.toSet());
		this.password = student.getPassword();
		this.matricNo = student.getMatricNo();
		this.isEnabled = student.getIsEnabled();
		this.isLocked = student.getIsLocked();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return matricNo;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
