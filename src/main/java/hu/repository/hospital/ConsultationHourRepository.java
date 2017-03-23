package hu.repository.hospital;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.hospital.ConsultationHour;

public interface ConsultationHourRepository extends JpaRepository<ConsultationHour, Long> {

	List<ConsultationHour> findByDepartmentId(Long departmentId);

	List<ConsultationHour> findByBeginDateBetween(Date beginDate, Date endDate);

	List<ConsultationHour> findByBeginDateAfterAndDepartmentId(Date beginDate, Long departmentId);
}
