package io.swagger.api;

import io.swagger.model.ConsultationHour;
import io.swagger.model.ConsultationHourSearch;
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
public class ConsultationHourSearchApiController implements ConsultationHourSearchApi {

    public ResponseEntity<List<ConsultationHour>> consultationHourSearchPost(

@ApiParam(value = "Search data" ,required=true ) @RequestBody ConsultationHourSearch request

) {
        // do some magic!
        return new ResponseEntity<List<ConsultationHour>>(HttpStatus.OK);
    }

}
