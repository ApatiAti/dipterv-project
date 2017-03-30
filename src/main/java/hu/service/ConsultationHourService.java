
package hu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import hu.exception.BasicServiceException;
import hu.exception.ConsultationHourNotFound;
import hu.exception.DepartmentNotFoundException;
import hu.model.hospital.Appointment;
import hu.model.hospital.ConsultationHour;
import hu.model.hospital.ConsultationHourType;
import hu.model.hospital.Department;
import hu.model.hospital.dto.ConsultationHourSearch;
import hu.model.user.User;
import hu.repository.hospital.ConsultationHourRepository;
import hu.repository.hospital.ConsultationHourTypeRepository;
import hu.repository.hospital.DepartmentRepository;
import hu.util.EmailType;

@Service
public class ConsultationHourService {

	private static final Logger logger = Logger.getLogger(ConsultationHourService.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ConsultationHourRepository consultationHourRepository;

	@Autowired
	private ConsultationHourTypeRepository consultationHourTypeRepository;

	@Autowired
	private MailService mailService;

	public List<ConsultationHour> findConsultationHourList(Long departmentId) {
		List<ConsultationHour> consultationHourList = consultationHourRepository.findByDepartmentId(departmentId);
		return consultationHourList;
	}

	public ConsultationHour createConsultationHour(ConsultationHour consultationHour, Long departmentId)
			throws DepartmentNotFoundException, BasicServiceException {
		if (departmentId != null) {
			Department department = departmentRepository.findOne(departmentId);

			if (department != null) {
				ConsultationHourType type = consultationHour.getType();
				if (type != null) {
					type = consultationHourTypeRepository.findOne(type.getId());
					consultationHour.setDepartment(department);
					return consultationHourRepository.save(consultationHour);
				} else {
					throw new BasicServiceException("Nem létezik a megadott rendelési idő típus");
				}
			} else {
				throw new DepartmentNotFoundException(departmentId);
			}
		} else {
			throw new BasicServiceException("Nincs megadva megfelelő adat");
		}
	}

	public List<ConsultationHour> sortConsultationHour(ConsultationHourSearch searchEntity, Long departmentId) {
		if (searchEntity != null) {
			return consultationHourRepository.findByDepartmentId(departmentId, searchEntity.getStartDate(),
					searchEntity.getEndDate(), searchEntity.getChTypeId());
		}

		return new ArrayList<ConsultationHour>();
	}

	public ConsultationHour findConsultationHour(Long consultationHourId) {
		return consultationHourRepository.findOne(consultationHourId);
	}

	// transsactional a Hibernate.initialize miatt kell
	@Transactional
	public ConsultationHour findConsultationHourWithAppointment(Long consultationHourId) {
		ConsultationHour consultationHour = consultationHourRepository.findOne(consultationHourId);
		List<Appointment> appointments = consultationHour.getAppointments();

		Hibernate.initialize(appointments);

		return consultationHour;
	}

	public List<ConsultationHourType> findConsultationHourTypeByDepartmentId(long departmentId)
			throws BasicServiceException {
		List<ConsultationHourType> types = consultationHourTypeRepository.findByDepartmentId(departmentId);

		if (CollectionUtils.isEmpty(types)) {
			throw new BasicServiceException("ConsultationHourTypes not fount for Department");
		}

		return types;
	}

	@Transactional
	public ConsultationHour modifyConsultationHour(ConsultationHour consultationHour) throws BasicServiceException {
		ConsultationHour dbConsultationHour = consultationHourRepository.findOne(consultationHour.getId());

		if (dbConsultationHour != null) {
			dbConsultationHour.setBeginDate(consultationHour.getBeginDate());
			dbConsultationHour.setEndDate(consultationHour.getEndDate());
			dbConsultationHour.setRoom(consultationHour.getRoom());
			dbConsultationHour.setMaxNumberOfPatient(consultationHour.getMaxNumberOfPatient());
			dbConsultationHour.setType(consultationHour.getType());

			notifyAllPatientAfterModification(dbConsultationHour);

			dbConsultationHour = consultationHourRepository.save(dbConsultationHour);
			logger.info("Sikeres rendelési idő módosítás");

			return dbConsultationHour;
		} else {
			throw new BasicServiceException("Nincs ilyen rendelési idő");
		}
	}

	private void notifyAllPatientAfterModification(ConsultationHour consultationHour) throws BasicServiceException {
		List<Appointment> appointments = consultationHour.getAppointments();

		if (!CollectionUtils.isEmpty(appointments)) {
			for (Appointment appointment : appointments) {
				Map<String, Object> emailModel = new HashMap<>();
				User patient = appointment.getPatient();

				emailModel.put("name", patient.getPersonalData().getName());
				emailModel.put("beginDate", consultationHour.getBeginDate());
				emailModel.put("endDate", consultationHour.getEndDate());

				String email = patient.getEmail();
				mailService.sendTemplateEnginedMail(email, EmailType.CONSULTATION_HOUR_MODIFIED, emailModel);
			}
		}
	}

	public void validateConsultationHour(ConsultationHour consultationHour)
			throws ConsultationHourNotFound, BasicServiceException {
		if (consultationHour == null) {
			throw new ConsultationHourNotFound();
		}
		if (consultationHour.getBeginDate().before(new Date())) {
			throw new BasicServiceException("Nen lehet időpontot foglalni a kezdés után!");
		}
		if (consultationHour.getNumberOfAppointment() >= consultationHour.getMaxNumberOfPatient()) {
			throw new BasicServiceException("Időpont betelt!");
		}
	}
}
