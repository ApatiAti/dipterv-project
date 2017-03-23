package hu.model.document;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.model.hospital.Appointment;
import hu.model.user.User;

@Entity
@Table(name = "documentfile_appointment")
public class DocumentFileAppointment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private DocumentFile document;
	private Appointment appointment;
	private User createUser;
	private Date createDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "idDocument", referencedColumnName ="id")
	public DocumentFile getDocument() {
		return document;
	}

	public void setDocument(DocumentFile document) {
		this.document = document;
	}

	@OneToOne
	@JoinColumn(name = "idAppointment", referencedColumnName ="id")
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}


	@OneToOne
	@JoinColumn(name = "createUserId", referencedColumnName = "id")
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
