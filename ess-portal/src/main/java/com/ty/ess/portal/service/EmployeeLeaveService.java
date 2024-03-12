package com.ty.ess.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ty.ess.portal.dao.EmployeeLeaveDao;
import com.ty.ess.portal.dao.UserDao;
import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.EmployeeLeave;
import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.exception.ValidationException;
import com.ty.ess.portal.payload.EmployeeLeaveDto;
import com.ty.ess.portal.util.LeaveStatus;

@Service
public class EmployeeLeaveService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeLeaveDao employeeLeaveDao;

	// create leave request
	public ResponseEntity<ResponseStructure<EmployeeLeave>> createLeaveRequest(int employeeId,
			EmployeeLeaveDto employeeLeaveRequest, BindingResult result) {
		if (result.hasErrors()) {
			String message = "";
			for (FieldError error : result.getFieldErrors()) {
				message = message + error.getDefaultMessage() + "\n";
			}
			throw new ValidationException(message);
		}
		EmployeeLeave employeeLeave = new EmployeeLeave();
		User user = userDao.findUserByUserId(employeeId);
		if (user != null) {
			employeeLeave.builder().leaveDate(employeeLeaveRequest.getLeaveDate()).leaveStatus(LeaveStatus.PENDING)
					.user(user).build();
			employeeLeaveDao.createEmployeeLeaveRequest(employeeLeave);

			ResponseStructure<EmployeeLeave> response = new ResponseStructure<EmployeeLeave>();
			response.builder().statusCode(HttpStatus.CREATED.value()).message("Success").data(employeeLeave).build();
			return new ResponseEntity<ResponseStructure<EmployeeLeave>>(response, HttpStatus.CREATED);
		} else {
			throw new UsernameNotFoundException("Employee Not Found..!");
		}
	}

	// find employee by id
	public ResponseEntity<ResponseStructure<List<EmployeeLeave>>> findEmployeeLeaveByEmployeeId(int employeeId) {
		List<EmployeeLeave> employeeLeaves = employeeLeaveDao.findEmployeeById(employeeId);
		if (!employeeLeaves.isEmpty()) {
			ResponseStructure<List<EmployeeLeave>> response = new ResponseStructure<List<EmployeeLeave>>();
			response.builder().statusCode(HttpStatus.OK.value()).message("Success").data(employeeLeaves).build();

			return new ResponseEntity<ResponseStructure<List<EmployeeLeave>>>(response, HttpStatus.OK);
		} else {
			throw new UsernameNotFoundException("Employee Not Found..!");
		}

	}
}
