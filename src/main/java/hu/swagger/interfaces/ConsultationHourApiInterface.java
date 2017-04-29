package hu.swagger.interfaces;

import org.springframework.http.ResponseEntity;

import io.swagger.model.ConsultationHourSearch;

public interface ConsultationHourApiInterface {

	ResponseEntity<Object> apiConsultationHourSearchPost(ConsultationHourSearch request);
	
}
