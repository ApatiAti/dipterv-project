package hu.web.controller.abstarct;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.ModelKeys;

public abstract class BaseController {
	
	public abstract Logger getLogger();
	
	public void errorLoggingAndCreateErrorFlashAttribute(RedirectAttributes redirectAttributes, String errorString) {
		errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, errorString, null);
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
}
