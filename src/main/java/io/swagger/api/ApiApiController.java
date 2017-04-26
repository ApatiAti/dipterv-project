package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import hu.swagger.interfaces.ApiImplementor;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Appointment;
import io.swagger.model.ConsultationHourSearch;
import io.swagger.model.LoginRequest;
import io.swagger.model.LoginResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T02:23:55.545+02:00")

@Controller
public class ApiApiController implements ApiApi {

	@Autowired
	ApiImplementor apiImplementor;
	
	public ResponseEntity<Object> apiAppointmentDelete(
			@ApiParam(value = "Appointment", required = true) @RequestBody Appointment request	) {
		// do some magic!
		return apiImplementor.apiAppointmentDelete(request);
	}

	public ResponseEntity<Object> apiAppointmentGet(
			@ApiParam(value = "Appointment", required = true) @RequestParam(value = "appointmentId", required = true) Long appointmentId	) {
		// do some magic!
		return apiImplementor.apiAppointmentGet(appointmentId);
	}

	public ResponseEntity<Object> apiAppointmentListGet(
			@ApiParam(value = "User's/Patient's id", required = true) @RequestParam(value = "userId", required = true) Long userId	) {
		// do some magic!
		return apiImplementor.apiAppointmentListGet(userId);
	}

	public ResponseEntity<Object> apiAppointmentPost(
			@ApiParam(value = "Appointment", required = true) @RequestBody Appointment request	) {
		// do some magic!
		return apiImplementor.apiAppointmentPost(request);
	}

	public ResponseEntity<Object> apiAppointmentPut(
			@ApiParam(value = "Appointment", required = true) @RequestBody Appointment request	) {
		// do some magic!
		return apiImplementor.apiAppointmentPut(request);
	}

	public ResponseEntity<Object> apiConsultationHourSearchPost(
			@ApiParam(value = "Search data", required = true) @RequestBody ConsultationHourSearch request	) {
		// do some magic!
		return apiImplementor.apiConsultationHourSearchPost(request);
	}

	public ResponseEntity<Object> apiGetDepartmentsAndTypesGet() {
		// do some magic!
		return apiImplementor.apiGetDepartmentsAndTypesGet();
	}

	public ResponseEntity<LoginResponse> apiLoginPost(
			@ApiParam(value = "Login object", required = true) @RequestBody LoginRequest email	) {
		// do some magic!
		return new ResponseEntity<LoginResponse>(HttpStatus.OK);
	}

}
