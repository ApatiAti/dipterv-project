package hu.web.controller.hospital.consultationHour;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import hu.model.user.User;
import hu.service.interfaces.ConsultationHourService;
import hu.service.interfaces.DepartmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
public class ConsultationHourDetailsController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ConsultationHourDetailsController.class);
		
	@Autowired 
	private ConsultationHourService consultationHourService;
	@Autowired
	private DepartmentService departmentService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	
	/**
	 * Megadott ConsoltationHour és a hozzá tartozó Appointment adatait részletező felület
	 * @throws BasicServiceException 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}", method = RequestMethod.GET)
	public String getConsultationHourDetailsPage(Map<String, Object> model
			, @PathVariable Long departmentId
			, @PathVariable Long consultationHourId) throws BasicServiceException{
		
		populateModelForConsultationHourDetailsPage(model, departmentId, consultationHourId);
		model.put(ModelKeys.IS_CONSULTATIONHOUR_MOFICATION, false);

		addDoctorListToModel(model, departmentId);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS;
	}

	public void addDoctorListToModel(Map<String, Object> model, Long departmentId) {
		Department department = departmentService.findDepartmentWithDoctors(departmentId);
		List<User> employeeList = department != null ? department.getEmployee() : new ArrayList<>();
		model.put(ModelKeys.DOCTORS_LIST, employeeList);
	}

	/**
	 * Megadott ConsoltationHour és a hozzá tartozó Appointment adatait részletező felület megnyitása módosításra
	 * @throws BasicServiceException 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/{consultationHourId}/edit", method=RequestMethod.GET)
	public String getConsultationHourDeatilsPageForModification(Map<String, Object> model
			, @PathVariable Long departmentId
			, @PathVariable Long consultationHourId) throws BasicServiceException{
		
		populateModelForConsultationHourDetailsPage(model, departmentId, consultationHourId);
		addDoctorListToModel(model, departmentId);
		
		model.put(ModelKeys.IS_CONSULTATIONHOUR_MOFICATION, true);
		
		return ViewNameHolder.VIEW_CONSULTATION_HOUR_DETAILS; 
	}
	
	/**
	 * A kapott ConsoltationHour módosítása 
	 */
	@RequestMapping(value = "/{departmentId}/consultationHour/edit", method=RequestMethod.POST)
	public String modifyConsultationHour(Map<String, Object> model
			, @PathVariable Long departmentId
			, @Valid ConsultationHour consultationHour, BindingResult bindingResult
			, RedirectAttributes redirectAttributes){

		boolean hasError = handleValidationErrors(bindingResult, model);
		if (hasError){
			errorLogAndDisplayMessage(redirectAttributes);
			Department department = new Department();
			department.setId(departmentId);
			consultationHour.setDepartment(department);
			redirectAttributes.addFlashAttribute(ModelKeys.ConsultationHour, consultationHour);
			return ViewNameHolder.redirectToConsultationHourDetailsModify(consultationHour);
		}
		
		try {
			ConsultationHour modifiedConsultationHour = consultationHourService.modifyConsultationHour(consultationHour);
			succesLogAndDisplayMessage(redirectAttributes, "Sikeres rendelési idő módosítás!");
			
			return ViewNameHolder.redirectToConsultationHourDetails(modifiedConsultationHour);

		} catch (BasicServiceException e) {
			errorLogAndDisplayMessage(redirectAttributes, e);
			return ViewNameHolder.REDIRECT_TO_HOME;
		}
	}	

	private void populateModelForConsultationHourDetailsPage(Map<String, Object> model, Long departmentId,
			Long consultationHourId) throws BasicServiceException {
		ConsultationHour consultationHour = consultationHourService.findConsultationHourWithAppointment(consultationHourId);
		
		addConsultationTypesToModel(model, departmentId, consultationHourService);
		model.putIfAbsent(ModelKeys.ConsultationHour, consultationHour);
	}
}