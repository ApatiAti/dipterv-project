package hu.swagger.interfaces;

import org.springframework.http.ResponseEntity;

public interface DepartmentApiInterface {

	ResponseEntity<Object> apiGetDepartmentsAndTypesGet();
	
}
