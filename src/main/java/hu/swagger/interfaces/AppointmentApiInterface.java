package hu.swagger.interfaces;

import org.springframework.http.ResponseEntity;

import io.swagger.model.Appointment;

public interface AppointmentApiInterface {

	ResponseEntity<Object> apiAppointmentDelete(Appointment request);
                   
	ResponseEntity<Object> apiAppointmentGet(Long appointmentId);
                   
	ResponseEntity<Object> apiAppointmentListGet();
	               
	ResponseEntity<Object> apiAppointmentPost(Appointment request);
                   
	ResponseEntity<Object> apiAppointmentPut(Appointment request);

}
