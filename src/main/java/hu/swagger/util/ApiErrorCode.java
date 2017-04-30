package hu.swagger.util;

public enum ApiErrorCode {
	DEFAULT(1),
	AUTHORIZATION(2),
	INVALID_USER(3), 
	INVALID_CONSULTATION_HOUR(4),
	;
	
	private final int code;

	private ApiErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
