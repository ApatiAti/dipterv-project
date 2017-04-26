package hu.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class HospitalSavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	protected final Logger logger = LoggerFactory.getLogger(HospitalSavedRequestAwareAuthenticationSuccessHandler.class);

	@Autowired
	HospitalAuthenticationHelper authenticationHelper;

	private RequestCache requestCache = new HttpSessionRequestCache();


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		if (authenticationHelper.isRequestToAPI(request)){
			APICallOnAuthenticationSuccess(request, response, authentication);
		} else {
			defaultOnAuthenticationSuccess(request, response, authentication);
		}
	}


	public void APICallOnAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		  
        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        
        String targetUrlParam = getTargetUrlParameter();
        
        if (isAlwaysUseDefaultTargetUrl()
          || (targetUrlParam != null
          && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }
 
        clearAuthenticationAttributes(request);
	}

	/**
	 * {@link SavedRequestAwareAuthenticationSuccessHandler} onAuthenticationSucces metódusának másolata.
	 */
	public void defaultOnAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}
		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils.hasText(request
						.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		clearAuthenticationAttributes(request);

		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

}
