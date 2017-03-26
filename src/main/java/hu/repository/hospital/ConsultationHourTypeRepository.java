package hu.repository.hospital;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.model.hospital.ConsultationHourType;

@Repository
public interface ConsultationHourTypeRepository extends JpaRepository<ConsultationHourType, Long> {

	List<ConsultationHourTypeRepository> findByDepartmentId(Long departmentId);
}
