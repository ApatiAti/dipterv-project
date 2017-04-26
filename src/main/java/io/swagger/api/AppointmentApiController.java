package io.swagger.api;

import io.swagger.model.Appointment;
import io.swagger.model.Error;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T03:07:35.390+02:00")

@Controller
public class AppointmentApiController implements AppointmentApi {

    public ResponseEntity<Void> appointmentDelete(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Appointment> appointmentGet(@ApiParam(value = "Appointment", required = true) @RequestParam(value = "appointmentId", required = true) Long appointmentId



) {
        // do some magic!
        return new ResponseEntity<Appointment>(HttpStatus.OK);
    }

    public ResponseEntity<List<Appointment>> appointmentListGet(@ApiParam(value = "User's/Patient's id", required = true) @RequestParam(value = "userId", required = true) Long userId



) {
        // do some magic!
        return new ResponseEntity<List<Appointment>>(HttpStatus.OK);
    }

    public ResponseEntity<Void> appointmentPost(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> appointmentPut(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
