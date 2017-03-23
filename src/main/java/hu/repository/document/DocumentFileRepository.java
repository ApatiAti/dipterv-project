package hu.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.model.document.DocumentFile;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, Long> {

}
