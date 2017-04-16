package hu.web.controller.hospital.appointment;

import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.AlreadyHaveAppointmentException;
import hu.exception.BasicServiceException;
import hu.exception.ConsultationHourNotFound;
import hu.exception.UserNotFoundException;
import hu.model.hospital.Appointment;
import hu.service.AppointmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
public class AppointmentController extends BaseController {

	public static final Logger logger = Logger.getLogger(AppointmentService.class);
	
	@Autowired
	AppointmentService appointmentService;


	@Override
	public Logger getLogger() {
		return logger;
	}
	
	/**
	 * Új Appointment létrehozó felület behozása 
	 */
	@RequestMapping(value="/appointment/create" , method = RequestMethod.GET)
	public String getCreateAppointmentPage(Model model
			, @ModelAttribute(ModelKeys.CurrentUserName) String currentUserName
			, @RequestParam(value = "chId" , required = true) Long consultationHourId
			, RedirectAttributes redirectAttributes){
				
		String errorString;
		String ifErrorwhereToRedirect = ViewNameHolder.REDIRECT_TO_HOME;

		try {
			Appointment newAppointment = appointmentService.buildNewAppointment(consultationHourId, currentUserName);
		
			model.addAttribute(ModelKeys.Appointment, newAppointment);
			model.addAttribute(ModelKeys.ConsultationHour, newAppointment.getConsultationHour());
			
			return ViewNameHolder.VIEW_APPOINTMENT;
		
		} catch (AlreadyHaveAppointmentException e ) {
			errorString = "A betegnek már van az adott időpontra hely foglalva";
			if (e.getDepartmentId()!= null){
				ifErrorwhereToRedirect = ViewNameHolder.REDIRECT_TO_CONSULTATION_HOUR_LIST.replace("{depId}", e.getDepartmentId().toString());
			}
		} catch (UserNotFoundException e) {
			errorString = "A megadott username-el nem létezik felhasználó. Username : " + currentUserName;
		} catch (ConsultationHourNotFound  e) {
			errorString = "A megadott Id-va nem létezik fogadási időpont. Id : " + consultationHourId;
		} catch (BasicServiceException e) {
			errorString = e.getMessage();
		}
		
		errorLogAndDisplayMessage(redirectAttributes, errorString);
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
			succesLogAndDisplayMessage(redirectAttributes, "Foglalás sikeresen létrehozva");
			
			return ViewNameHolder.REDIRECT_TO_CONSULTATION_HOUR_LIST.replace("{depId}", appointment.getConsultationHour().getDepartment().getId().toString());
		} catch (ConsultationHourNotFound  | UserNotFoundException e) {
			String errorString = "A megadott paraméterek nem megfelelőek.";
			errorLogAndDisplayMessage(redirectAttributes, errorString, e);
		} catch (BasicServiceException e) {
			errorLogAndDisplayMessage(redirectAttributes, e.getMessage(), e);
		}
		
		return ViewNameHolder.REDIRECT_TO_HOME;
	}

}
