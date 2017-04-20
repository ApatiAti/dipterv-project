package hu.service.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.exception.BasicServiceException;
import hu.model.hospital.ConsultationHourType;
import hu.model.hospital.Department;

public interface DepartmentService {

	public List<Department> getDepartments();

	public Department findDepartment(Long departmentId);
	
	public Department findDepartmentWithDoctors(long departmentId);

	public Department modifyDepartment(Department department);

	Map<Department, List<ConsultationHourType>> getAllDepartmentsAndTypesGet();
}
