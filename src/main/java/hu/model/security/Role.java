package hu.model.security;

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
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Role implements Serializable {

	
	private Long id;
	private String describtion;
	private String code;
	private List<RoleGroup> groups;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getDescription() {
		return describtion;
	}

	public void setDescription(String describtion) {
		this.describtion = describtion;
	}

	@Column(nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_to_rolegroup",
			joinColumns = { @JoinColumn(name = "idRole") },
			inverseJoinColumns = {@JoinColumn(name = "idRoleGroup") })
	public List<RoleGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<RoleGroup> groups) {
		this.groups = groups;
	}

}
