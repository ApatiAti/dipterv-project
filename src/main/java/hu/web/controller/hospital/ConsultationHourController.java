package hu.web.controller.hospital;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.BasicServiceException;
import hu.model.hospital.ConsultationHour;
import hu.model.hospital.Department;
import hu.model.hospital.dto.ConsultationHourSearch;
import hu.service.ConsultationHourService;
import hu.service.DepartmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;
import hu.web.util.validator.ConsultationHourSearchValidator;

@Controller
public class ConsultationHourController extends BaseController {

	private static final Logger logger = Logger.getLogger(ConsultationHourController.class);
	
	@Autowired
	private ConsultationHourSearchValidator consultationHourSearchValidator;

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired 
	private ConsultationHourService consultationHourService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	/**
	 * A paramétereknek megfelelő Department-hez tartozó ConsultationHour-ok listázó felület
	 * @throws BasicServiceException 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/list", method = RequestMethod.GET)
	public String getConsultationHourListPage(Map<String, Object> model, @PathVariable(value="departmentId") Long departmentId) throws BasicServiceException{
		Department department = departmentService.findDepartment(departmentId);
		
		List<ConsultationHour> consultationHourList = consultationHourService.findConsultationHourList(departmentId);
		
		ConsultationHourSearch searchEntity = new ConsultationHourSearch();
		
		model.putIfAbsent(ModelKeys.ConsultationHourList, consultationHourList);
		model.putIfAbsent(ModelKeys.SearchEntity, searchEntity);
		model.put(ModelKeys.DEPARTMENT, department);
		
		addConsultationTypesToModel(model, departmentId, consultationHourService);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_LIST;
	}
	
	/**
	 * ConsoltationHour listázó felületen a lista ConsoltationHour lista szűrése
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/list", method = RequestMethod.POST)
	public String sortConsultationHourList(Map<String, Object> model, @PathVariable Long departmentId
			, ConsultationHourSearch searchEntity, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		
		consultationHourSearchValidator.validate(searchEntity, bindingResult);
		 
		if (bindingResult.hasErrors()){
			model.put(ModelKeys.SearchEntity, searchEntity);
			return ViewNameHolder.VIEW_CONSULTATION_HOUR_LIST;
		}
		
		List<ConsultationHour> consultationHourList = consultationHourService.sortConsultationHour(searchEntity, departmentId);
		
		redirectAttributes.addFlashAttribute(ModelKeys.ConsultationHourList, consultationHourList);
		redirectAttributes.addFlashAttribute(ModelKeys.SearchEntity, searchEntity);
		
		return ViewNameHolder.REDIRECT_TO_CONSULTATION_HOUR_LIST.replace("{depId}", departmentId.toString());
	}
	
	/**
	 * Megadott ConsoltationHour és a hozzá tartozó Appointment adatait részletező felület
	 * @throws BasicServiceException 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}", method = RequestMethod.GET)
	public String getConsultationHourDetailsPage(Map<String, Object> model
			, @PathVariable Long departmentId
			, @PathVariable Long consultationHourId) throws BasicServiceException{
		
		createModelForConsultationHourDetailsPage(model, departmentId, consultationHourId);
		model.put(ModelKeys.IS_CONSULTATIONHOUR_MOFICATION, false);

		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS;
	}

	/**
	 * Megadott ConsoltationHour és a hozzá tartozó Appointment adatait részletező felület megnyitása módosításra
	 * @throws BasicServiceException 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}/edit", method=RequestMethod.GET)
	public String modifyConsultationHour(Map<String, Object> model
			, @PathVariable Long departmentId
			, @PathVariable Long consultationHourId) throws BasicServiceException{
		
		createModelForConsultationHourDetailsPage(model, departmentId, consultationHourId);
		model.put(ModelKeys.IS_CONSULTATIONHOUR_MOFICATION, true);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS; 
	}
	
	/**
	 * ConsoltationHour módosítása 
	 */
	@RequestMapping(value = "/consultationHour/{consultationHourId}/edit", method=RequestMethod.POST)
	public String modifyConsultationHour(Map<String, Object> model
			, @Valid ConsultationHour consultationHour, BindingResult bindingResult
			, RedirectAttributes redirectAttributes){

		boolean hasError = handleValidationErrors(bindingResult, model);
		if (hasError){
			errorLogAndDisplayMessage(redirectAttributes);
			return ViewNameHolder.VIEW_DEPARTMENT_MODIFICATION;
		}
		
		try {
			ConsultationHour modifiedConsultationHour = consultationHourService.modifyConsultationHour(consultationHour);
			succesLogAndDisplayMessage(redirectAttributes, "Sikeres rendelési idő módosítás!");
			
			return ViewNameHolder.redirectToConsultationHourDetails(modifiedConsultationHour);

		} catch (BasicServiceException e) {
			errorLogAndDisplayMessage(redirectAttributes, e.getMessage(), e);
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
	}	

	private void createModelForConsultationHourDetailsPage(Map<String, Object> model, Long departmentId,
			Long consultationHourId) throws BasicServiceException {
		ConsultationHour consultationHour = consultationHourService.findConsultationHourWithAppointment(consultationHourId);
		
		addConsultationTypesToModel(model, departmentId, consultationHourService);
		model.put(ModelKeys.ConsultationHour, consultationHour);
	}
}