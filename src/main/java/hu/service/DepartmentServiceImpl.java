package hu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.exception.BasicServiceException;
import hu.model.hospital.ConsultationHourType;
import hu.model.hospital.Department;
import hu.repository.hospital.DepartmentRepository;
import hu.service.interfaces.ConsultationHourService;
import hu.service.interfaces.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	ConsultationHourService consultationHourService;
		
	public List<Department> getDepartments(){
		return departmentRepository.findAll();
	}

	public Department findDepartment(Long departmentId) {
		return departmentRepository.findOne(departmentId);
	}
	
	@Transactional
	public Department findDepartmentWithDoctors(long departmentId) {
		Department department = findDepartment(departmentId);
		if (department != null){
			Hibernate.initialize(department.getEmployee());
		}
		
		return department;
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
	

	@Override
	public Map<Department, List<ConsultationHourType>> getAllDepartmentsAndTypesGet(){
		Map<Department, List<ConsultationHourType>> resultMap = new HashMap<>();
		
		List<Department> departmentIdList = departmentRepository.findAll();
		for (Department department : departmentIdList) {
			try {
				List<ConsultationHourType> chTypeList = consultationHourService.findConsultationHourTypeByDepartmentId(department.getId());
			
				resultMap.put(department, chTypeList);
			} catch (BasicServiceException e) { 
				// do nothing
			}
		}
		
		return resultMap;
	}

}
