package com.ty.ess.portal.entity;

import com.ty.ess.portal.util.LeaveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "emp_leave_sequence")
	@SequenceGenerator(name = "emp_leave_sequence", initialValue = 200, allocationSize = 1, sequenceName = "leave_sequence")
	private long leaveId;
	private String leaveDate;
	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private User user;
}
