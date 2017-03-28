package hu.model.hospital;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import hu.model.user.User;

@SuppressWarnings("serial")
@Entity
@Table(name = "Department")
public class Department implements Serializable {

	private Long id;
	private String name;
	private String phoneNumber;
	private String place;
	private User departmentHead;
	private List<User> employee;
	private String description;

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Length(min =4)
	@NotNull
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Length(min = 11, max = 12)
	@NotBlank
	@Column(nullable = true, length = 12)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotBlank
	@Column(nullable = false)
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departmentHead", nullable = false)
	public User getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(User departmentHead) {
		this.departmentHead = departmentHead;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "department_to_user", joinColumns = @JoinColumn(name = "idDepartment"), inverseJoinColumns = @JoinColumn(name = "iduser"))
	public List<User> getEmployee() {
		return employee;
	}

	public void setEmployee(List<User> employee) {
		this.employee = employee;
	}

	@NotBlank
	@Length(max = 500)
	@Column(length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
