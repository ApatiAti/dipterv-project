package io.swagger.api;

import io.swagger.model.LoginResponse;
import io.swagger.model.Error;
import io.swagger.model.LoginRequest;

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

@Api(value = "login", description = "the login API")
public interface LoginApi {

    @ApiOperation(value = "Login method", notes = "Through the Login endpoint the user can login in the application. This endpoint if the login was succesful return a security token and the user's id ", response = LoginResponse.class, tags={ "Login", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Logged user's id and a security token", response = LoginResponse.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = LoginResponse.class) })
    @RequestMapping(value = "/login",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<LoginResponse> loginPost(

@ApiParam(value = "Login object" ,required=true ) @RequestBody LoginRequest email

);

}
