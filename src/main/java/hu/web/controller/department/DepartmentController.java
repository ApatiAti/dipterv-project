package hu.web.controller.department;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hu.exception.DepartmentNotFoundException;
import hu.model.hospital.ConsultationHour;
import hu.model.hospital.Department;
import hu.model.hospital.dto.ConsultationHourSearch;
import hu.service.DepartmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;
import hu.web.util.validator.ConsultationHourSearchValidator;

@Controller
@RequestMapping(value = "/department")
@SessionAttributes(value = {ModelKeys.SearchEntity, ModelKeys.Department})
public class DepartmentController extends BaseController {

	private static final Logger logger = Logger.getLogger(DepartmentController.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	

	@Autowired
	private ConsultationHourSearchValidator consultationHourSearchValidator;

	@Autowired
	private DepartmentService departmentService;

	
	@RequestMapping(value ="/list" , method = RequestMethod.GET)
	public String getDeparmentsPage(Map<String, Object> model){
		List<Department> departmentList = departmentService.getDepartments();
		model.put(ModelKeys.DepartmentList, departmentList);
		return ViewNameHolder.VIEW_DEPARTMENTS;
	}
	
	@RequestMapping(value = "/{departmentId}/consultationHour/list", method = RequestMethod.GET)
	public String getConsultationHourListPage(Map<String, Object> model, @PathVariable(value="departmentId") Long departmentId){
		Department department = departmentService.findDepartment(departmentId);
		
		
		List<ConsultationHour> consultationHourList = departmentService.getConsultationHour(departmentId);
		ConsultationHourSearch searchEntity = new ConsultationHourSearch();
		
		model.putIfAbsent(ModelKeys.ConsultationHourList, consultationHourList);
		model.putIfAbsent(ModelKeys.SearchEntity, searchEntity);
		model.put(ModelKeys.Department, department);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_LIST;
	}

	@RequestMapping(value = "/{departmentId}/consultationHour/list", method = RequestMethod.POST)
	public String sortConsultationHourList(Map<String, Object> model, @PathVariable Long departmentId
			, ConsultationHourSearch searchEntity, BindingResult bindingResult){
		
		consultationHourSearchValidator.validate(searchEntity, bindingResult);
		
		// TODO ősosztály error kezelőjét berakni ide
		if (bindingResult.hasErrors()){
			model.put(ModelKeys.SearchEntity, searchEntity);
			return ViewNameHolder.VIEW_CONSULTATION_HOUR_LIST;
		}
		
		List<ConsultationHour> consultationHourList = departmentService.sortConsultationHour(searchEntity, departmentId);
		
		model.put(ModelKeys.ConsultationHourList, consultationHourList);
		model.put(ModelKeys.SearchEntity, searchEntity);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_LIST;
	}
	
	// /department/{id}/consultationHour/{id}
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}", method = RequestMethod.GET)
	public String getConsultationHourDetailsPage(Map<String, Object> model, @PathVariable Long departmentId, @PathVariable Long consultationHourId){
		ConsultationHour consultationHour = departmentService.findConsultationHourWithAppointment(consultationHourId);
		
		model.put(ModelKeys.ConsultationHour, consultationHour);

		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS;
	}
	
	
	@RequestMapping(value = "/{departmentId}/consultationHour/create", method = RequestMethod.POST)
	public String createConsultationHour(Map<String, Object> model, @PathVariable Long departmentId, @Valid ConsultationHour newConsultationHour
			, BindingResult bindingResult, RedirectAttributes redirectAttributes){

		boolean hasError = handleValidationErrors(bindingResult, model);
		if (hasError){
			return ViewNameHolder.VIEW_CONSULTATION_HOUR_LIST;
		}
		
		try {
			departmentService.createConsultationHour(newConsultationHour, departmentId);
		
		} catch (DepartmentNotFoundException e) {
			String errorLog = "A megadott id-jű osztály nem létezik. Id : " + departmentId.toString();
			
			errorLoggingAndCreateErrorFlashAttribute(redirectAttributes, errorLog, e);
			
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS;
	}

}
