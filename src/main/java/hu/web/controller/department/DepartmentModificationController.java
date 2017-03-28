package hu.web.controller.department;

import java.util.Map;

import javax.validation.Valid;

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

import hu.exception.BasicServiceException;
import hu.exception.DepartmentNotFoundException;
import hu.model.hospital.ConsultationHour;
import hu.model.hospital.ConsultationHourType;
import hu.model.hospital.Department;
import hu.service.ConsultationHourService;
import hu.service.DepartmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
@SessionAttributes({ModelKeys.DEPARTMENT_ID, ModelKeys.DEPARTMENT})
public class DepartmentModificationController extends BaseController {

	private static final Logger logger = Logger.getLogger(DepartmentModificationController.class);
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ConsultationHourService consultationHourService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	
	@RequestMapping(value = "/department/{departmentId}", method = RequestMethod.GET)
	public String getDepartmentModificationPage(Map<String, Object> model
			, @PathVariable long departmentId, RedirectAttributes redirectAttributes){
		
		try{
			Department department = departmentService.findDepartment(departmentId);
			
			addConsultationTypesToModel(model, departmentId, consultationHourService);
			
			ConsultationHour consultationHour = new ConsultationHour();
			consultationHour.setType(new ConsultationHourType());
			
			model.put(ModelKeys.ConsultationHour, consultationHour);
			model.put(ModelKeys.DEPARTMENT, department);
			model.put(ModelKeys.DEPARTMENT_ID, departmentId);

			return ViewNameHolder.VIEW_DEPARTMENT_MODIFICATION;
		} catch (BasicServiceException e){
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage());
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
	}

	/**
	 * Már létező Department módosítása
	 */
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public String modifyDepartment(Map<String, Object> model
			, @Valid Department department, BindingResult bindingResult
			, RedirectAttributes redirectAttributes){
		
		if (handleValidationErrors(bindingResult, model)){
			Long depId = department.getId(); 
			
			if (depId != null){	
				errorLoggingAndCreateErrorFlashAttribute(redirectAttributes);
				return ViewNameHolder.VIEW_DEPARTMENT_MODIFICATION;
				
			} else {
				errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "A megadott osztály nem módosítható");
				return ViewNameHolder.REDIRECT_TO_HOME;
			}
		}
		
		Department modifiedDepartment = departmentService.modifyDepartment(department);
		
		if (modifiedDepartment != null){
			succesLogAndDisplayMessage(redirectAttributes, "Osztály módosítása sikeres");
			return ViewNameHolder.REDIRECT_TO_DEPARTMENT_MODIFICATION.replace("{depId}", modifiedDepartment.getId().toString());
		} else {
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, "Hiba a mentés során");
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
	}
	
	/**
	 * ConsoltationHour létrehozása 
	 */
	@RequestMapping(value = "/consultationHour", method=RequestMethod.POST)
	public String createConsultationHour(Map<String, Object> model
			, @ModelAttribute(ModelKeys.DEPARTMENT_ID) Long departmentId
			, @Valid ConsultationHour consultationHour, BindingResult bindingResult
			, RedirectAttributes redirectAttributes){

		boolean hasError = handleValidationErrors(bindingResult, model);
		if (hasError){
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes);
			return ViewNameHolder.VIEW_DEPARTMENT_MODIFICATION;
		}
		
		try {
			departmentService.createConsultationHour(consultationHour, departmentId);
			succesLogAndDisplayMessage(redirectAttributes, "Sikeres rendelési idő foglalás");
			
		} catch (BasicServiceException | DepartmentNotFoundException e) {
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, e.getMessage(), e);
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS;
	}
}