package io.swagger.api;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Appointment;
import io.swagger.model.Error;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-30T19:21:17.373Z")

@Api(value = "appointment", description = "the appointment API")
public interface AppointmentApi {

    @ApiOperation(value = "Delete appointment", notes = "With this endpoint user can delete an existing appointment ", response = Object.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Object> appointmentDelete( @NotNull @ApiParam(value = "AppointmentId", required = true) @RequestParam(value = "appointmentId", required = true) Long appointmentId);


    @ApiOperation(value = "Get appointment", notes = "With this endpoint user can get an existing appointment with it's id ", response = Object.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> appointmentGet( @NotNull @ApiParam(value = "Appointment", required = true) @RequestParam(value = "appointmentId", required = true) Long appointmentId);


    @ApiOperation(value = "Get appointmentList", notes = "This endpoint returns information aboutthe user's appointment ", response = Object.class, responseContainer = "List", tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> appointmentListGet();


    @ApiOperation(value = "Post appointment", notes = "With this endpoint user can create a new appointment ", response = Object.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Object> appointmentPost(@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request);


    @ApiOperation(value = "Put appointment", notes = "With this endpoint user can update an existing appointment ", response = Object.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Object> appointmentPut(@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request);

}
