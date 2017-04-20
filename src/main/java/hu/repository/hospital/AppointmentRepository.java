package hu.repository.hospital;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.hospital.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPatientUsername(String currentUserName);

	List<Appointment> findByPatientId(long patientId);

}
