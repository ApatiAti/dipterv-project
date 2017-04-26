package io.swagger.api;

import io.swagger.model.ConsultationHour;
import io.swagger.model.ConsultationHourSearch;
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

@Api(value = "consultationHourSearch", description = "the consultationHourSearch API")
public interface ConsultationHourSearchApi {

    @ApiOperation(value = "Search for ConsultationHour", notes = "The consultationHourSearch endpoint returns information about the searched consultationHours ", response = ConsultationHour.class, responseContainer = "List", tags={ "ConsultationHour", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = ConsultationHour.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = ConsultationHour.class) })
    @RequestMapping(value = "/consultationHourSearch",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<List<ConsultationHour>> consultationHourSearchPost(

@ApiParam(value = "Search data" ,required=true ) @RequestBody ConsultationHourSearch request

);

}
