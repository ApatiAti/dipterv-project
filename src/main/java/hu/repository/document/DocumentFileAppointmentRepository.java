package hu.repository.document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.model.document.DocumentFileAppointment;

@Repository
public interface DocumentFileAppointmentRepository  extends JpaRepository<DocumentFileAppointment, Long> {

	DocumentFileAppointment findByAppointmentIdAndDocumentId(Long appointmentId, Long documentId);

	List<DocumentFileAppointment> findByAppointmentId(Long appointmentId);

	List<DocumentFileAppointment> findByAppointmentPatientId(Long currentUserID);
}
