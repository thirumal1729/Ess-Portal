package com.ty.ess.portal.entity;

import com.ty.ess.portal.util.LeaveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeave {

	private long leaveId;
	private String leaveDate;
	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private User user;
}
