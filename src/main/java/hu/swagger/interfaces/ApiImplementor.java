package hu.swagger.interfaces;

import org.springframework.http.ResponseEntity;

import io.swagger.model.Appointment;
import io.swagger.model.ConsultationHourSearch;
import io.swagger.model.LoginRequest;
import io.swagger.model.LoginResponse;

public interface ApiImplementor {

	ResponseEntity<Object> apiAppointmentDelete(Appointment request);
                   
	ResponseEntity<Object> apiAppointmentGet(Long appointmentId);
                   
	ResponseEntity<Object> apiAppointmentListGet(Long userId);
	               
	ResponseEntity<Object> apiAppointmentPost(Appointment request);
                   
	ResponseEntity<Object> apiAppointmentPut(Appointment request);
                   
	ResponseEntity<Object> apiConsultationHourSearchPost(ConsultationHourSearch request);
                   
	ResponseEntity<Object> apiGetDepartmentsAndTypesGet();

}
