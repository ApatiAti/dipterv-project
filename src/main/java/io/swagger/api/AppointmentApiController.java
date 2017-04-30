package io.swagger.api;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import hu.swagger.interfaces.AppointmentApiInterface;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Appointment;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

@Controller
public class AppointmentApiController implements AppointmentApi {

	@Autowired
	AppointmentApiInterface apiImplementor;

    public ResponseEntity<Object> appointmentDelete( @NotNull @ApiParam(value = "AppointmentId", required = true) @RequestParam(value = "request", required = true) Long request) {
        return apiImplementor.apiAppointmentDelete(request);
    }

    public ResponseEntity<Object> appointmentGet( @NotNull @ApiParam(value = "Appointment", required = true) @RequestParam(value = "appointmentId", required = true) Long appointmentId) {
    	return apiImplementor.apiAppointmentGet(appointmentId);
    }

    public ResponseEntity<Object> appointmentListGet() {
    	return apiImplementor.apiAppointmentListGet();
    }

    public ResponseEntity<Object> appointmentPost(@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request) {
    	return apiImplementor.apiAppointmentPost(request);
    }

    public ResponseEntity<Object> appointmentPut(@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request) {
    	return apiImplementor.apiAppointmentPut(request);
    }


}
