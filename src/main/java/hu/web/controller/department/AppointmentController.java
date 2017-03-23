package hu.web.controller.department;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import hu.model.hospital.Appointment;
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

	@Override
	public Logger getLogger() {
		return logger;
	}
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	DocumentService documentService;
	
	@RequestMapping(value="/appointment/create" , method = RequestMethod.GET)
	public String getCreateAppointmentPage(Model model
			, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @RequestParam(value = "chId" , required = true) Long consultationHourId
			, @RequestParam(value = "depId", required = false) Long departmentId, RedirectAttributes redirectAttributes){
				
		String errorString = "";
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

	@RequestMapping(value = "/appointment/create", method = RequestMethod.POST)
	public String createAppointment(Map<String, Object> model, Appointment appointment
			,@ModelAttribute(ModelKeys.CurrentUserName) String currentUser
			,@RequestParam(value = "chId" , required = true) Long consultationHourId, RedirectAttributes redirectAttributes){
		
		// TODO validation
		
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



	@Autowired
	MailService mailService;
	
//	időpont foglalás listázása	beteg	listMyAppointments	 /myAppointments
	@RequestMapping(value = "/myAppointments" , method = RequestMethod.GET)
	public String getMyAppointmentsPage(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName){
		
		List<Appointment> myAppointmentList = appointmentService.getAppintmentByUsername(currentUserName);
		
		model.put(ModelKeys.AppointmentList, myAppointmentList);
	
		
		return "department/appointmentList";
	}

	//	időpont foglalás megtekitése	beteg, orvos/admin	viewAppointment	 /appointment?id=
	@RequestMapping(value = "/appointment/{appointmentId}" , method = RequestMethod.GET)
	public String getAppointmentPage(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @PathVariable Long appointmentId){
		
		Appointment appointment = appointmentService.findAppointmentById(appointmentId);		
		List<DocumentFileAppointment> documentAppFileList = documentService.findDocFileAppByAppointmentId(appointmentId);
		
		model.put(ModelKeys.IsDisabled, true);
		model.put(ModelKeys.Appointment, appointment);
		model.put(ModelKeys.ConsultationHour, appointment.getConsultationHour());
		model.put(ModelKeys.DocumentAppFileList, documentAppFileList);
		
		return ViewNameHolder.VIEW_APPOINTMENT_MODIFY;
	}
		
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

	// TODO AJAX-os hívássá tenni és kliens oldalon törölni a sort
	@RequestMapping(value ="/appointment/delete", method = RequestMethod.POST)
	public String deleteAppointment(Model model, @ModelAttribute(ModelKeys.CurrentUserName) String currentUsername
			, @RequestParam(value = "appId", required = true) Long appointmentId, RedirectAttributes redirectAttributes){
		
		appointmentService.deleteAppointment(appointmentId);
		
		CustomMessage message = new CustomMessage(CustomMessageSeverity.SUCCESS, "Időpont foglalás törlése sikeres!");
		redirectAttributes.addFlashAttribute(ModelKeys.DisplayMessage, message);
		
		return ViewNameHolder.REDIRECT_TO_MY_APPOINTMENTS;
	}
	
	
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
		} catch ( Throwable e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			message = new CustomMessage(CustomMessageSeverity.ERROR, "Időpont foglalás törlése sikertelen!");
		}

		return new ResponseEntity<CustomMessage>(message, headers, status);
	}

	
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/appointment/{appointmentId}/uploadFile", method = RequestMethod.POST)
	public String uploadFile(Model model,
			@PathVariable("appointmentId") Long appointmentId,	
			@RequestParam("name") String fileName,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (fileName != null && !fileName.trim().isEmpty()){
			
			if (file != null && !file.isEmpty()){
				
				try {
					documentService.saveUploadedFile(appointmentId, file, fileName);
				} catch (BasicServiceException e) {
					errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "Hiba a fájl feltöltés közben", e);
				}
				
			} else {
				errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "Fájl feltöltése kötelező");
			}
		} else {
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "File név megadása kötelező");
		}
		
		
		return ViewNameHolder.REDIRECT_TO_APPOINTMENT.replace("{appId}", appointmentId.toString());
		
	}
	
	
    @RequestMapping(value="/appointment/{appointmentId}/download/{documentId}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response
    		, @PathVariable("appointmentId") Long appointmentId
    		, @PathVariable("documentId") Long documentId
    		, RedirectAttributes redirectAttributes
    				){
     
    	try {
    		DocumentFile file = documentService.findDocumentByAppointmentIdAndDocumentFileAppId(appointmentId, documentId);
    		
    		DocumentUtil.setFileInResponse(response, file);
    		
    	} catch ( AuthorizationException | BasicServiceException e ){
    		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage(), e);
		} catch (IOException e) {
    		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "File letöltése közben hiba történt!", e);
		} 
    }

	
 
	
}