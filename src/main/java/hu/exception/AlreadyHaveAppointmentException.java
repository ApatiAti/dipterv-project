package hu.exception;

@SuppressWarnings("serial")
public class AlreadyHaveAppointmentException extends Exception {
	private Long departmentId;

	public AlreadyHaveAppointmentException(Long departmentId) {
		super("Már van foglalása erre az időpontra");
		this.departmentId = departmentId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	
}
