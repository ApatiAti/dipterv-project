package hu.repository.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.hospital.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
