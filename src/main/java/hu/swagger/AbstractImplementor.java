package hu.swagger;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hu.swagger.util.ApiErrorUtil;
import io.swagger.model.Error;

public abstract class AbstractImplementor {

	public AbstractImplementor() {
		super();
	}

	protected ResponseEntity<Object> handlingServiceCall(String succesLog, String errorLog, Callable<Object> method) {
		try{
			Object result = method.call();
			getLogger().info(succesLog);
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e){
			return exceptionHandling(e, errorLog);
		}
	}

	protected ResponseEntity<Object> exceptionHandling(Exception e, String logMessage) {
		Error error = ApiErrorUtil.createErrorFromException(e);
		
		getLogger().error(logMessage , e);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	public abstract Logger getLogger();
	
}