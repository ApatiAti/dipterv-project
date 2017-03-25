package hu.web.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.model.user.PersonalData;
import hu.service.UserService;
import hu.web.util.CustomMessage;
import hu.web.util.CustomMessage.CustomMessageSeverity;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;
import hu.web.util.validator.PersonalDataValidator;

@Controller
@SessionAttributes({ ModelKeys.PersonalDataIsDisabled })
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	PersonalDataValidator personalDataValidator;

	@Autowired
	UserService userService;

	/**
	 * Személyes adatok felület megjelenítése a bejelentkezett felhasználó számára
	 */
	@RequestMapping(value = "/personalData", method = RequestMethod.GET)
	public String getDefaultPersonalData(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String username, RedirectAttributes redirectAttributes){
		PersonalData personalData = userService.findPersonalDataByUsername(username);
		
		if (personalData == null){
			String errorMessage = "A bejelentkezett felhasználónak nincs személyes adatai. Bejelentkezett felhasználó : " + username;
			CustomMessage message = new CustomMessage(CustomMessageSeverity.ERROR, errorMessage);

			logger.error(errorMessage);
			redirectAttributes.addFlashAttribute(ModelKeys.DisplayMessage, message);
		}

		model.put(ModelKeys.PersonalDataIsDisabled, true);
		model.put(ModelKeys.PersonalData, personalData);

		return ViewNameHolder.VIEW_PERSONAL_DATA;
	}
	
	/**
	 * Személyes adatok megjelenítése personalDataId alapján.
	 */
	@RequestMapping(value = "/personalData/{personalDataId}", method = RequestMethod.GET)
	public String getPersonalData(Map<String, Object> model, @ModelAttribute(ModelKeys.CurrentUserName) String username,
			@PathVariable Long personalDataId) {
		
		PersonalData personalData = userService.findPersonalDataByUserId(personalDataId);

		model.put(ModelKeys.PersonalDataIsDisabled, true);
		model.put(ModelKeys.PersonalData, personalData);

		return ViewNameHolder.VIEW_PERSONAL_DATA;
	}
	
	/**
	 * Személyes adatokat módosító oldal behozása.
	 */
	@RequestMapping(value = "/personalData/{personalDataId}/edit", method = RequestMethod.GET)
	public String getPersonalDataEdit(Map<String, Object> model,
			@ModelAttribute(ModelKeys.CurrentUserName) String username, @PathVariable Long personalDataId
			, RedirectAttributes redirectAttributes) {
		PersonalData personalData = userService.getEditablePersonalData(username, personalDataId);

		if (personalData != null){
			model.put(ModelKeys.PersonalDataIsDisabled, false);
			model.put(ModelKeys.PersonalData, personalData);
	
			return ViewNameHolder.VIEW_PERSONAL_DATA;
		} else {
			CustomMessage message = new CustomMessage(CustomMessageSeverity.ERROR, "Csak a saját adataidat módosíthatod");
			redirectAttributes.addFlashAttribute(ModelKeys.DisplayMessage, message);
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
	}

	/**
	 * Személyes adatokat módisítása, ha a megadott adatok validok.
	 */
	@RequestMapping(value = "/personalData/edit", method = RequestMethod.POST)
	public String editPersonalData(PersonalData personalData, BindingResult bindingResult
			, @ModelAttribute(ModelKeys.CurrentUserName) String username
			, RedirectAttributes redirectAttributes) {
		
		personalDataValidator.validate(personalData, bindingResult);

		if (bindingResult.hasErrors()) {
			return ViewNameHolder.VIEW_PERSONAL_DATA;
		}

		personalData = userService.updatePersonalDate(personalData, username);

		redirectAttributes.addFlashAttribute(ModelKeys.PersonalDataIsDisabled, false);
		redirectAttributes.addFlashAttribute(ModelKeys.PersonalData, personalData);

		return ViewNameHolder.REDIRECT_TO_PERSONAL_DATA.replace("{pdId}", personalData.getId().toString());
	}
	

}
