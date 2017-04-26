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
import hu.exception.security.AuthorizationException;
import hu.model.hospital.Appointment;
import hu.model.hospital.ConsultationHour;
import hu.model.user.User;
import hu.repository.document.DocumentFileAppointmentRepository;
import hu.repository.hospital.AppointmentRepository;
import hu.repository.user.UserRepository;
import hu.service.interfaces.AppointmentService;
import hu.service.interfaces.ConsultationHourService;
import hu.service.interfaces.DocumentService;
import hu.service.interfaces.security.SecurityService;
import hu.web.util.CalendarUtil;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	DocumentFileAppointmentRepository documentFileAppointmentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	ConsultationHourService consultationHourService;
	
	@Autowired
	SecurityService securityService;
	
	@Override
	@Transactional
	public Appointment buildNewAppointment(Long consultationHourId, String currentUserName) throws ConsultationHourNotFound, UserNotFoundException, AlreadyHaveAppointmentException, BasicServiceException {
		ConsultationHour consultationHour = consultationHourService.findConsultationHour(consultationHourId);
		
		consultationHourService.validateConsultationHour(consultationHour);
		
		User currentUser = userRepository.findByUsername(currentUserName);
		
		if (currentUser == null){
			throw new UserNotFoundException();
		}
		
		Hibernate.initialize(consultationHour.getAppointments());
		
		List<Appointment> appointments = consultationHour.getAppointments();
		
		if (appointments != null && !appointments.isEmpty()){
			for (Appointment appointmentItem : appointments) {
				if (appointmentItem.getPatient().getId().equals(currentUser.getId())){
					throw new AlreadyHaveAppointmentException(consultationHour.getDepartment().getId());
				}
			}
		}
		
		Appointment buildAppointment = new Appointment();
		buildAppointment.setConsultationHour(consultationHour);
		
		return buildAppointment;
	}


	@Override
	@Transactional
	public void saveAppointment(Appointment appointment, Long consultationHourId, String currentUserName) throws ConsultationHourNotFound, UserNotFoundException, BasicServiceException {
		User currentUser = userRepository.findByUsername(currentUserName);
		
		if (currentUser == null){
			throw new UserNotFoundException();
		}
		
		saveAppointment(appointment, consultationHourId, currentUser);
	}
	
	@Override
	@Transactional
	public void saveAppointment(String complaints, long consultationHourId, long userId) throws UserNotFoundException, ConsultationHourNotFound, BasicServiceException {
		Appointment appointment = new Appointment();
		appointment.setComplaints(complaints);
		
		User currentUser = userRepository.findOne(userId);
		
		if (currentUser == null){
			throw new UserNotFoundException();
		}
		
		saveAppointment(appointment, consultationHourId, currentUser);
		
	}

	@Transactional
	private void saveAppointment(Appointment appointment, long consultationHourId, User user) throws ConsultationHourNotFound, BasicServiceException {
		ConsultationHour consultationHour = consultationHourService.findConsultationHour(consultationHourId);
		
		consultationHourService.validateConsultationHour(consultationHour);
		
		appointment.setConsultationHour(consultationHour);
		appointment.setPatient(user);
		
		appointmentRepository.save(appointment);
	}
	
	@Override
	public List<Appointment> getAppointmentByUsername(String currentUserName) {
		return appointmentRepository.findByPatientUsername(currentUserName);	
	}

	@Override
	public Appointment findAppointmentById(Long appointmentId) throws AuthorizationException {
		Appointment appointment = appointmentRepository.findOne(appointmentId);
		securityService.authorizeOwner(appointment);
		return appointment;
	}

	@Override
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
	@Override
	public void modifyAppointment(Appointment editAppointment) throws BasicServiceException {
		Appointment oldAppointment = appointmentRepository.findOne(editAppointment.getId());
			
		if (oldAppointment != null){
			oldAppointment.setComplaints(editAppointment.getComplaints());
			return;
		} 
		
		throw new BasicServiceException("Nem létezik a megadott foglalás");
	}

	@Transactional
	@Override
	public void modifyAppointment(long appointmentId, String complaints) throws BasicServiceException, AuthorizationException {
		Appointment oldAppointment = appointmentRepository.findOne(appointmentId);
		
		securityService.authorizeOwner(oldAppointment);
		
		if (oldAppointment != null){
			oldAppointment.setComplaints(complaints);
			return;
		} 
		
		throw new BasicServiceException("Nem létezik a megadott foglalás");
	}
	


	@Override
	@Transactional	
	public void deleteAppointment(Long appointmentId) throws BasicServiceException, AuthorizationException {
		Appointment appointment = appointmentRepository.findOne(appointmentId);
		ConsultationHour consultationHour = appointment.getConsultationHour();
		
		securityService.authorizeOwner(appointment);
		
		
		if (CalendarUtil.beforeNotNull(consultationHour.getBeginDate(), new Date())){
			throw new BasicServiceException("Nem törölhető foglalás a rendelési idő megkezdése után.");
		} else {
			int count = documentFileAppointmentRepository.countByAppointmentId(appointmentId);
			if (count > 0){
				throw new BasicServiceException("Nem törölhető foglalás mert már tartozik hozzá feltöltött dokumentum!");
			}
		}
		
		appointmentRepository.delete(appointmentId);
	}


	@Override
	public List<Appointment> getAppointmentByUserId(long patientId) throws AuthorizationException {
		securityService.authorizeOwnerByUserId(patientId);
		return appointmentRepository.findByPatientId(patientId);
	}
	
	

	
}
