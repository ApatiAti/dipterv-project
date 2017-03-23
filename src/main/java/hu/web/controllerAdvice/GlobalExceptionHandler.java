package hu.web.controllerAdvice;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import hu.repository.user.UserRepository;
import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@Autowired
	private UserRepository citizenRepository;

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		
		// Otherwise setup and send the user to a default error-view.
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();

		String[] securityError = req.getParameterMap().get(ModelKeys.Security);
		String content;
		if (securityError != null){
			// TODO messagePropertiesből olvasni.
			content = e.getMessage() + ". You don't have the right privilage.";
		} else {
			content = e.getMessage();
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
		
		
		e.printStackTrace();
		
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