package hu.model.hospital;

import java.io.Serializable;
import javax.persistence.*;



@SuppressWarnings("serial")
@Entity
@Table(name="consultationhour_type", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"DEPARTMENTID", "NAME"})
	})
public class ConsultationHourType implements Serializable {

	private Long id;

	private Department department;

	private String name;
	
	public ConsultationHourType() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "DEPARTMENTID", nullable = false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}