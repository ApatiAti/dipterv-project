package hu.swagger;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.model.hospital.ConsultationHourType;
import hu.service.interfaces.DepartmentService;
import hu.swagger.interfaces.DepartmentApiInterface;
import hu.swagger.util.ApiHospitalMapper;
import io.swagger.model.Department;

@Service
public class DepartmentApiImplementor extends AbstractImplementor implements DepartmentApiInterface{

	static final Logger logger = LoggerFactory.getLogger(DepartmentApiImplementor.class);
	
	@Autowired
	DepartmentService departmentService;
	
	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public ResponseEntity<Object> apiGetDepartmentsAndTypesGet() {
		logger.debug("call apiGetDepartmentsAndTypesGet. No Parameters");
		return handlingServiceCall("Sikeres Appointment lista lekérdezés"
				, "Sikertelen Appointment lista lekérdezés."
				, () -> {
					Map<hu.model.hospital.Department, List<ConsultationHourType>> departmentAndTypes = departmentService.getAllDepartmentsAndTypesGet();

					List<Department> mapDepartmentsAndConsultationHourTypeMapToApi = ApiHospitalMapper.mapDepartmentsAndConsultationHourTypeMapToApi(departmentAndTypes);
					logger.debug("Response of apiGetDepartmentsAndTypesGet call" );
					logResultCollection(mapDepartmentsAndConsultationHourTypeMapToApi);
					return mapDepartmentsAndConsultationHourTypeMapToApi;
				}
				);
	}
}
