package io.swagger.api;

import io.swagger.model.Appointment;
import io.swagger.model.Error;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T03:07:35.390+02:00")

@Api(value = "appointment", description = "the appointment API")
public interface AppointmentApi {

    @ApiOperation(value = "Delete appointment", notes = "With this endpoint user can delete an existing appointment ", response = Void.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Void.class) })
    @RequestMapping(value = "/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> appointmentDelete(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

);


    @ApiOperation(value = "Get appointment", notes = "With this endpoint user can get an existing appointment with it's id ", response = Appointment.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Appointment.class) })
    @RequestMapping(value = "/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Appointment> appointmentGet(@ApiParam(value = "Appointment", required = true) @RequestParam(value = "appointmentId", required = true) Long appointmentId



);


    @ApiOperation(value = "Get appointmentList", notes = "This endpoint returns information aboutthe user's appointment ", response = Appointment.class, responseContainer = "List", tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Appointment.class) })
    @RequestMapping(value = "/appointment/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Appointment>> appointmentListGet(@ApiParam(value = "User's/Patient's id", required = true) @RequestParam(value = "userId", required = true) Long userId



);


    @ApiOperation(value = "Post appointment", notes = "With this endpoint user can create a new appointment ", response = Void.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Void.class) })
    @RequestMapping(value = "/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> appointmentPost(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

);


    @ApiOperation(value = "Put appointment", notes = "With this endpoint user can update an existing appointment ", response = Void.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Void.class) })
    @RequestMapping(value = "/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> appointmentPut(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

);

}
