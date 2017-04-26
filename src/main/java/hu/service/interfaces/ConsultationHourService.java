package hu.service.interfaces;

import java.util.List;

import hu.exception.BasicServiceException;
import hu.exception.ConsultationHourNotFound;
import hu.exception.DepartmentNotFoundException;
import hu.model.hospital.ConsultationHour;
import hu.model.hospital.ConsultationHourType;
import hu.model.hospital.dto.ConsultationHourSearch;

public interface ConsultationHourService {

	List<ConsultationHour> findConsultationHourList(Long departmentId);

	/**
	 *  Létrehoz egy új ConsultationHourt a megadott paramétereke segítségével. 
	 * @param consultationHor 	Létrehozandó ConsultationHour
	 * @param departmentId	Létrehozandó ConsultationHour-hoz tartozó Department
	 * @return
	 * @throws DepartmentNotFoundException
	 * @throws BasicServiceException
	 */
	ConsultationHour createConsultationHour(ConsultationHour consultationHour, Long departmentId) throws DepartmentNotFoundException, BasicServiceException;

	List<ConsultationHour> sortConsultationHour(ConsultationHourSearch searchEntity, Long departmentId);

	List<ConsultationHour> sortConsultationHour(ConsultationHourSearch seachEntity, String departmentName);
	
	ConsultationHour findConsultationHour(Long consultationHourId);
	
	ConsultationHour findConsultationHourWithAppointment(Long consultationHourId);

	List<ConsultationHourType> findConsultationHourTypeByDepartmentId(long departmentId) throws BasicServiceException;

	/**
	 *  Módosít egy ConsultationHour-t a megadott ConsultationHour alapján.
	 *  Módosításról email-t küld ki a felhasználóknak akiknek Appointment-jét érinti a változás
	 * @param consultationHour
	 * @return
	 * @throws BasicServiceException
	 */
	ConsultationHour modifyConsultationHour(ConsultationHour consultationHour) throws BasicServiceException;

	void validateConsultationHour(ConsultationHour consultationHour) throws ConsultationHourNotFound, BasicServiceException;


}
