package hu.web.controller.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.model.user.User;
import hu.repository.user.UserRepository;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ViewNameHolder;

@Controller
public class LoginController extends BaseController{

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserRepository citizenRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		
		if (principal instanceof UserDetails) {
			logger.debug("Már be van jelentkezve a felhasználó");
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
		
		setModelToNavigateLoginPage(model);
		return ViewNameHolder.VIEW_LOGIN;
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String get403Page(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			logger.debug("Már be van jelentkezve a felhasználó");
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
		
		setModelToNavigateLoginPage(model);
		return ViewNameHolder.VIEW_LOGIN;
	}
	
	public void setModelToNavigateLoginPage(Model model) {
		logger.debug("Nincs bejelentkezett felhasználó. Login screenre navigálás");
		model.addAttribute("loginUser", new User());
	}
	
	/*
	// HomeControllerbe lett áthelyezvea Srpign security bevezetése miatt
//	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String postLoginPage(Model model, @ModelAttribute("loginUser") Citizen loginUser, HttpSession session) {
//		Citizen currentUser = citizenRepository.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
		
//		Citizen currentUser = citizenRepository.findByEmailAndPassword(loginUser.getUsername(), loginUser.getPassword());
		System.out.println("Logolás miatt hogy ide jön-e?");
		
		if (currentUser != null && currentUser.getId() != null) {
			// model.addAttribute("currentUser", currentUser);
			session.setAttribute("currentUser", currentUser);
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
		return ViewNameHolder.VIEW_LOGIN;
	}

// Spring security maga csinálja meg 
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.setAttribute("currentUser", null);
		return ViewNameHolder.REDIRECT_TO_HOME;
	}
*/

}
