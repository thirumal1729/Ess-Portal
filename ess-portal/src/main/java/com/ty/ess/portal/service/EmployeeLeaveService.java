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

@Service
public class EmployeeLeaveService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeLeaveDao employeeLeaveDao;

	// Apply leave Request
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
			employeeLeave.setLeaveDate(employeeLeave.getLeaveDate());
			employeeLeaveDao.createEmployeeLeaveRequest(employeeLeave);

			ResponseStructure<EmployeeLeave> response = new ResponseStructure<EmployeeLeave>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Success");
			response.setData(employeeLeave);
			return new ResponseEntity<ResponseStructure<EmployeeLeave>>(response, HttpStatus.CREATED);
		} else {
			throw new UsernameNotFoundException("Employee Not Found..!");
		}
	}

	// find leave list by EmployeeId
	public ResponseEntity<ResponseStructure<List<EmployeeLeave>>> findEmployeeLeaveByEmployeeId(int employeeId) {
		List<EmployeeLeave> employeeLeaves = employeeLeaveDao.findEmployeeById(employeeId);
		if (!employeeLeaves.isEmpty()) {
			ResponseStructure<List<EmployeeLeave>> response = new ResponseStructure<List<EmployeeLeave>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Found");
			response.setData(employeeLeaves);

			return new ResponseEntity<ResponseStructure<List<EmployeeLeave>>>(response, HttpStatus.OK);
		} else {
			throw new UsernameNotFoundException("Employee Not Found..!");
		}
	}
}
