package hu.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import hu.component.util.EnviromentConstans;
import hu.util.MessageConstants;


public class LoggingInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Autowired
	MessageSource messageSource;	
	
	@Autowired
	Environment env;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.preHandle(request, response, handler);
		
		if (logger.isDebugEnabled()){
			String viewName = request.getRequestURI();
			String regexp = env.getProperty(EnviromentConstans.MVC_REQUEST_LOGGING_EXCLUDE_REGEXP, "/template_files/(.)*$");
			
			if (!viewName.matches(regexp)){
				String logMessage = messageSource.getMessage(MessageConstants.LOGGING_CONTROLLER_VIEW_OPEN, null, null);
				logger.debug(logMessage, viewName);
			}
		}
		return true;
	}
}
