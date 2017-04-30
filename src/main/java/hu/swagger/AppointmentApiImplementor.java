package hu.swagger;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.service.interfaces.AppointmentService;
import hu.swagger.interfaces.AppointmentApiInterface;
import hu.swagger.util.ApiHospitalMapper;
import io.swagger.model.Appointment;

@Service
public class AppointmentApiImplementor extends AbstractImplementor implements AppointmentApiInterface {

	static final Logger logger = LoggerFactory.getLogger(AppointmentApiImplementor.class);
	
	@Autowired
	AppointmentService appointmentService;
	
	@Override
	public Logger getLogger(){
		return logger;
	}
	
	@Override
	public ResponseEntity<Object> apiAppointmentDelete(Long appointmentId) {
		logger.debug("call apiAppointmentDelete. Parameters  AppointmentId = " + appointmentId.toString());
		try {
			appointmentService.deleteAppointment(appointmentId);
			
			logger.info("Sikeres Appointment törlés");
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e, "Sikertelen Appointment törlés. Id : " + appointmentId);
		}
	}

	@Override
	public ResponseEntity<Object> apiAppointmentGet(Long appointmentId) {
		logger.debug("call apiAppointmentGet. Parameters  appointmentId = " + appointmentId.toString());
		return handlingServiceCall("Sikeres Appointment lekérdezés", "Sikertelen Appointment lekérdezés. Id : " + appointmentId,
				() -> { 
					hu.model.hospital.Appointment appointment = appointmentService.findAppointmentById(appointmentId);
					Appointment mapAppoinmentToApi = ApiHospitalMapper.mapAppoinmentToApi(appointment);
					logger.debug("apiAppointmentGet response");
					logResultObject(mapAppoinmentToApi);
					return mapAppoinmentToApi;
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
					List<Appointment> mapAppoinmentListToApi = ApiHospitalMapper.mapAppoinmentListToApi(appointment);
					logger.debug("apiAppointmentListGet response");
					logResultCollection(mapAppoinmentListToApi);
					
					return mapAppoinmentListToApi;
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
						hu.model.hospital.Appointment savedAppointment = appointmentService.saveAppointment(request.getComplaints(), request.getConsultationHourId());
						Appointment mapAppoinmentToApi = ApiHospitalMapper.mapAppoinmentToApi(savedAppointment);
						logResultObject(mapAppoinmentToApi);
						return mapAppoinmentToApi;
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
	
}
