package hu.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.document.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

	DocumentType findByTypeName(String name);

}
