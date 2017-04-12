package hu.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.document.DocumentTypeToConsultationHourType;

public interface DocumentTypeToConsultationHourTypeRepository
		extends JpaRepository<DocumentTypeToConsultationHourType, Long> {

}
