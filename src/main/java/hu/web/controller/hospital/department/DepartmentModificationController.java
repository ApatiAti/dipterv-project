package hu.web.controller.hospital.department;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import hu.model.user.User;
import hu.service.interfaces.ConsultationHourService;
import hu.service.interfaces.DepartmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.CalendarUtil;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
@SessionAttributes({ModelKeys.DEPARTMENT_ID, ModelKeys.DEPARTMENT, ModelKeys.DOCTORS_LIST, ModelKeys.CONSULTATIONHOUR_TYPES})
public class DepartmentModificationController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentModificationController.class);
	
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
			Department department = departmentService.findDepartmentWithDoctors(departmentId);
			
			ConsultationHour consultationHour = new ConsultationHour();
			consultationHour.setType(new ConsultationHourType());
			consultationHour.setDoctor(new User());
			
			List<User> employeeList = department != null ? department.getEmployee() : new ArrayList<>();
			
			addConsultationTypesToModel(model, departmentId, consultationHourService);
			model.put(ModelKeys.ConsultationHour, consultationHour);
			model.put(ModelKeys.DOCTORS_LIST, employeeList);
			model.put(ModelKeys.DEPARTMENT, department);
			model.put(ModelKeys.DEPARTMENT_ID, departmentId);

			return ViewNameHolder.VIEW_DEPARTMENT_MODIFICATION;
		} catch (BasicServiceException e){
			errorLogAndDisplayMessage(redirectAttributes, e);
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
				errorLogAndDisplayMessage(redirectAttributes);
				return ViewNameHolder.VIEW_DEPARTMENT_MODIFICATION;
				
			} else {
				errorLogAndDisplayMessage(redirectAttributes, "A megadott osztály nem módosítható");
				return ViewNameHolder.REDIRECT_TO_HOME;
			}
		}
		
		Department modifiedDepartment = departmentService.modifyDepartment(department);
		
		if (modifiedDepartment != null){
			succesLogAndDisplayMessage(redirectAttributes, "Osztály módosítása sikeres");
			return ViewNameHolder.REDIRECT_TO_DEPARTMENT_MODIFICATION.replace("{depId}", modifiedDepartment.getId().toString());
		} else {
			errorLogAndDisplayMessage(redirectAttributes, "Hiba a mentés során");
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
		boolean intervalError = CalendarUtil.afterNotNull(consultationHour.getBeginDate(), consultationHour.getEndDate());
		
		if (hasError || intervalError){
			if (intervalError){
				errorLogAndDisplayMessage(redirectAttributes, "Hibás adatok lettek megadva. Kezdő és vége dátum rosszul lett megadva");
			} else {
				errorLogAndDisplayMessage(redirectAttributes);
			}
			return ViewNameHolder.REDIRECT_TO_DEPARTMENT_MODIFICATION.replace("{depId}", departmentId.toString());
		}
		
		try {
			ConsultationHour newConsultationHour = consultationHourService.createConsultationHour(consultationHour, departmentId);
			succesLogAndDisplayMessage(redirectAttributes, "Sikeres rendelési idő foglalás");

			return ViewNameHolder.redirectToConsultationHourDetails(newConsultationHour);
			
		} catch (BasicServiceException | DepartmentNotFoundException e) {
			errorLogAndDisplayMessage(redirectAttributes, e);
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
	}
}