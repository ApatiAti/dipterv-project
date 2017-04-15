package hu.web.controller.hospital.consultationHour;

import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.BasicServiceException;
import hu.model.hospital.ConsultationHour;
import hu.service.ConsultationHourService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
public class ConsultationHourDetailsController extends BaseController {

	private static final Logger logger = Logger.getLogger(ConsultationHourDetailsController.class);
		
	@Autowired 
	private ConsultationHourService consultationHourService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	
	/**
	 * Megadott ConsoltationHour és a hozzá tartozó Appointment adatait részletező felület
	 * @throws BasicServiceException 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}", method = RequestMethod.GET)
	public String getConsultationHourDetailsPage(Map<String, Object> model
			, @PathVariable Long departmentId
			, @PathVariable Long consultationHourId) throws BasicServiceException{
		
		createModelForConsultationHourDetailsPage(model, departmentId, consultationHourId);
		model.put(ModelKeys.IS_CONSULTATIONHOUR_MOFICATION, false);

		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS;
	}

	// TODO módosítás esetén nem elsz jó mert az orvos neve eltűnik a legördülűből
	/**
	 * Megadott ConsoltationHour és a hozzá tartozó Appointment adatait részletező felület megnyitása módosításra
	 * @throws BasicServiceException 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}/edit", method=RequestMethod.GET)
	public String modifyConsultationHour(Map<String, Object> model
			, @PathVariable Long departmentId
			, @PathVariable Long consultationHourId) throws BasicServiceException{
		
		createModelForConsultationHourDetailsPage(model, departmentId, consultationHourId);
		model.put(ModelKeys.IS_CONSULTATIONHOUR_MOFICATION, true);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS; 
	}
	
	/**
	 * A kapott ConsoltationHour módosítása 
	 */
	@RequestMapping(value = "/consultationHour/{consultationHourId}/edit", method=RequestMethod.PUT)
	public String modifyConsultationHour(Map<String, Object> model
			, @Valid ConsultationHour consultationHour, BindingResult bindingResult
			, RedirectAttributes redirectAttributes){

		boolean hasError = handleValidationErrors(bindingResult, model);
		if (hasError){
			errorLogAndDisplayMessage(redirectAttributes);
			// TODO ez itt rossz mivel módosítani nem ott lehet
			return ViewNameHolder.VIEW_DEPARTMENT_MODIFICATION;
		}
		
		try {
			ConsultationHour modifiedConsultationHour = consultationHourService.modifyConsultationHour(consultationHour);
			succesLogAndDisplayMessage(redirectAttributes, "Sikeres rendelési idő módosítás!");
			
			return ViewNameHolder.redirectToConsultationHourDetails(modifiedConsultationHour);

		} catch (BasicServiceException e) {
			errorLogAndDisplayMessage(redirectAttributes, e.getMessage(), e);
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
	}	

	private void createModelForConsultationHourDetailsPage(Map<String, Object> model, Long departmentId,
			Long consultationHourId) throws BasicServiceException {
		ConsultationHour consultationHour = consultationHourService.findConsultationHourWithAppointment(consultationHourId);
		
		addConsultationTypesToModel(model, departmentId, consultationHourService);
		model.put(ModelKeys.ConsultationHour, consultationHour);
	}
}