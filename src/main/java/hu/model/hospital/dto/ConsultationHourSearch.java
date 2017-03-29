package hu.model.hospital.dto;

import java.util.Date;

public class ConsultationHourSearch {
//	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private Date startDate;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private Long chTypeId;
	
	
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

	/**
	 * ConsultationHourType.id
	 * @return
	 */
	public Long getChTypeId() {
		return chTypeId;
	}

	public void setChTypeId(Long chTypeId) {
		this.chTypeId = chTypeId;
	}
	
}
