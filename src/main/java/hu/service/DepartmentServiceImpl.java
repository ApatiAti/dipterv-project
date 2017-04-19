package hu.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.model.hospital.Department;
import hu.repository.hospital.DepartmentRepository;
import hu.service.interfaces.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
		
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

}
