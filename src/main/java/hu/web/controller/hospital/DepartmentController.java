package hu.web.controller.hospital;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import hu.model.hospital.Department;
import hu.service.DepartmentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
@RequestMapping(value = "/department")
@SessionAttributes(value = {ModelKeys.SearchEntity, ModelKeys.DEPARTMENT})
public class DepartmentController extends BaseController {

	private static final Logger logger = Logger.getLogger(DepartmentController.class);

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
	
}
