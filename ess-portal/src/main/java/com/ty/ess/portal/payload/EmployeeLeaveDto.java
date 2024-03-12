package com.ty.ess.portal.payload;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveDto {
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Invalid date format..please use dd/MM/yyyy format..")
	private String leaveDate;
}
