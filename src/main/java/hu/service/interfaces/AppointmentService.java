package hu.service.interfaces;

import java.util.List;

import hu.exception.AlreadyHaveAppointmentException;
import hu.exception.BasicServiceException;
import hu.exception.ConsultationHourNotFound;
import hu.exception.UserNotFoundException;
import hu.model.hospital.Appointment;

public interface AppointmentService {

	/**
	 *  Ellenőrzi hogy lehetséges a megadott paraméterekkel új apppointment-et létrehzoni. Ha igen akkor létrehozza annak a vázát
	 * @param consultationHourId ConsultationHour, amihez a Appointment-et létre akarjuk hozni
	 * @param currentUserName  Épp bejelentkezett felhasználó
	 * @return  Az új appointment váza
	 * @throws ConsultationHourNotFound		Nem létezik a megadott consultationHourId-hoz entitás
	 * @throws UserNotFoundException	Nem létezik a megadott felhasználó
	 * @throws AlreadyHaveAppointmentException	Adott ConsultationHour-hoz már van Appointmentje
	 * @throws BasicServiceException
	 */
	Appointment buildNewAppointment(Long consultationHourId, String currentUserName) throws ConsultationHourNotFound,
			UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException;

	/**
	 * Appointement mentése
	 * @param appointment 	Mentendő Appointment
	 * @param consultationHourId 	ConsultationHour amihez szeretnénk menteni
	 * @param currentUserName	Épp bejelentkezett felhasználó
	 * @throws ConsultationHourNotFound		Nem létezik a megadott consultationHourId-hoz entitás
	 * @throws UserNotFoundException	Nem létezik a megadott felhasználó
	 * @throws BasicServiceException
	 */
	void saveAppointment(Appointment appointment, Long consultationHourId, String currentUserName)
			throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException;

	/**
	 * Megadott felhasználó összes Appointementjét visszaadja
	 * @param currentUserName
	 * @return
	 */
	List<Appointment> getAppointmentByUsername(String currentUserName);

	Appointment findAppointmentById(Long appointmentId);

	Appointment findAppointmentByIdAndUserName(Long appointmentId, String currentUserName) throws BasicServiceException;

	void modifyAppointment(Appointment editAppointment) throws BasicServiceException;

	void deleteAppointment(Long appointmentId) throws BasicServiceException;

}
