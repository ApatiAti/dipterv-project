package hu.swagger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.model.hospital.ConsultationHour;
import hu.model.hospital.ConsultationHourType;
import hu.service.interfaces.AppointmentService;
import hu.service.interfaces.ConsultationHourService;
import hu.service.interfaces.DepartmentService;
import hu.swagger.interfaces.ApiImplementor;
import hu.swagger.util.ApiErrorUtil;
import hu.swagger.util.ApiHospitalMapper;
import io.swagger.model.Appointment;
import io.swagger.model.ConsultationHourSearch;
import io.swagger.model.Error;

@Service
public class ApiImplementorImp implements ApiImplementor {

	private static final Logger logger = LoggerFactory.getLogger(ApiImplementorImp.class);
	
	@Autowired
	AppointmentService appointmentService;
	@Autowired
	ConsultationHourService consultationHourService;
	@Autowired
	DepartmentService departmentService;
	
	
	
	@Override
	public ResponseEntity<Object> apiAppointmentDelete(Appointment request) {
		logger.debug("call apiAppointmentDelete. Parameters  Appointment = " + request.toString());
		try {
			appointmentService.deleteAppointment(request.getAppointmentId());
			
			logger.info("Sikeres Appointment törlés");
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e, "Sikertelen Appointment törlés. Id : " + request.getAppointmentId());
		}
	}

	@Override
	public ResponseEntity<Object> apiAppointmentGet(Long appointmentId) {
		logger.debug("call apiAppointmentGet. Parameters  appointmentId = " + appointmentId.toString());
		return handlingServiceCall("Sikeres Appointment lekérdezés", "Sikertelen Appointment lekérdezés. Id : " + appointmentId,
				() -> { 
					hu.model.hospital.Appointment appointment = appointmentService.findAppointmentById(appointmentId);
					return ApiHospitalMapper.mapAppoinmentToApi(appointment);
				}
				);
	}


	@Override
	public ResponseEntity<Object> apiAppointmentListGet() {
		logger.debug("call apiAppointmentListGet. No Parameters");
		return handlingServiceCall("Sikeres Appointment lista lekérdezés"
				, "Sikertelen Appointment lista lekérdezés.",
				() -> {
					List<hu.model.hospital.Appointment> appointment = appointmentService.getAppointmentByLoggedUserId();
					return ApiHospitalMapper.mapAppoinmentListToApi(appointment);
					}
				);
	}

	@Override
	public ResponseEntity<Object> apiAppointmentPost(final Appointment request) {
		logger.debug("call apiAppointmentPost. Parameters  Appointment = " + request.toString());
		return handlingServiceCall("Sikeres Appointment lista lekérdezés"
				, "Sikertelen Appointment lista lekérdezés.",
				new Callable<Object>() {
					@Override
					public Object call() throws Exception {
//						TODO 
//						appointmentService.saveAppointment(request.getComplaints(), request.getConsultationHourId(), request.getUserId());
						return null;
					}
				}
			); 
	}

	@Override
	public ResponseEntity<Object> apiAppointmentPut(Appointment request) {
		logger.debug("call apiAppointmentPut. Parameters  Appointment = " + request.toString());
		return handlingServiceCall("Sikeres Appoinment módosítás"
				, "Sikertelen Appoinment módosítás."
				, new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						appointmentService.modifyAppointment(request.getAppointmentId(), request.getComplaints());
						return null;
					}
				});
	}

	@Override
	public ResponseEntity<Object> apiConsultationHourSearchPost(ConsultationHourSearch request) {
		logger.debug("call apiConsultationHourSearchPost. Parameters  ConsultationHourSearch = " + request.toString());
		return handlingServiceCall("Sikeres ConsultationHour keresés"
				, "Sikertelen ConsultationHour keresés"
				, new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						hu.model.hospital.dto.ConsultationHourSearch seachEntity = ApiHospitalMapper.mapConsultationHourSearchFromApi(request);
						
						List<ConsultationHour> consultationHourList = consultationHourService.sortConsultationHour(seachEntity, request.getDepartmentName());
						
						return ApiHospitalMapper.mapConsultationHourToApi(consultationHourList);
					}
				});
	}

	@Override
	public ResponseEntity<Object> apiGetDepartmentsAndTypesGet() {
		logger.debug("call apiGetDepartmentsAndTypesGet. No Parameters");
		return handlingServiceCall("Sikeres Appointment lista lekérdezés"
				, "Sikertelen Appointment lista lekérdezés."
				, () -> {
					Map<hu.model.hospital.Department, List<ConsultationHourType>> departmentAndTypes = departmentService.getAllDepartmentsAndTypesGet();
					return ApiHospitalMapper.mapDepartmentsAndConsultationHourTypeMapToApi(departmentAndTypes);
				}
				);
	}


	private ResponseEntity<Object> handlingServiceCall(String succesLog, String errorLog, Callable<Object> method){
		try{
			Object result = method.call();
			logger.info(succesLog);
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e){
			return exceptionHandling(e, errorLog);
		}
	}
	
	private ResponseEntity<Object> exceptionHandling(Exception e, String logMessage) {
		Error error = ApiErrorUtil.createErrorFromException(e);
		
		logger.error(logMessage , e);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	
}
