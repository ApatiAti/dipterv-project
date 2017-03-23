package hu.model.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import hu.model.user.User;

@SuppressWarnings("serial")
@Entity
public class RoleGroup implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String description;
	private String code;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_to_rolegroup",
			joinColumns = { @JoinColumn(name = "idRoleGroup") },
			inverseJoinColumns = { @JoinColumn(name = "idRole") })
	private List<Role> roles;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_to_rolegroup", 
	           joinColumns = { @JoinColumn(name = "idRoleGroup") }, 
	           inverseJoinColumns = { @JoinColumn(name = "idUser") })
	private List<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
