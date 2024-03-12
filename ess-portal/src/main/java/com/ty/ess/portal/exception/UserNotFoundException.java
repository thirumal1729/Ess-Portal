package com.ty.ess.portal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserNotFoundException extends RuntimeException {

	private String message = "";

	@Override
	public String getMessage() {
		return this.message;
	}
}
