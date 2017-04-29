package hu.swagger.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.springframework.util.CollectionUtils;

import hu.model.hospital.ConsultationHour;
import hu.model.hospital.ConsultationHourType;
import io.swagger.model.ConsultationHourSearch;
import io.swagger.model.Department;

public class ApiHospitalMapper {

	public static hu.model.hospital.dto.ConsultationHourSearch mapConsultationHourSearchFromApi(
			ConsultationHourSearch request) {
		Date startDate = request.getBeginDate().toDate();
		Date endDate = request.getEndDate().toDate();
		
		hu.model.hospital.dto.ConsultationHourSearch seachEntity = new hu.model.hospital.dto.ConsultationHourSearch();
		seachEntity.setStartDate(startDate);
		seachEntity.setEndDate(endDate);
		seachEntity.setChTypeId(request.getTypeId());
		return seachEntity;
	}
	
	public static List<io.swagger.model.ConsultationHour> mapConsultationHourToApi(List<ConsultationHour> consultationHourList) {
		if (!CollectionUtils.isEmpty(consultationHourList)){
			List<io.swagger.model.ConsultationHour> apiList = new ArrayList<>();
			
			for (ConsultationHour consultationHour : consultationHourList) {
				DateTime beginDateTime = convertDateToDateTime(consultationHour.getBeginDate());
				DateTime endDateTime = convertDateToDateTime(consultationHour.getEndDate());
				
				io.swagger.model.ConsultationHour apiObject = new io.swagger.model.ConsultationHour();
				apiObject.setConsultationHourId(consultationHour.getId());
				apiObject.setTypeId(consultationHour.getType().getId());
				apiObject.setBeginDate(beginDateTime);
				apiObject.setEndDate(endDateTime);
				apiObject.setRoom(consultationHour.getRoom());
				apiObject.setDoctorsName(consultationHour.getDoctor().getPersonalData().getName().getFullName());
				apiObject.setMaxPatientCount(consultationHour.getMaxNumberOfPatient());
				apiObject.setCurrentPatientCount(consultationHour.getNumberOfAppointment());
				
				apiList.add(apiObject);
			}
			
			return apiList;
		}
		return null;
	}

	public static DateTime convertDateToDateTime(Date beginDate) {
		DateTime beginDateTime = beginDate != null ? new DateTime(beginDate) : null;
		return beginDateTime;
	}

	public static List<io.swagger.model.Appointment> mapAppoinmentListToApi(List<hu.model.hospital.Appointment> appointmentList) {
		if (CollectionUtils.isEmpty(appointmentList)){
			return null;
		}
		
		List<io.swagger.model.Appointment> apiList = new ArrayList<>();
		
		for (hu.model.hospital.Appointment appointment : appointmentList) {
			apiList.add(mapAppoinmentToApi(appointment));
		}
		
		return apiList;
		
	}
	
	public static io.swagger.model.Appointment mapAppoinmentToApi(hu.model.hospital.Appointment appointment) {
		if (appointment == null){
			return null;
		}
		
		ConsultationHour consultationHour = appointment.getConsultationHour();
		DateTime beginDateTime = convertDateToDateTime(consultationHour.getBeginDate());
		DateTime endDateTime = convertDateToDateTime(consultationHour.getEndDate());
		
		io.swagger.model.Appointment apiObject = new io.swagger.model.Appointment();
		apiObject.setAppointmentId(appointment.getId());
		apiObject.setBeginDate(beginDateTime);
		apiObject.setEndDate(endDateTime);
		apiObject.setRoom(consultationHour.getRoom());
		apiObject.setDoctorsName(consultationHour.getDoctor().getPersonalData().getName().getFullName());
		apiObject.setComplaints(appointment.getComplaints());
		apiObject.setPatientName(appointment.getPatient().getUsername());
		apiObject.setConsultationHourId(consultationHour.getId());
		apiObject.setDepartmentId(consultationHour.getDepartment().getId());
		apiObject.setConsultationTypeId(consultationHour.getType().getId());		
		
		return apiObject;
	}

	public static List<io.swagger.model.Department> mapDepartmentsAndConsultationHourTypeMapToApi(Map<hu.model.hospital.Department, List<ConsultationHourType>> departmentAndTypes) {
		if (CollectionUtils.isEmpty(departmentAndTypes)){
			return null;
		}
		List<io.swagger.model.Department> apiDepartmentList = new ArrayList<>();
		
		for (Entry<hu.model.hospital.Department, List<ConsultationHourType>> entry : departmentAndTypes.entrySet()) {
 			io.swagger.model.Department apiDepartment = new Department();
			apiDepartment.setDepartmentId(entry.getKey().getId());
			apiDepartment.setDepartmentName(entry.getKey().getName());
			
			
			apiDepartment.setConsultationHourType( mapConsultationHourTypeListToApi( entry.getValue()));
			
			apiDepartmentList.add(apiDepartment);
		}
		return apiDepartmentList;
	}

	private static List<io.swagger.model.ConsultationHourType> mapConsultationHourTypeListToApi(List<hu.model.hospital.ConsultationHourType> consultationHourTypeList){
		List<io.swagger.model.ConsultationHourType> apiConsultationHourTypeList = new ArrayList<>();
		
		if (CollectionUtils.isEmpty(consultationHourTypeList)){
			return apiConsultationHourTypeList;
		}
		
		for (hu.model.hospital.ConsultationHourType consultationHourType : consultationHourTypeList) {
			io.swagger.model.ConsultationHourType apiConsultationHourType = mapConsultationHourTypeToApi(
					consultationHourType);
			
			apiConsultationHourTypeList.add(apiConsultationHourType);
		}
		
		return apiConsultationHourTypeList;
	}

	public static io.swagger.model.ConsultationHourType mapConsultationHourTypeToApi(
			hu.model.hospital.ConsultationHourType consultationHourType) {
		io.swagger.model.ConsultationHourType apiConsultationHourType = new io.swagger.model.ConsultationHourType();
		apiConsultationHourType.setId(consultationHourType.getId());
		apiConsultationHourType.setName(consultationHourType.getName());
		return apiConsultationHourType;
	}




}
