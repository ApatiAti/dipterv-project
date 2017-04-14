package hu.web.controller.hospital.appointment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.BasicServiceException;
import hu.exception.security.AuthorizationException;
import hu.model.document.DocumentFile;
import hu.model.document.DocumentFileAppointment;
import hu.model.document.enums.DocumentTypeEnum;
import hu.model.hospital.Appointment;
import hu.model.hospital.ConsultationHour;
import hu.service.AppointmentService;
import hu.service.DocumentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.DocumentUtil;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@SessionAttributes({ModelKeys.APPOINTMENT_ID})
@Controller
public class AppointmentDetailsController extends BaseController {

	private static final Logger logger = Logger.getLogger(AppointmentDetailsController.class);
	
	@Autowired
	AppointmentService appointmentService;
	@Autowired
	DocumentService documentService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	/**
	 * Model feltöltése a appointmentDetails.html view-nak megfelelően.
	 * @param model
	 * @param appointmentId
	 * @param appointment
	 */
	private void populateModelForViewAppointmentModify(Map<String, Object> model, Appointment appointment, boolean isDisabled) {
		ConsultationHour consultationHour = appointment.getConsultationHour();
		List<DocumentFileAppointment> documentAppFileList = documentService.findDocFileAppByAppointmentId(appointment.getId());
		List<DocumentTypeEnum> documentTypeEnumList = documentService.getDocumentTypeEnumByConsultationHourType(consultationHour.getType().getId()); 
		
		
		model.put(ModelKeys.IsDisabled, isDisabled);
		model.put(ModelKeys.Appointment, appointment);
		model.put(ModelKeys.APPOINTMENT_ID, appointment.getId());
		model.put(ModelKeys.ConsultationHour, appointment.getConsultationHour());
		
		model.put(ModelKeys.DocumentAppFileList, documentAppFileList);
		model.put(ModelKeys.DOCUMENT_TYPE_LIST, documentTypeEnumList);
	}

	
	/**
	 * Egy Appointment megtekitése
	 */
	@RequestMapping(value = "/appointment/{appointmentId}" , method = RequestMethod.GET)
	public String getAppointmentPage(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @PathVariable Long appointmentId){
		
		Appointment appointment = appointmentService.findAppointmentById(appointmentId);	
		
		populateModelForViewAppointmentModify(model, appointment, true);
		
		return ViewNameHolder.VIEW_APPOINTMENT_MODIFY;
	}


	/**
	 * Megadott id-val rendelkező Appointment módosító felület
	 */
	@RequestMapping(value = "/appointment/modify" , method = RequestMethod.GET)
	public String getAppointmentModifyPage(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @RequestParam("appId") Long appointmentId, RedirectAttributes redirectAttributes){
				
		try {
			Appointment appointment = appointmentService.findAppointmentByIdAndUserName(appointmentId, currentUserName);
			
			populateModelForViewAppointmentModify(model, appointment, false);
			
			return ViewNameHolder.VIEW_APPOINTMENT_MODIFY;
		
		} catch (BasicServiceException e ){
			errorLogAndDisplayMessage(redirectAttributes, e.getMessage(), e);
		}
		
		return ViewNameHolder.REDIRECT_TO_HOME;
	}


	/**
	 * Egy Appointment-hez tartozó beteg panaszainak a módosítása. 
	 */
	@RequestMapping(value = "/appointment/modify" , method = RequestMethod.POST)
	public String modifyAppointment(Model model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUsername
			, Appointment appointment, RedirectAttributes redirectAttributes){
	
		try {
			appointmentService.modifyAppointment(appointment);
			succesLogAndDisplayMessage(redirectAttributes, "Foglalás sikeresen módosítva");
		} catch (BasicServiceException e) {
			errorLogAndDisplayMessage(redirectAttributes, e.getMessage(), e);
		}
		
		return ViewNameHolder.REDIRECT_TO_MY_APPOINTMENTS;
	}

	
	/**
	 * Egy fájl feltöltése egy létező Appointmenthez 
	 */
	@RequestMapping(value = "/appointment/{appointmentId}/uploadFile", method = RequestMethod.POST)
	public String uploadFile(Model model,
			@PathVariable("appointmentId") Long appointmentId,	
			@RequestParam("name") String fileName,
			@RequestParam("file") MultipartFile file,	
			@RequestParam("documentType") DocumentTypeEnum documentTypeEnum,
			RedirectAttributes redirectAttributes) {
	
		if (file != null && !file.isEmpty()){
			
			String errorMessage = DocumentUtil.validateFileName(fileName);
			if (errorMessage != null){
				errorLogAndDisplayMessage(redirectAttributes, errorMessage);
				return ViewNameHolder.REDIRECT_TO_APPOINTMENT.replace("{appId}", appointmentId.toString());
			}
	
			try {
				documentService.saveUploadedFile(appointmentId, file, fileName, documentTypeEnum);
				succesLogAndDisplayMessage(redirectAttributes, "A megadott fájl sikeressen feltöltésre került.");
			} catch (BasicServiceException e) {
				errorLogAndDisplayMessage(redirectAttributes, e);
			}
		} else {
			errorLogAndDisplayMessage(redirectAttributes, "Fájl feltöltése kötelező");
		}
				
		return ViewNameHolder.REDIRECT_TO_APPOINTMENT.replace("{appId}", appointmentId.toString());
	}


	/**
	 * Megadott Appoinmenthez tartozó fájl letöltése. 
	 * Ha nem lehetséges a letöltés (jogosulatlan/hiba történik), akkor a kezdő képernyőre navigálunk
	 */
    @RequestMapping(value="/appointment/{appointmentId}/download/{documentId}", method = RequestMethod.GET)
    public Object downloadFile( @PathVariable("appointmentId") Long appointmentId
    		, @PathVariable("documentId") Long documentId
    		, RedirectAttributes redirectAttributes) throws IOException{
     
    	try {
    		DocumentFile file = documentService.findDocumentByAppointmentIdAndDocumentFileAppId(appointmentId, documentId);
    		
    		ResponseEntity<InputStreamResource> responseEntity = DocumentUtil.setFileIntoResponseEntity(file, true);
    		logger.info("Sikeresen letöltötte a következő fájlt. Id : " + file.getFileName());
    		
    		return responseEntity;
    	} catch ( AuthorizationException | BasicServiceException e ){
    		errorLogAndDisplayMessage(redirectAttributes, e.getMessage(), e);
		} catch (IOException e) {
    		errorLogAndDisplayMessage(redirectAttributes, "File letöltése közben hiba történt!", e);
		} 
    	return ViewNameHolder.REDIRECT_TO_HOME;
    }

}
