package hu.web.controller.hospital.appointment;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.model.hospital.Appointment;
import hu.service.AppointmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.CustomMessage;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;
import hu.web.util.CustomMessage.CustomMessageSeverity;

@Controller
public class MyAppointmentListController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MyAppointmentListController.class);
	
	@Autowired
	AppointmentService appointmentService;
	
	@Override
	protected Logger getLogger() {
		return logger;
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
			
			String messageString = "Időpont foglalás törlése sikeres!";
			logger.info(messageString);
			message = new CustomMessage(CustomMessageSeverity.SUCCESS, messageString);
		} catch ( Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			
			String errorMessage = "Időpont foglalás törlése sikertelen!";
			logger.error(errorMessage );
			message = new CustomMessage(CustomMessageSeverity.ERROR, errorMessage );
		}

		return new ResponseEntity<CustomMessage>(message, headers, status);
	}

}