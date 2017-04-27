package hu.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

public class HospitalAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	@Autowired
	HospitalAuthenticationHelper authenticationHelper;
	
	public HospitalAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		if (authenticationHelper.isRequestToAPI(request) || authenticationHelper.isRequestIsApiLogin(request)){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		} else {
			super.commence(request, response, authException);
		}
	}


}
