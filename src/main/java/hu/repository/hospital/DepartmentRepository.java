package hu.repository.hospital;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.model.hospital.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByName(String name);

}
