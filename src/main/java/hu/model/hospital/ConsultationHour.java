package hu.model.hospital;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;

import hu.model.user.User;

/**
 * @author Apati
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ConsultationHour")
public class ConsultationHour implements Serializable {

	private Long id;
	private Department department;
	private Date beginDate;
	private Date endDate;
	private int maxNumberOfPatient;
	private String room;	
	private List<Appointment> appointments;
	private ConsultationHourType type;
	private User doctor;

	
//	@Formula("select count(*) from Appointment a where a.consultationHour.id = id")
	private int numberOfAppointment;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "departmentId", nullable = false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Min(1)
	@NotNull
	@Column(nullable = false)
	public int getMaxNumberOfPatient() {
		return maxNumberOfPatient;
	}

	public void setMaxNumberOfPatient(int maxNumberOfPatient) {
		this.maxNumberOfPatient = maxNumberOfPatient;
	}

	@NotBlank
	@Column(nullable = false)
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@OneToMany(mappedBy = "consultationHour", fetch = FetchType.LAZY)
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "consultationhour_tpyeid", nullable = false)
	public ConsultationHourType getType() {
		return type;
	}

	public void setType(ConsultationHourType type) {
		this.type = type;
	}

	@Formula( value = "( select count(*) FROM ConsultationHour ch INNER JOIN Appointmenton on a.consultationHourId = ch.id where ch.id = id)")
	public int getNumberOfAppointment() {
		return numberOfAppointment;
	}
	
	public void setNumberOfAppointment(int numberOfAppointment) {
		this.numberOfAppointment = numberOfAppointment;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "doctorId", nullable = false)
	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	
	

}
