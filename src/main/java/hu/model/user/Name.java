package hu.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Embeddable
public class Name implements Serializable {
	private TitleEnum title;
	private String firstName;
	private String lastName;
	private String fullName;

	@Transient
	public String getFullName(){
		String titleName = "";
		if (title != null){
			titleName  = title.name();
		}
		return titleName + " " + firstName + " " + lastName;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 20)
	public TitleEnum getTitle() {
		return title;
	}

	public void setTitle(TitleEnum title) {
		this.title = title;
	}

	
	@Column(nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	@Column(nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
