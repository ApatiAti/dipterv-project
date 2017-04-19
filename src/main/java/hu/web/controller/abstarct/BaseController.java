package hu.web.controller.abstarct;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.BasicServiceException;
import hu.model.hospital.ConsultationHourType;
import hu.service.interfaces.ConsultationHourService;
import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.ModelKeys;

@Component
public abstract class BaseController {
	
	private static final String VALIDATION_ERROR = "A megadott adatok hibásak";
	
	@Autowired
	MessageSource messageSource;	
	
	protected abstract Logger getLogger();
	
	public void errorLogAndDisplayMessage(RedirectAttributes redirectAttributes) {
		errorLogAndDisplayMessage(redirectAttributes, VALIDATION_ERROR, null);
	}
	
	public void errorLogAndDisplayMessage(RedirectAttributes redirectAttributes, String errorString) {
		errorLogAndDisplayMessage(redirectAttributes, errorString, null);
	}

	public void errorLogAndDisplayMessage(RedirectAttributes redirectAttributes, Exception e) {
		errorLogAndDisplayMessage(redirectAttributes, e.getMessage(), e);
	}
	
	public void errorLogAndDisplayMessage(RedirectAttributes redirectAttributes, String errorString, Exception e) {
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
			CustomMessage errorMessage = new CustomMessage(CustomMessageSeverity.ERROR, VALIDATION_ERROR);
			
			model.put(ModelKeys.DisplayMessage, errorMessage);
		}
		
		return hasErrors;
	}

	public void addConsultationTypesToModel(Map<String, Object> model, Long departmentId, ConsultationHourService consultationHourService) throws BasicServiceException {
		List<ConsultationHourType> consultationTypes = consultationHourService.findConsultationHourTypeByDepartmentId(departmentId);
		
		model.put(ModelKeys.CONSULTATIONHOUR_TYPES, consultationTypes);
	}

}
