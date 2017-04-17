package hu.web.controllerAdvice;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class); 
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
		
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();

		String[] securityError = req.getParameterMap().get(ModelKeys.Security);
		String content;
		if (securityError != null){
			content = e.getMessage() + ". Nincs megfelelő joga ehhez a művelethez.";
		} else {
			content = "Hiba történt.\n" + e.getMessage();
		}
		
		map.put(ModelKeys.DisplayMessage, new CustomMessage(CustomMessageSeverity.ERROR, content));
		
		StringBuilder builder = new StringBuilder(req.getRequestURL());
		Set<Entry<String, String[]>> entrySet = req.getParameterMap().entrySet();
	
		// A kért url összerakása stringé
		if (!CollectionUtils.isEmpty(entrySet)){
			builder.append("?");
			int size = entrySet.size();
		for (Entry<String, String[]> entry : entrySet) {
				
				builder.append(entry.getKey());
				builder.append("=");
				builder.append(StringUtils.join(entry.getValue(), ""));
				size--;
				if (size > 0){
					builder.append("&");
				}
			}
		}
		
		map.put(ModelKeys.ErrorUrl, builder.toString());
		setGlobalModelVariables(map);
		
		mav.setViewName(ViewNameHolder.VIEW_HOME);
		
		logger.error(content , e);
		
		return mav;
	}
	
	public void setGlobalModelVariables(Map<String, Object> model){
		/* TODO Current User besettelése
		if (!model.containsKey(ModelKeys.CurrentUser)){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
			if (principal instanceof UserDetails) {
				String username = ((UserDetails)principal).getUsername();
			
				model.put(ModelKeys.CurrentUser, citizenRepository.findByUsername(username));
			}
		}
		*/
	}
}