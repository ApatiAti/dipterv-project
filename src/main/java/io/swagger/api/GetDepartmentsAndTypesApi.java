package io.swagger.api;

import io.swagger.model.Department;
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

@Api(value = "getDepartmentsAndTypes", description = "the getDepartmentsAndTypes API")
public interface GetDepartmentsAndTypesApi {

    @ApiOperation(value = "Get Departments and department's consultationHour's Types", notes = "The DepartmentsAndTypes endpoint returns information about the hospital's departments and about the consultation Hour's tpyes ", response = Department.class, responseContainer = "List", tags={ "Department", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = Department.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Department.class) })
    @RequestMapping(value = "/getDepartmentsAndTypes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Department>> getDepartmentsAndTypesGet();

}
