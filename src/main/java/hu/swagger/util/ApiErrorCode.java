package hu.swagger.util;

public enum ApiErrorCode {
	DEFAULT(1),
	AUTHORIZATION(2),
	INVALID_USER(3), 
	INVALID_CONSULTATION_HOUR(4),
	;
	
	private long code;

	private ApiErrorCode(long code) {
		this.code = code;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}


	
	
}
