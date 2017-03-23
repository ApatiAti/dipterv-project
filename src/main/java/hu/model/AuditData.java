package hu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AuditData implements Serializable {

	private Date createDate;
	private Date modifyDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;

	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@PreUpdate
	public void onUpdate() {
		this.modifyDate = new Date();
	}

	@PrePersist
	public void onPersist() {
		this.createDate = new Date();
		if (modifyDate == null){
			this.modifyDate = this.createDate;
		}
	}

}
