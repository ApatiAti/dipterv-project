package hu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.repository.user.UserRepository;
import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
public class HomeController {

	@Autowired
	UserRepository citizenRepository;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHome(Model model, @RequestParam(value = ModelKeys.Security, required = false) String security) {
		handleSecurityParam(model, security);

		return ViewNameHolder.VIEW_HOME;
	}
	
	/**
	 * Default url elérése. 
	 * Ha hibával navigálunk ide akkor 403-as hibát dobunk.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultPAge(Model model, @RequestParam(value = ModelKeys.Security, required = false) String security) {
		handleSecurityParam(model, security);

		return ViewNameHolder.VIEW_HOME;
	}

	/**
	 * Security paraméter függvényében a model feltöltése
	 */
	public void handleSecurityParam(Model model, String security) {
		if (security != null) {
			if ("forbidden".equals(security)) {
				model.addAttribute(ModelKeys.DisplayMessage,
						new CustomMessage(CustomMessageSeverity.ERROR, "403 Forbidden"));
			}
		}
	}
}