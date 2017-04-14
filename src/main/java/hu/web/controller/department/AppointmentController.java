package hu.web.controller.department;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.AlreadyHaveAppointmentException;
import hu.exception.BasicServiceException;
import hu.exception.ConsultationHourNotFound;
import hu.exception.UserNotFoundException;
import hu.exception.security.AuthorizationException;
import hu.model.document.DocumentFile;
import hu.model.document.DocumentFileAppointment;
import hu.model.document.enums.DocumentTypeEnum;
import hu.model.hospital.Appointment;
import hu.model.hospital.ConsultationHour;
import hu.service.AppointmentService;
import hu.service.DocumentService;
import hu.service.MailService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.DocumentUtil;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
public class AppointmentController extends BaseController {

	public static final Logger logger = Logger.getLogger(AppointmentService.class);
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	MailService mailService;

	@Override
	public Logger getLogger() {
		return logger;
	}
	
	// TODO nem kell departmentId
	/**
	 * Új Appointment létrehozó felület behozása 
	 */
	@RequestMapping(value="/appointment/create" , method = RequestMethod.GET)
	public String getCreateAppointmentPage(Model model
			, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @RequestParam(value = "chId" , required = true) Long consultationHourId
			, @RequestParam(value = "depId", required = false) Long departmentId, RedirectAttributes redirectAttributes){
				
		String errorString;
		String ifErrorwhereToRedirect = ViewNameHolder.REDIRECT_TO_HOME;

		try {
			Appointment newAppointment = appointmentService.buildNewAppointment(consultationHourId, currentUserName);
		
			model.addAttribute(ModelKeys.Appointment, newAppointment);
			model.addAttribute(ModelKeys.ConsultationHour, newAppointment.getConsultationHour());
			
			return ViewNameHolder.VIEW_APPOINTMENT;
		
		} catch (AlreadyHaveAppointmentException e ) {
			errorString = "A betegnek már van az adót időpontra időpontja foglalva";
			if (departmentId != null){
				ifErrorwhereToRedirect = ViewNameHolder.REDIRECT_TO_CONSULTATION_HOUR_LIST.replace("{depId}", departmentId.toString());
			}
		} catch (UserNotFoundException e) {
			errorString = "A megadott username-el nem létezik felhasználó. Username : " + currentUserName;
		} catch (ConsultationHourNotFound  e) {
			errorString = "A megadott Id-va nem létezik fogadási időpont. Id : " + consultationHourId;
		} catch (BasicServiceException e) {
			errorString = e.getMessage();
		}
		
		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, errorString);
		return ifErrorwhereToRedirect;
	}

	/**
	 * Új Appointment létrehozása 
	 */
	@RequestMapping(value = "/{consultationHourId}/appointment/create", method = RequestMethod.POST)
	public String createAppointment(Map<String, Object> model, @Valid Appointment appointment
			,BindingResult bindingResult
			,@PathVariable(value = "consultationHourId") Long consultationHourId
			,@ModelAttribute(ModelKeys.CurrentUserName) String currentUser
			, RedirectAttributes redirectAttributes){
		
		// TODO validation
		if (handleValidationErrors(bindingResult, model)){
			logger.error("Hibás adatok lettek megadva.");
			return ViewNameHolder.VIEW_APPOINTMENT;
		}
		
		try {
			appointmentService.saveAppointment(appointment, consultationHourId, currentUser);
			
			return ViewNameHolder.REDIRECT_TO_CONSULTATION_HOUR_LIST.replace("{depId}", appointment.getConsultationHour().getDepartment().getId().toString());
		} catch (ConsultationHourNotFound  | UserNotFoundException e) {
			String errorString = "A megadott paraméterek nem megfelelőek.";
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, errorString, e);
		} catch (BasicServiceException e) {
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage(), e);
		}
		
		return ViewNameHolder.REDIRECT_TO_HOME;
	}

	
	/**
	 * Egy user a saját időpont foglalásainak listázása listMyAppointments->myAppointments
	 */
	@RequestMapping(value = "/myAppointments" , method = RequestMethod.GET)
	public String getMyAppointmentsPage(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName){
		
		List<Appointment> myAppointmentList = appointmentService.getAppintmentByUsername(currentUserName);
		
		model.put(ModelKeys.AppointmentList, myAppointmentList);
	
		return ViewNameHolder.VIEW_MY_APPOINTMENT;
	}

	/**
	 * Beteg egy saját időpont foglalásának megtekitése	 (beteg, orvos/admin) viewAppointment -> appointment?id=
	 */
	@RequestMapping(value = "/appointment/{appointmentId}" , method = RequestMethod.GET)
	public String getAppointmentPage(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @PathVariable Long appointmentId){
		
		Appointment appointment = appointmentService.findAppointmentById(appointmentId);	
		ConsultationHour consultationHour = appointment.getConsultationHour();
		List<DocumentFileAppointment> documentAppFileList = documentService.findDocFileAppByAppointmentId(appointmentId);
		List<DocumentTypeEnum> documentTypeEnumList = documentService.getDocumentTypeEnumByConsultationHourType(consultationHour.getType().getId()); 
		
		
		model.put(ModelKeys.IsDisabled, true);
		model.put(ModelKeys.Appointment, appointment);
		model.put(ModelKeys.ConsultationHour, consultationHour);
		model.put(ModelKeys.DocumentAppFileList, documentAppFileList);
		model.put(ModelKeys.DOCUMENT_TYPE_LIST, documentTypeEnumList);
		
		return ViewNameHolder.VIEW_APPOINTMENT_MODIFY;
	}
	
	/**
	 * Megadott id-val rendelkező Appointment-t módosító felület
	 */
	@RequestMapping(value = "/appointment/modify" , method = RequestMethod.GET)
	public String getAppointmentModifyPage(Model model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @RequestParam("appId") Long appointmentId, RedirectAttributes redirectAttributes){
				
		try {
			Appointment appointment = appointmentService.findAppointmentByIdAndUserName(appointmentId, currentUserName);

			model.addAttribute(ModelKeys.IsDisabled, false);
			model.addAttribute(ModelKeys.Appointment, appointment);
			model.addAttribute(ModelKeys.ConsultationHour, appointment.getConsultationHour());
			
			return ViewNameHolder.VIEW_APPOINTMENT_MODIFY;
		
		} catch (BasicServiceException e ){
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage(), e);
		}
		
		return ViewNameHolder.REDIRECT_TO_HOME;
	}

	/**
	 * Egy foglaláshoz tartozó beteg panaszainak a módosítása. 
	 */
	@RequestMapping(value = "/appointment/modify" , method = RequestMethod.POST)
	public String modifyAppointment(Model model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUsername
			, Appointment appointment, RedirectAttributes redirectAttributes){
	
		try {
			appointmentService.modifyAppointment(appointment);
		} catch (BasicServiceException e) {
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage(), e);
		}
		
		return ViewNameHolder.REDIRECT_TO_MY_APPOINTMENTS;
	}

	/**
	 * Megadott id-val rendelkező Appointment törlése Beteg által
	 */
	@Deprecated
	@RequestMapping(value ="/appointment/delete", method = RequestMethod.POST)
	public String deleteAppointment(Model model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUsername
			, @RequestParam(value = "appId", required = true) Long appointmentId, RedirectAttributes redirectAttributes){
		
		appointmentService.deleteAppointment(appointmentId);
		
		succesLogAndDisplayMessage(redirectAttributes, "Időpont foglalás törlése sikeres!");
		
		return ViewNameHolder.REDIRECT_TO_MY_APPOINTMENTS;
	}
	
	/**
	 * AJAX
	 * Megadott id-val rendelkező Appointment törlése Beteg által
	 */
	@RequestMapping(value ="/appointment/delete", method = RequestMethod.POST, headers = "X-Requested-With=XMLHttpRequest")
	public @ResponseBody ResponseEntity<CustomMessage> deleteAppointmentAJAX(Model model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUsername
			, @RequestParam(value = "appId", required = true) Long appointmentId, RedirectAttributes redirectAttributes){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus status;
		CustomMessage message;
		
		try{
			appointmentService.deleteAppointment(appointmentId);
		
			status = HttpStatus.OK;
			message = new CustomMessage(CustomMessageSeverity.SUCCESS, "Időpont foglalás törlése sikeres!");
		} catch ( Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			message = new CustomMessage(CustomMessageSeverity.ERROR, "Időpont foglalás törlése sikertelen!");
		}

		return new ResponseEntity<CustomMessage>(message, headers, status);
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
				errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, errorMessage);
				return ViewNameHolder.REDIRECT_TO_APPOINTMENT.replace("{appId}", appointmentId.toString());
			}
	
			try {
				documentService.saveUploadedFile(appointmentId, file, fileName, documentTypeEnum);
				succesLogAndDisplayMessage(redirectAttributes, "A megadott fájl sikeressen feltöltésre került.");
			} catch (BasicServiceException e) {
				errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e);
			}
		} else {
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "Fájl feltöltése kötelező");
		}
				
		return ViewNameHolder.REDIRECT_TO_APPOINTMENT.replace("{appId}", appointmentId.toString());
	}
	
	
    @RequestMapping(value="/appointment/{appointmentId}/download/{documentId}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response
    		, @PathVariable("appointmentId") Long appointmentId
    		, @PathVariable("documentId") Long documentId
    		, RedirectAttributes redirectAttributes){
     
    	try {
    		DocumentFile file = documentService.findDocumentByAppointmentIdAndDocumentFileAppId(appointmentId, documentId);
    		
    		DocumentUtil.setFileInResponse(response, file, true);
    		logger.info("Sikeresen letöltötte a következő fájlt. Id : " + file.getFileName());
    	} catch ( AuthorizationException | BasicServiceException e ){
    		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage(), e);
		} catch (IOException e) {
    		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "File letöltése közben hiba történt!", e);
		} 
    }
}
