package hu.repository.hospital;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hu.model.hospital.ConsultationHour;

public interface ConsultationHourRepository extends JpaRepository<ConsultationHour, Long> {

	
	@Query("SELECT ch from ConsultationHour ch JOIN FETCH ch.department JOIN FETCH ch.type where 1 = 1"
			+ " and (:departmentId is null or ch.department.id = :departmentId)"
			+ " and (:startDate is null or ch.beginDate = :startDate)" 
			+ " and (:endDate is null or ch.endDate = :endDate)"
			+ " and (:typeId is null or ch.type.id = :typeId)"
			)
	List<ConsultationHour> findByDepartmentId(
			@Param(value = "departmentId") Long departmentId
			, @Param(value = "startDate") Date startDate
			, @Param(value = "endDate") Date endDate
			, @Param(value = "typeId") Long typeId);

	List<ConsultationHour> findByDepartmentId(Long departmentId);
}
