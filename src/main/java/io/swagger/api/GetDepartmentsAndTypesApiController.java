package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import hu.swagger.interfaces.DepartmentApiInterface;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-29T11:37:00.219Z")

@Controller
public class GetDepartmentsAndTypesApiController implements GetDepartmentsAndTypesApi {

	@Autowired
	DepartmentApiInterface apiImplementor;

    public ResponseEntity<Object> getDepartmentsAndTypesPost() {
        return apiImplementor.apiGetDepartmentsAndTypesGet();
    }

}
