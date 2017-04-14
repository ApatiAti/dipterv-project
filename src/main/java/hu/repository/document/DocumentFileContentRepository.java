package hu.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.model.document.DocumentFileContent;

public interface DocumentFileContentRepository extends JpaRepository<DocumentFileContent, Long> {

}
