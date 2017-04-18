package hu.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.config.TestDbConstans;
import hu.exception.AlreadyHaveAppointmentException;
import hu.exception.BasicServiceException;
import hu.exception.ConsultationHourNotFound;
import hu.exception.UserNotFoundException;
import hu.model.hospital.Appointment;
import hu.repository.hospital.AppointmentRepository;
import hu.repository.hospital.ConsultationHourRepository;
import hu.service.interfaces.AppointmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:configs/appContext.xml" ,"classpath:configs/appContext-mail.xml"})
public class AppointmentServiceIT {

	@Autowired
	AppointmentService appointmentService;
	@Autowired
	ConsultationHourRepository consultationHourRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Test
	public void buildNewAppointmentSuccess() throws ConsultationHourNotFound, UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException{
		Long consultationHourId = TestDbConstans.CONSULTATION_HOUR_ID_1;
		String patientUsername = TestDbConstans.PATIENT_1_USERNAME;
		
		Appointment appointment = appointmentService.buildNewAppointment(consultationHourId, patientUsername);
		
		assertEquals(null, appointment.getComplaints());
		assertEquals(consultationHourId, appointment.getConsultationHour().getId());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void buildNewAppointmentNoUser() throws ConsultationHourNotFound, UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException{
		Long consultationHourId = TestDbConstans.CONSULTATION_HOUR_ID_1;
		String patientUsername = TestDbConstans.NOT_EXISTING_PATIENT_USERNAME;
		
		appointmentService.buildNewAppointment(consultationHourId, patientUsername);
	}
	
	@Test(expected = ConsultationHourNotFound.class)
	public void buildNewAppointmentNoConsultationHour() throws ConsultationHourNotFound, UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException{
		Long consultationHourId = TestDbConstans.NOT_EXISTING_CONSULTATION_HOUR_ID;
		String patientUsername = TestDbConstans.PATIENT_1_USERNAME;
		
		appointmentService.buildNewAppointment(consultationHourId, patientUsername);
	}
	
//	@Test(expected = AlreadyHaveAppointmentException.class)
	@Test
	public void buildNewAppointmentAlreadyHaveAppoiment() throws ConsultationHourNotFound, UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException{
		Long consultationHourId = TestDbConstans.CONSULTATION_HOUR_ID_0;
		String patientUsername = TestDbConstans.PATIENT_1_USERNAME;
		
		appointmentService.buildNewAppointment(consultationHourId, patientUsername);
	}

	@Test(expected = BasicServiceException.class)
	public void buildNewAppointmentWrongDate() throws ConsultationHourNotFound, UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException{
		Long consultationHourId = TestDbConstans.CONSULTATION_HOUR_ID_2;
		String patientUsername = TestDbConstans.PATIENT_1_USERNAME;
		
		appointmentService.buildNewAppointment(consultationHourId, patientUsername);
	}
	
	@Test
	public void saveAppointmentSuccess() throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException {
		String patientUsername = TestDbConstans.PATIENT_2_USERNAME;
		Long consultationHourId = TestDbConstans.CONSULTATION_HOUR_ID_1;
		
		String complaints = TestDbConstans.APPOINTMENT_NEW_COMPLAINT;
		
		Appointment appointment = new Appointment();
		appointment.setComplaints(complaints);
		appointment.setConsultationHour(null);
		
		
		appointmentService.saveAppointment(appointment, consultationHourId, patientUsername);
		
		List<Appointment> appointmentList = appointmentRepository.findByPatientUsername(patientUsername);
		Optional<Appointment> findFirst = appointmentList.stream().filter(appoinment -> consultationHourId.equals(appoinment.getConsultationHour().getId())).findFirst();
		
		assertTrue(findFirst.isPresent());
		Appointment savedAppointment = findFirst.get();
		
		assertEquals(complaints, savedAppointment.getComplaints());
		assertNotNull(savedAppointment.getConsultationHour());
		assertEquals(patientUsername, savedAppointment.getPatient().getUsername());
		
	}

	@Test(expected = ConsultationHourNotFound.class)
	public void saveAppointmentNoConsultationHour() throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException {
		String patientUsername = TestDbConstans.PATIENT_2_USERNAME;
		Long consultationHourId = TestDbConstans.NOT_EXISTING_CONSULTATION_HOUR_ID;
		
		String complaints = TestDbConstans.APPOINTMENT_NEW_COMPLAINT;
		
		Appointment appointment = new Appointment();
		appointment.setComplaints(complaints);
		appointment.setConsultationHour(null);
		
		
		appointmentService.saveAppointment(appointment, consultationHourId, patientUsername);
		
	}
	
	@Test(expected = UserNotFoundException.class)
	public void saveAppointmentNoUser() throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException {
		String patientUsername = TestDbConstans.NOT_EXISTING_PATIENT_USERNAME;
		Long consultationHourId = TestDbConstans.CONSULTATION_HOUR_ID_1;
		
		String complaints = TestDbConstans.APPOINTMENT_NEW_COMPLAINT;
		
		Appointment appointment = new Appointment();
		appointment.setComplaints(complaints);
		appointment.setConsultationHour(null);
		
		
		appointmentService.saveAppointment(appointment, consultationHourId, patientUsername);
	}
	
	@Test(expected = BasicServiceException.class)
	public void saveAppointmentWrongDate() throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException {
		String patientUsername = TestDbConstans.PATIENT_2_USERNAME;
		Long consultationHourId = TestDbConstans.CONSULTATION_HOUR_ID_2;
		
		String complaints = TestDbConstans.APPOINTMENT_NEW_COMPLAINT;
		
		Appointment appointment = new Appointment();
		appointment.setComplaints(complaints);
		appointment.setConsultationHour(null);
		
		
		appointmentService.saveAppointment(appointment, consultationHourId, patientUsername);
	}
	
	/**
	 * Appointement mentése
	 * @param appointment 	Mentendő Appointment
	 * @param consultationHourId 	ConsultationHour amihez szeretnénk menteni
	 * @param currentUserName	Épp bejelentkezett felhasználó
	 * @throws ConsultationHourNotFound		Nem létezik a megadott consultationHourId-hoz entitás
	 * @throws UserNotFoundException	Nem létezik a megadott felhasználó
	 * @throws BasicServiceException
	 */
//	void saveAppointment(Appointment appointment, Long consultationHourId, String currentUserName)
//			throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException;

}
