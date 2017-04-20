package io.swagger.api;

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
import io.swagger.model.ConsultationHour;
import io.swagger.model.ConsultationHourSearch;
import io.swagger.model.Department;
import io.swagger.model.Error;
import io.swagger.model.LoginRequest;
import io.swagger.model.LoginResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T02:23:55.545+02:00")

@Api(value = "api", description = "the api API")
public interface ApiApi {

    @ApiOperation(value = "Delete appointment", notes = "With this endpoint user can delete an existing appointment ", response = Void.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Object> apiAppointmentDelete(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

);


    @ApiOperation(value = "Get appointment", notes = "With this endpoint user can get an existing appointment with it's id ", response = Appointment.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> apiAppointmentGet(@ApiParam(value = "Appointment", required = true) @RequestParam(value = "appointmentId", required = true) Long appointmentId



);


    @ApiOperation(value = "Get appointmentList", notes = "This endpoint returns information aboutthe user's appointment ", response = Appointment.class, responseContainer = "List", tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> apiAppointmentListGet(@ApiParam(value = "User's/Patient's id", required = true) @RequestParam(value = "userId", required = true) Long userId



);


    @ApiOperation(value = "Post appointment", notes = "With this endpoint user can create a new appointment ", response = Void.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Object> apiAppointmentPost(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

);


    @ApiOperation(value = "Put appointment", notes = "With this endpoint user can update an existing appointment ", response = Appointment.class, tags={ "Appointment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Appointment.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/appointment",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Object> apiAppointmentPut(

@ApiParam(value = "Appointment" ,required=true ) @RequestBody Appointment request

);


    @ApiOperation(value = "Search for ConsultationHour", notes = "The consultationHourSearch endpoint returns information about the searched consultationHours ", response = ConsultationHour.class, responseContainer = "List", tags={ "ConsultationHour", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = ConsultationHour.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/consultationHourSearch",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Object> apiConsultationHourSearchPost(

@ApiParam(value = "Search data" ,required=true ) @RequestBody ConsultationHourSearch request

);


    @ApiOperation(value = "Get Departments and department's consultationHour's Types", notes = "The DepartmentsAndTypes endpoint returns information about the hospital's departments and about the consultation Hour's tpyes ", response = Department.class, responseContainer = "List", tags={ "Department", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = Department.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/getDepartmentsAndTypes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> apiGetDepartmentsAndTypesGet();


    @ApiOperation(value = "Login method", notes = "Through the Login endpoint the user can login in the application. This endpoint if the login was succesful return a security token and the user's id ", response = LoginResponse.class, tags={ "Login", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Logged user's id and a security token", response = LoginResponse.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = LoginResponse.class) })
    @RequestMapping(value = "/api/login",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<LoginResponse> apiLoginPost(

@ApiParam(value = "Login object" ,required=true ) @RequestBody LoginRequest email

);

}
