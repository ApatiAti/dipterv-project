package hu.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import hu.exception.BasicServiceException;
import hu.exception.security.AuthorizationException;
import hu.model.document.DocumentFile;
import hu.model.document.DocumentFileAppointment;
import hu.model.document.enums.DocumentTypeEnum;

public interface DocumentService {

	/**
	 * Feltöltött fájl és annak típusának validálása és mentése.
	 * Ha sikeres a feltöltés email értesítőt kap a felhasználó, akihez feltöltötték
	 * @param appointmentId		Appointemnt id-ja, ahova fel akarjuk tölteni a fájlt
	 * @param file		A kapott fájl
	 * @param fileName	A fájl neve kiterjesztéssel együtt
	 * @param documentTypeId 	Feltöltött fájl megadott típus
	 * @return
	 * @throws BasicServiceException  
	 */
	public boolean saveUploadedFile(Long appointmentId, MultipartFile file, String fileName,
			DocumentTypeEnum documentTypeId) throws BasicServiceException;
	
	/**
	 * DocumentumFile keresése a megadott paraméterke alapján.
	 * A dokumentum tartalma is inicializálva lesz.
	 * @param appointmentId
	 * @param documentFileAppId
	 * @return
	 * @throws BasicServiceException
	 * @throws AuthorizationException
	 */
	public DocumentFile findDocumentByAppointmentIdAndDocumentFileAppId(Long appointmentId, Long documentFileAppId) throws BasicServiceException, AuthorizationException;

	public List<DocumentFileAppointment> findDocFileAppByAppointmentId(Long appointmentId);

	public List<DocumentFileAppointment> findDocFileAppByCurrentUser();
	
	public List<DocumentTypeEnum> getDocumentTypeEnumByConsultationHourType(Long consultationHourTypeId);
}
