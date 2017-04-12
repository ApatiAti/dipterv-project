package hu.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hu.exception.BasicServiceException;
import hu.exception.security.AuthorizationException;
import hu.model.document.DocumentFile;
import hu.model.document.DocumentFileAppointment;
import hu.model.document.DocumentFileContent;
import hu.model.document.DocumentType;
import hu.model.document.enums.DocumentTypeEnum;
import hu.model.hospital.Appointment;
import hu.model.user.User;
import hu.repository.document.DocumentFileAppointmentRepository;
import hu.repository.document.DocumentFileContentRepository;
import hu.repository.document.DocumentFileRepository;
import hu.repository.document.DocumentTypeRepository;
import hu.repository.hospital.AppointmentRepository;
import hu.service.security.SecurityService;
import hu.util.EmailType;

@Service
public class DocumentService {

	private static final Logger logger = Logger.getLogger(DocumentService.class);
	
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
	
	
	@Transactional
	public boolean saveUploadedFile(Long appointmentId, MultipartFile file, String fileName) throws BasicServiceException {
		try {
			DocumentFile doc = createNewDocumentFile(file, fileName, DocumentTypeEnum.LELET);
			DocumentFileAppointment docFileApp = createNewDocumentFileAppointment(doc, appointmentId);
			
			sendNotificationEmailToPatient(docFileApp);
			
			return true;
		} catch (IOException e) {
			String errorMessage = "Hiba történt a file mentése során";
			logger.error(errorMessage, e);
			throw new BasicServiceException(errorMessage);
		} 
	}

	@Transactional
	private void sendNotificationEmailToPatient(DocumentFileAppointment docFileApp) throws BasicServiceException {
		Map<String, Object> emailModel = new HashMap<>();
		User patient = docFileApp.getAppointment().getPatient();
		
		emailModel.put("name" , patient.getPersonalData().getName());
		emailModel.put("createDate", new Date());
		
		mailService.sendTemplateEnginedMail("tntiti@hotmail.com", EmailType.DOCUMENT_UPLOADED, emailModel);
	}

	@Transactional
	private DocumentFile createNewDocumentFile(MultipartFile file, String fileName, DocumentTypeEnum type) throws IOException {
		DocumentFileContent content = new DocumentFileContent();
		byte[] contentBytes = file.getBytes();
		content.setContent(contentBytes);
		// Kérds hogy ez így megy-e
		content = documentFileContentRepository.save(content);

		DocumentType documentType = documentTypeRepository.findByTypeName(type.name());
		
		DocumentFile doc = new DocumentFile();
		doc.setFileName(fileName);
		doc.setDocumentType(documentType);
		doc.setCreateDate(new Date());
		doc.setContentFile(content);
		doc.setContentType(file.getContentType());
		
		return documentFileRepository.save(doc);
	}

	@Transactional
	private DocumentFileAppointment createNewDocumentFileAppointment(DocumentFile doc, Long appointmentId) throws BasicServiceException {
		Appointment app = appointmentRepository.findOne(appointmentId);

		if (app != null){
			DocumentFileAppointment docFileApp = new DocumentFileAppointment();
			docFileApp.setDocument(doc);
			docFileApp.setAppointment(app);
			docFileApp.setCreateUser(securityService.getCurrentUser());
			docFileApp.setCreateDate(new Date());
			
			return docFileAppointmentRepository.save(docFileApp);
		} else {
			throw new BasicServiceException("Appointment nem található");
		}
	}


	public DocumentFile findDocumentByAppointmentIdAndDocumentFileAppId(Long appointmentId, Long documentFileAppId) throws BasicServiceException, AuthorizationException {
		DocumentFileAppointment docFileApp = docFileAppointmentRepository.findOne(documentFileAppId);
		
		securityService.authorizeCurrentUserToDownload(docFileApp);
		
		if (docFileApp != null && appointmentId.equals(docFileApp.getAppointment().getId())){
			return docFileApp.getDocument();
		}
		
		throw new BasicServiceException("Keresett fájl nem található");
	}


	public List<DocumentFileAppointment> findDocFileAppByAppointmentId(Long appointmentId) {
		return docFileAppointmentRepository.findByAppointmentId(appointmentId);
	}


	public List<DocumentFileAppointment> findDocFileAppByCurrentUser() {
		Long currentUserID = securityService.getCurrentUser().getId();

		return docFileAppointmentRepository.findByAppointmentPatientId(currentUserID);
	}

	public List<DocumentTypeEnum> getDocumentTypeEnumByConsultationHourType(Long consultationHourTypeId) {
		return documentTypeRepository.findTypeNameByConsultationHourTypeIdAndSysDate(consultationHourTypeId);
	}
}
