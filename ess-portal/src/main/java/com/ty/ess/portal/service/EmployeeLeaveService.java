package com.ty.ess.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ess.portal.dao.EmployeeLeaveDao;
import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.EmployeeLeave;
import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.payload.EmployeeLeaveDto;
import com.ty.ess.portal.util.LeaveStatus;

@Service
public class EmployeeLeaveService {

	@Autowired
	private EmployeeLeaveDao employeeLeaveDao;

	// create leave request
//	public ResponseEntity<ResponseStructure<EmployeeLeave>> createLeaveRequest(int employeeId,
//			EmployeeLeaveDto employeeLeaveRequest) {
//		EmployeeLeave employeeLeave = new EmployeeLeave();
//	//	User user = userDao
//		//employeeLeave.builder().leaveDate(employeeLeaveRequest.getLeaveDate()).leaveStatus(LeaveStatus.PENDING).user(user).build();
//		employeeLeaveDao.createEmployeeLeaveRequest(employeeLeave);
//		
//	//	ResponseStructure<EmployeeLeave>
//	}
}
