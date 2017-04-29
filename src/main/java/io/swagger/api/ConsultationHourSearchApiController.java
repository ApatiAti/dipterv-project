package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import hu.swagger.interfaces.ConsultationHourApiInterface;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ConsultationHourSearch;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

@Controller
public class ConsultationHourSearchApiController implements ConsultationHourSearchApi {

	@Autowired
	ConsultationHourApiInterface apiImplementor;

    public ResponseEntity<Object> consultationHourSearchPost(@ApiParam(value = "Search data" ,required=true ) @RequestBody ConsultationHourSearch request) {
        return apiImplementor.apiConsultationHourSearchPost(request);
    }

}
