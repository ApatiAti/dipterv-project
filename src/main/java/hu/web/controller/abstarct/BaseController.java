package hu.web.controller.abstarct;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.BasicServiceException;
import hu.model.hospital.ConsultationHourType;
import hu.service.ConsultationHourService;
import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.ModelKeys;

public abstract class BaseController {
	
	protected abstract Logger getLogger();
	
	public void errorLoggingAndCreateErrorFlashAttribute(RedirectAttributes redirectAttributes) {
		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "Hibás adatok lettek megadva", null);
	}
	
	public void errorLoggingAndCreateErrorFlashAttribute(RedirectAttributes redirectAttributes, String errorString) {
		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, errorString, null);
	}

	public void errorLoggingAndCreateErrorFlashAttribute(RedirectAttributes redirectAttributes, Exception e) {
		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage(), e);
	}
	
	public void errorLoggingAndCreateErrorFlashAttribute(RedirectAttributes redirectAttributes, String errorString, Exception e) {
		if (e != null){
			getLogger().error(errorString, e);
		} else {
			getLogger().error(errorString);
		}
		
		CustomMessage message = new CustomMessage(CustomMessageSeverity.ERROR, errorString);
		redirectAttributes.addFlashAttribute(ModelKeys.DisplayMessage, message);
	}

	protected void succesLogAndDisplayMessage(RedirectAttributes redirectAttributes, String succesMessage) {
		getLogger().info(succesMessage);
		
		CustomMessage message = new CustomMessage(CustomMessageSeverity.SUCCESS, succesMessage);
		redirectAttributes.addFlashAttribute(ModelKeys.DisplayMessage, message);
	}
	
	public boolean handleValidationErrors(BindingResult bindingResult, Map<String, Object> model) {
		boolean hasErrors = bindingResult.hasErrors();
		if (hasErrors){
			String errorLog = "A megadott adatok hibásak";
			CustomMessage errorMessage = new CustomMessage(CustomMessageSeverity.ERROR, errorLog);
			
			model.put(ModelKeys.DisplayMessage, errorMessage);
		}
		
		return hasErrors;
	}

	public void addConsultationTypesToModel(Map<String, Object> model, Long departmentId, ConsultationHourService consultationHourService) throws BasicServiceException {
		List<ConsultationHourType> consultationTypes = consultationHourService.findConsultationHourTypeByDepartmentId(departmentId);
		
		model.put(ModelKeys.CONSULTATIONHOUR_TYPES, consultationTypes);
	}

}
