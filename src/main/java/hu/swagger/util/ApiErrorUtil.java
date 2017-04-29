package hu.swagger.util;

import hu.exception.ConsultationHourNotFound;
import hu.exception.UserNotFoundException;
import hu.exception.security.AuthorizationException;
import io.swagger.model.Error;

public class ApiErrorUtil {

	public static Error createErrorFromException(Exception e){
		Error error = new Error();
		error.setMessage(e.getMessage());
		error.setCode(getErrorCodeByException(e));
		
		return error;
	}
	
	private static Integer getErrorCodeByException(Exception e) {
		if (e instanceof AuthorizationException){
			return ApiErrorCode.AUTHORIZATION.getCode();
		}
		if (e instanceof UserNotFoundException){
			return ApiErrorCode.INVALID_USER.getCode();
		}
		if (e instanceof ConsultationHourNotFound){
			return ApiErrorCode.INVALID_CONSULTATION_HOUR.getCode();
		}
		
		
		return ApiErrorCode.DEFAULT.getCode();
	}
}
