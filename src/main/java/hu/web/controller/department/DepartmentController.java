package hu.web.controller.department;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes(value = {ModelKeys.SearchEntity, ModelKeys.DEPARTMENT})
public class DepartmentController extends BaseController {

	private static final Logger logger = Logger.getLogger(DepartmentController.class);

	@Autowired
	private ConsultationHourSearchValidator consultationHourSearchValidator;

	@Autowired
	private DepartmentService departmentService;

	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	/**
	 * Department listázó felület
	 */
	@RequestMapping(value ="/list" , method = RequestMethod.GET)
	public String getDeparmentsPage(Map<String, Object> model){
		List<Department> departmentList = departmentService.getDepartments();
		model.put(ModelKeys.DepartmentList, departmentList);
		return ViewNameHolder.VIEW_DEPARTMENTS;
	}
	
	/**
	 * A paramétereknek megfelelő Department-hez tartozó ConsultationHour-ok listázó felület
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/list", method = RequestMethod.GET)
	public String getConsultationHourListPage(Map<String, Object> model, @PathVariable(value="departmentId") Long departmentId){
		Department department = departmentService.findDepartment(departmentId);
		
		
		List<ConsultationHour> consultationHourList = departmentService.getConsultationHour(departmentId);
		ConsultationHourSearch searchEntity = new ConsultationHourSearch();
		
		model.putIfAbsent(ModelKeys.ConsultationHourList, consultationHourList);
		model.putIfAbsent(ModelKeys.SearchEntity, searchEntity);
		model.put(ModelKeys.DEPARTMENT, department);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_LIST;
	}
	
	/**
	 * ConsoltationHour listázó felületen a lista ConsoltationHour lista szűrése
	 */
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
	
	/**
	 * Megadott ConsoltationHour és a hozzá tartozó Appointment adatait részletező felület
	 * /department/{id}/consultationHour/{id}ű
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}", method = RequestMethod.GET)
	public String getConsultationHourDetailsPage(Map<String, Object> model, @PathVariable Long departmentId, @PathVariable Long consultationHourId){
		ConsultationHour consultationHour = departmentService.findConsultationHourWithAppointment(consultationHourId);
		
		model.put(ModelKeys.ConsultationHour, consultationHour);

		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS;
	}
}
