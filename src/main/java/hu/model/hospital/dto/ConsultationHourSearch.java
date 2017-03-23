package hu.model.hospital.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ConsultationHourSearch {
//	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private Date startDate;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date beginDate) {
		this.endDate = beginDate;
	}
	
}
