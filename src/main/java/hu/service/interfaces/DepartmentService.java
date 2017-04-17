package hu.service.interfaces;

import java.util.List;

import hu.model.hospital.Department;

public interface DepartmentService {

	public List<Department> getDepartments();

	public Department findDepartment(Long departmentId);
	
	public Department findDepartmentWithDoctors(long departmentId);

	public Department modifyDepartment(Department department);
}
