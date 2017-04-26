package io.swagger.api;

import io.swagger.model.LoginResponse;
import io.swagger.model.Error;
import io.swagger.model.LoginRequest;

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
public class LoginApiController implements LoginApi {

    public ResponseEntity<LoginResponse> loginPost(

@ApiParam(value = "Login object" ,required=true ) @RequestBody LoginRequest email

) {
        // do some magic!
        return new ResponseEntity<LoginResponse>(HttpStatus.OK);
    }

}
