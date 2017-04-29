package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.ConsultationHour;
import io.swagger.model.ConsultationHourSearch;
import io.swagger.model.Error;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

@Api(value = "consultationHourSearch", description = "the consultationHourSearch API")
public interface ConsultationHourSearchApi {

    @ApiOperation(value = "Search for ConsultationHour", notes = "The consultationHourSearch endpoint returns information about the searched consultationHours ", response = Object.class, responseContainer = "List", tags={ "ConsultationHour", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of products", response = ConsultationHour.class),
        @ApiResponse(code = 400, message = "Unexpected error", response = Error.class) })
    @RequestMapping(value = "/api/consultationHourSearch",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Object> consultationHourSearchPost(@ApiParam(value = "Search data" ,required=true ) @RequestBody ConsultationHourSearch request);

}
