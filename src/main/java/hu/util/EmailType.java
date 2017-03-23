package hu.util;

public enum EmailType {
	TESZT_EMAIL("NewFile", "Teszteléshez tökéletes"),
	DOCUMENT_UPLOADED("newDocumentUploaded", "Feltöltésre került egy új dokumentum.");
	
	private String emailTemplateName;
	private String emailSubject;
	
	private EmailType(String emailTemplateName, String emailSubject) {
		this.emailTemplateName = emailTemplateName;
		this.emailSubject = emailSubject;
	}

	public String getEmailTemplateName() {
		return emailTemplateName;
	}

	public void setEmailTemplateName(String emailTemplateName) {
		this.emailTemplateName = emailTemplateName;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	
	
}
