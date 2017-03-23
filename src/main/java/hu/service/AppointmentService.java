package hu.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.exception.AlreadyHaveAppointmentException;
import hu.exception.BasicServiceException;
import hu.exception.ConsultationHourNotFound;
import hu.exception.UserNotFoundException;
import hu.model.hospital.Appointment;
import hu.model.hospital.ConsultationHour;
import hu.model.user.User;
import hu.repository.hospital.AppointmentRepository;
import hu.repository.hospital.ConsultationHourRepository;
import hu.repository.user.UserRepository;

@Service
public class AppointmentService {

	@Autowired
	ConsultationHourRepository consultationHourRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	DocumentService documentService;
	
	@Transactional
	public Appointment buildNewAppointment(Long consultationHourId, String currentUserName) throws ConsultationHourNotFound, UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException {
		ConsultationHour consultationHour = consultationHourRepository.findOne(consultationHourId);
		
		validateConsultationHour(consultationHour);
		
		User currentUser = userRepository.findByUsername(currentUserName);
		
		if (currentUser == null){
			throw new UserNotFoundException();
		}
		
		Hibernate.initialize(consultationHour.getAppointments());
		
		List<Appointment> appointments = consultationHour.getAppointments();
		
		if (appointments != null && !appointments.isEmpty()){
			for (Appointment appointmentItem : appointments) {
				if (appointmentItem.getPatient().getId().equals(currentUser.getId())){
					throw new AlreadyHaveAppointmentException();
				}
			}
		}
		
		
		Appointment buildAppointment = new Appointment();
		buildAppointment.setConsultationHour(consultationHour);
		
		return buildAppointment;
	}

	// TODO consultationHourService-be átrakni
	public void validateConsultationHour(ConsultationHour consultationHour)
			throws ConsultationHourNotFound, BasicServiceException {
		if (consultationHour == null){
			throw new ConsultationHourNotFound();
		}
		if (consultationHour.getBeginDate().before(new Date())){
			throw new BasicServiceException("Nen lehet időpontot foglalni a kezdés után!");
		}
		if (consultationHour.getNumberOfAppointment() >= consultationHour.getMaxNumberOfPatient()){
			throw new BasicServiceException("Időpont betelt!");
		}
	}

	@Transactional
	public void saveAppointment(Appointment appointment, Long consultationHourId, String currentUserName) throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException {
		
		ConsultationHour consultationHour = consultationHourRepository.findOne(consultationHourId);
		
		validateConsultationHour(consultationHour);
		
		User currentUser = userRepository.findByUsername(currentUserName);
		
		if (currentUser == null){
			throw new UserNotFoundException();
		}
		
		
		appointment.setConsultationHour(consultationHour);
		appointment.setPatient(currentUser);
		
		appointmentRepository.save(appointment);
		
	}

	public List<Appointment> getAppintmentByUsername(String currentUserName) {
		return appointmentRepository.findByPatientUsername(currentUserName);	
	}

	public Appointment findAppointmentById(Long appointmentId) {
		return appointmentRepository.findOne(appointmentId);
	}

	public Appointment findAppointmentByIdAndUserName(Long appointmentId, String currentUserName) throws BasicServiceException {
		Appointment appointment = appointmentRepository.findOne(appointmentId);
		
		if (appointment != null){
			String username = appointment.getPatient().getUsername();

			if (username.equals(currentUserName)){
				return appointment;
			} else {
				throw new BasicServiceException("Csak a saját foglalás módosítható");
			}
		} 
	
		throw new BasicServiceException("Megadott foglalás nem létezik");
	}

	@Transactional
	public void modifyAppointment(Appointment editAppointment) throws BasicServiceException {
		Appointment oldAppointment = appointmentRepository.findOne(editAppointment.getId());
		
		if (oldAppointment != null){
			oldAppointment.setComplaints(editAppointment.getComplaints());
			return;
		} 
		
		throw new BasicServiceException("Nem létezik a megadott foglalás");
	}

	@Transactional	
	public void deleteAppointment(Long appointmentId) {
		appointmentRepository.delete(appointmentId);
	}

	
}
