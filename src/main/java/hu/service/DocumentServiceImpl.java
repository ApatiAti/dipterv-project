package hu.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hu.component.documentFile.DocumentFileValidatorFactory;
import hu.component.documentFile.validator.DocumentFileValidator;
import hu.exception.BasicServiceException;
import hu.exception.security.AuthorizationException;
import hu.model.document.DocumentFile;
import hu.model.document.DocumentFileAppointment;
import hu.model.document.DocumentFileContent;
import hu.model.document.DocumentType;
import hu.model.document.enums.DocumentTypeEnum;
import hu.model.document.enums.ExtensionTypes;
import hu.model.hospital.Appointment;
import hu.model.user.User;
import hu.repository.document.DocumentFileAppointmentRepository;
import hu.repository.document.DocumentFileContentRepository;
import hu.repository.document.DocumentFileRepository;
import hu.repository.document.DocumentTypeRepository;
import hu.repository.hospital.AppointmentRepository;
import hu.service.interfaces.DocumentService;
import hu.service.interfaces.MailService;
import hu.service.interfaces.security.SecurityService;
import hu.util.EmailType;

@Service
public class DocumentServiceImpl implements DocumentService{

	private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
	
	@Autowired
	DocumentFileRepository documentFileRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	DocumentFileAppointmentRepository docFileAppointmentRepository;

	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	@Autowired
	DocumentFileContentRepository documentFileContentRepository;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	DocumentFileValidatorFactory documentFileValidatorFactory;
	
	@Override
	@Transactional
	public boolean saveUploadedFile(Long appointmentId, MultipartFile file, String fileName, DocumentTypeEnum documentTypeId) throws BasicServiceException {
		try {
			Appointment appointment = appointmentRepository.findOne(appointmentId);
			
			securityService.authorizeCurrenctUserToUpload(appointment);
			
			if (appointment != null){	
				DocumentType documentType = getDocumentType(appointment , fileName, documentTypeId);
				
				if (documentType != null){
					DocumentFileValidator documentumTypeValidator = documentFileValidatorFactory.getDocumentFileValidator(documentType.getTypeName());
					
					boolean validat = documentumTypeValidator.validate(appointment, file, fileName, documentType);
				
					if (validat){
				
						DocumentFile doc = createNewDocumentFile(file, fileName, documentType);
						DocumentFileAppointment docFileApp = createNewDocumentFileAppointment(doc, appointment);
						
						sendNotificationEmailToPatient(docFileApp);
						
						return true;
					} else {
						throw new BasicServiceException("Hiba történt a validálás során");
					}
				} else {
					throw new BasicServiceException("Nincs ilyen document típus!");
				}
			} else {
				throw new BasicServiceException("Appointment nem található");
			}
		} catch ( BasicServiceException e){
			logger.error("Hiba történt a validálás során", e);
			throw e;
		} catch (IOException | AuthorizationException e) {
			String errorMessage = "Hiba történt a file mentése során";
			logger.error(errorMessage, e);
			throw new BasicServiceException(errorMessage);
		}
	}
	
	/**
	 * Megadott paraméterek szerinti DocumentType keresése
	 * @param appointment
	 * @param fileName
	 * @param documentTypeEnum
	 * @return 
	 * @throws BasicServiceException dobódik abban az esetben ha nem értelmezhető a megadott kiterjesztése a fájlnak
	 */
	@Transactional
	private DocumentType getDocumentType(Appointment appointment, String fileName, DocumentTypeEnum documentTypeEnum) throws BasicServiceException {
		ExtensionTypes extensionType = ExtensionTypes.fromFileName(fileName);
		
		if (extensionType == null){
			throw new BasicServiceException("Ez a kiterjesztési típus nem támogatott");
		} 
		
		return documentTypeRepository.getDocumentumTypeForUplodedFile(appointment.getConsultationHour().getType().getId(), documentTypeEnum, extensionType);
	}

	@Transactional
	private void sendNotificationEmailToPatient(DocumentFileAppointment docFileApp) throws BasicServiceException {
		Map<String, Object> emailModel = new HashMap<>();
		User patient = docFileApp.getAppointment().getPatient();
		
		emailModel.put("name" , patient.getPersonalData().getName());
		emailModel.put("createDate", new Date());
		
		mailService.sendTemplateEnginedMail(patient.getEmail(), EmailType.DOCUMENT_UPLOADED, emailModel);
	}

	@Transactional
	private DocumentFile createNewDocumentFile(MultipartFile file, String fileName, DocumentType documentType) throws IOException {
		DocumentFileContent content = new DocumentFileContent();
		byte[] contentBytes = file.getBytes();
		content.setContent(contentBytes);
		// Kérds hogy ez így megy-e
		content = documentFileContentRepository.save(content);
		
		DocumentFile doc = new DocumentFile();
		doc.setFileName(fileName);
		doc.setDocumentType(documentType);
		doc.setCreateDate(new Date());
		doc.setContentFile(content);
		doc.setContentType(file.getContentType());
		
		return documentFileRepository.save(doc);
	}

	@Transactional
	private DocumentFileAppointment createNewDocumentFileAppointment(DocumentFile doc, Appointment appointment) throws BasicServiceException {
		DocumentFileAppointment docFileApp = new DocumentFileAppointment();
		docFileApp.setDocument(doc);
		docFileApp.setAppointment(appointment);
		docFileApp.setCreateUser(securityService.getCurrentUser());
		docFileApp.setCreateDate(new Date());
		
		return docFileAppointmentRepository.save(docFileApp);
	}

	@Override
	@Transactional
	public DocumentFile findDocumentByAppointmentIdAndDocumentFileAppId(Long appointmentId, Long documentFileAppId) throws BasicServiceException, AuthorizationException {
		DocumentFileAppointment docFileApp = docFileAppointmentRepository.findOne(documentFileAppId);
		
		securityService.authorizeCurrentUserToDownload(docFileApp);
		
		if (docFileApp != null && appointmentId.equals(docFileApp.getAppointment().getId())){
			DocumentFile documentFile = docFileApp.getDocument();
			Hibernate.initialize(documentFile.getContentFile());
			return documentFile;
		}
		
		throw new BasicServiceException("Keresett fájl nem található");
	}

	@Override
	public List<DocumentFileAppointment> findDocFileAppByAppointmentId(Long appointmentId) {
		return docFileAppointmentRepository.findByAppointmentId(appointmentId);
	}

	@Override
	public List<DocumentFileAppointment> findDocFileAppByCurrentUser() {
		Long currentUserID = securityService.getCurrentUser().getId();

		return docFileAppointmentRepository.findByAppointmentPatientId(currentUserID);
	}
	
	@Override
	public List<DocumentTypeEnum> getDocumentTypeEnumByConsultationHourType(Long consultationHourTypeId) {
		return documentTypeRepository.findTypeNameByConsultationHourTypeIdAndSysDate(consultationHourTypeId);
	}
}
