package hu.web.security;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import hu.component.util.EnviromentConstans;

public class HospitalAuthenticationHelper {

	private static final Logger logger = LoggerFactory.getLogger(HospitalAuthenticationHelper.class);
	
	@Autowired
	Environment env;
	
	private static String API_REQUEST_BASE;
	
	@PostConstruct
	public void init(){
		logger.info("Initialize the HospitalAuthenticationHelper bean");
		HospitalAuthenticationHelper.API_REQUEST_BASE = env.getProperty(EnviromentConstans.MVC_API_REQUEST_BASE, "/api");
	}
	
	public boolean isRequestIsApiLogin(HttpServletRequest request) {
		return request.getParameter("mobile-api") != null;
		
	}
	
	public boolean isRequestToAPI(HttpServletRequest request) {
		return request.getRequestURI().startsWith(HospitalAuthenticationHelper.API_REQUEST_BASE);
	}
}
