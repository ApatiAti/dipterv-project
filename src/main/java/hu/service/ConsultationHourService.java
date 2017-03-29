package hu.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import hu.exception.BasicServiceException;
import hu.exception.DepartmentNotFoundException;
import hu.model.hospital.Appointment;
import hu.model.hospital.ConsultationHour;
import hu.model.hospital.ConsultationHourType;
import hu.model.hospital.Department;
import hu.model.hospital.dto.ConsultationHourSearch;
import hu.repository.hospital.ConsultationHourRepository;
import hu.repository.hospital.ConsultationHourTypeRepository;
import hu.repository.hospital.DepartmentRepository;

@Service
public class ConsultationHourService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ConsultationHourRepository consultationHourRepository;

	@Autowired
	private ConsultationHourTypeRepository consultationHourTypeRepository;

	public List<ConsultationHour> findConsultationHourList(Long departmentId) {
		List<ConsultationHour> consultationHourList = consultationHourRepository.findByDepartmentId(departmentId);
		return consultationHourList;
	}

	public void createConsultationHour(ConsultationHour consultationHour, Long departmentId)
			throws DepartmentNotFoundException, BasicServiceException {
		if (departmentId != null) {
			Department department = departmentRepository.findOne(departmentId);

			if (department != null) {
				ConsultationHourType type = consultationHour.getType();
				if (type != null) {
					type = consultationHourTypeRepository.findOne(type.getId());
					consultationHour.setDepartment(department);
					consultationHourRepository.save(consultationHour);
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
}
