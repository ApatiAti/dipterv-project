package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Department;
import io.swagger.model.Error;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

@Api(value = "getDepartmentsAndTypes", description = "the getDepartmentsAndTypes API")
public interface GetDepartmentsAndTypesApi {

    @ApiOperation(value = "Get Departments and department's consultationHour's Types", notes = "The DepartmentsAndTypes endpoint returns information about the hospital's departments and about the consultation Hour's tpyes ", response = Object.class, responseContainer = "List", tags={ "Department", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = Department.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/getDepartmentsAndTypes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> getDepartmentsAndTypesPost();

}
