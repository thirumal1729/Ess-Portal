package com.ty.ess.portal.entity;

import java.util.Collection;
import java.util.Collections;

import org.hibernate.usertype.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class User implements UserDetails{

	private int userId;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	private String email;
	private String password;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(()-> userType.toString());
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
