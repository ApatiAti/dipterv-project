package hu.util;

public enum EmailType {
	TESZT_EMAIL("NewFile", "Teszteléshez tökéletes"),
	DOCUMENT_UPLOADED("newDocumentUploaded", "Feltöltésre került egy új dokumentum.");
	
	private final String emailTemplateName;
	private final String emailSubject;
	
	private EmailType(String emailTemplateName, String emailSubject) {
		this.emailTemplateName = emailTemplateName;
		this.emailSubject = emailSubject;
	}

	public String getEmailTemplateName() {
		return emailTemplateName;
	}

	public String getEmailSubject() {
		return emailSubject;
	}
}
