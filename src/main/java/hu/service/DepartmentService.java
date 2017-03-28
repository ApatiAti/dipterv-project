package hu.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ConsultationHourRepository consultationHourRepository;
	
	@Autowired
	private ConsultationHourTypeRepository consultationHourTypeRepository;
	
	public List<Department> getDepartments(){
		return departmentRepository.findAll();
	}

	public List<ConsultationHour> getConsultationHour(Long departmentId) {
		List<ConsultationHour> consultationHourList = consultationHourRepository.findByDepartmentId(departmentId);
		return consultationHourList;
	}

	public void createConsultationHour(ConsultationHour consultationHour, Long departmentId) throws DepartmentNotFoundException, BasicServiceException {
		if (departmentId != null){
			Department department = departmentRepository.findOne(departmentId);
			
			if (department != null){
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

	@Transactional
	public ConsultationHour findConsultationHourWithAppointment(Long consultationHourId) {
		ConsultationHour consultationHour = consultationHourRepository.findOne(consultationHourId);
		List<Appointment> appointments = consultationHour.getAppointments();
		
		Hibernate.initialize(appointments);
		
		return consultationHour;
	}

	// TODO legújabb spring-jpadat tud Example alapján keresni
	public List<ConsultationHour> sortConsultationHour(ConsultationHourSearch searchEntity, Long departmentId) {
		Date endDate = searchEntity.getEndDate();
		Date startDate = searchEntity.getStartDate();

		List<ConsultationHour> consultationHourList = null;
		
		if (startDate == null){
			if (endDate == null){
				consultationHourList = consultationHourRepository.findByDepartmentId(departmentId);
			} else {
				// TODO kitalálni hibajelzést
			}
		} else {
			if (endDate == null){
				consultationHourList = consultationHourRepository.findByBeginDateAfterAndDepartmentId(startDate, departmentId);
			} else {
				consultationHourList = consultationHourRepository.findByBeginDateBetween(startDate, endDate);				
			}
		}
			
		return consultationHourList;
	}

	public Department findDepartment(Long departmentId) {
		return departmentRepository.findOne(departmentId);
	}

	@Transactional
	public Department modifyDepartment(Department department) {
		
		if (department != null && department.getId() != null){
			Department dbDepartment = departmentRepository.findOne(department.getId());
			
			dbDepartment.setDescription(department.getDescription());
			dbDepartment.setName(department.getName());
			dbDepartment.setPhoneNumber(department.getPhoneNumber());
			dbDepartment.setPlace(department.getPlace());
			
			return departmentRepository.save(dbDepartment);
		}
		
		return null;
	}
}
