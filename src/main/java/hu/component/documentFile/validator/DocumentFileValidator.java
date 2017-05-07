package hu.component.documentFile.validator;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import hu.exception.BasicServiceException;
import hu.model.document.DocumentType;
import hu.model.document.enums.DocumentTypeEnum;
import hu.model.hospital.Appointment;

public interface DocumentFileValidator {

	/**
	 * @return A validátor által kezelhető documentum típus.
	 */
	DocumentTypeEnum getSupport();
	
	/**
	 * @param appointment Ehhez az appointmenthez lett feltöltve a fájl.
	 * @param file Feltöltött fájl.
	 * @param fileName Fájl neve.
	 * @param documentType Feltöltött fájl típusa.
	 * @return Hiba szöveggel, hogy miért nem lett elfogadvan.
	 * @throws IOException
	 */
	boolean validate(Appointment appointment, MultipartFile file, String fileName, DocumentType documentType)
			throws IOException, BasicServiceException;

}