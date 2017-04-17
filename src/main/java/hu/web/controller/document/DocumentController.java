package hu.web.controller.document;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.model.document.DocumentFileAppointment;
import hu.service.interfaces.DocumentService;
import hu.web.controller.abstarct.BaseController;
import hu.web.util.ModelKeys;
import hu.web.util.ViewNameHolder;

@Controller
public class DocumentController extends BaseController{

	public static final Logger logger = Logger.getLogger(DocumentController.class);
	
	@Autowired
	DocumentService documentService;

	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	/**
	 * Beteg saját leleteit lehet listázni.
	 */
	@RequestMapping(value ="/myDocuments", method = RequestMethod.GET)
	public String getMyDocumentFilesPage(Model model){
		
		List<DocumentFileAppointment> documentAppFileList = documentService.findDocFileAppByCurrentUser();
		
		model.addAttribute(ModelKeys.DocumentAppFileList, documentAppFileList);
		
		return ViewNameHolder.VIEW_MY_DOCUMENTS;
	}
	
}
