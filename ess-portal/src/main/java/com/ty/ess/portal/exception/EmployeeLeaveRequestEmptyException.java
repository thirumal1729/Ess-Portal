package com.ty.ess.portal.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveRequestEmptyException extends RuntimeException {

	private String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
