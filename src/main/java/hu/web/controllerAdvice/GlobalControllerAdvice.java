package hu.web.controllerAdvice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import hu.service.interfaces.security.SecurityService;
import hu.web.util.ModelKeys;

@ControllerAdvice(basePackages = {"hu.web.controller"} )
@SessionAttributes(ModelKeys.CurrentUserName)
public class GlobalControllerAdvice {

	@Autowired
	SecurityService securityService;
	
	@ModelAttribute
	public void setGlobalModelVariables(Map<String, Object> model){
		model.put(ModelKeys.CurrentUserName, securityService.getCurrentUserName());	
	}
}


