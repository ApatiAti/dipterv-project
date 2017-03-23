package hu.web.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@SuppressWarnings("serial")
@JsonAutoDetect
public class CustomMessage implements Serializable {

	public enum CustomMessageSeverity {
		SUCCESS("success"),
		INFO("info"),
		WARNING("warning"),
		ERROR("danger");
		
		CustomMessageSeverity() {
		}
		
		CustomMessageSeverity( String value) {
			this.value=value;
		}
		
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private CustomMessageSeverity severity;
	private String content;

	public CustomMessage(CustomMessageSeverity severity, String content){
		this.severity=severity;
		this.content=content;
	}
	
	public CustomMessageSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(CustomMessageSeverity severity) {
		this.severity = severity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
