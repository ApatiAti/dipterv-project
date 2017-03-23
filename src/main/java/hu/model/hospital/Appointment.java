package hu.model.hospital;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import hu.model.user.User;

@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {

	private Long id;
	private ConsultationHour consultationHour;
	private User patient;
	private String complaints;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne
	@JoinColumn( name = "consultationHourId")
	public ConsultationHour getConsultationHour() {
		return consultationHour;
	}

	public void setConsultationHour(ConsultationHour consultationHour) {
		this.consultationHour = consultationHour;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "patientId")
	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	@Length(max = 500)
	@Column(nullable = true, length = 500)
	public String getComplaints() {
		return complaints;
	}

	public void setComplaints(String complaints) {
		this.complaints = complaints;
	}

}
