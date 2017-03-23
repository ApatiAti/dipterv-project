package hu.web.util.json.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.web.util.json.CustomDateJsonSerializer;

@SuppressWarnings("serial")
@JsonAutoDetect
@JsonInclude(Include.NON_NULL)
public class CitizenUpdateResponse implements Serializable {

	@JsonSerialize(using = CustomDateJsonSerializer.class)
	private Date lastWorkTime;
	private Long energy;
	private Long currentMoney;
	private String errorMessage;

	public Date getLastWorkTime() {
		return lastWorkTime;
	}

	public void setLastWorkTime(Date lastWorkTime) {
		this.lastWorkTime = lastWorkTime;
	}

	public Long getEnergy() {
		return energy;
	}

	public void setEnergy(Long energy) {
		this.energy = energy;
	}

	public Long getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(Long currentMoney) {
		this.currentMoney = currentMoney;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
