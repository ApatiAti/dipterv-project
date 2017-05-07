package hu.component.documentFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import hu.component.documentFile.validator.DefaultDocumentFileValidator;
import hu.component.documentFile.validator.DocumentFileValidator;
import hu.model.document.enums.DocumentTypeEnum;

@Component
@Scope("singleton")
public class DocumentFileValidatorFactory {
	
	@Autowired
	private List<DocumentFileValidator> validatorList;
	
	@Autowired
	private DefaultDocumentFileValidator defaultValidator;
	
	private static final Map<DocumentTypeEnum, DocumentFileValidator> validatorCache = new HashMap<>();
	
	
	@PostConstruct
	protected void initValidatorCache(){
		for (DocumentFileValidator validator : validatorList){
			DocumentTypeEnum support = validator.getSupport();
			if (support != null){
				validatorCache.put(support, validator);
			}
		}
	}	
	
	public DocumentFileValidator getDocumentFileValidator(DocumentTypeEnum documentTypeEnum){
		DocumentFileValidator validator = validatorCache.get(documentTypeEnum);
		if (validator == null){
			return defaultValidator;
		}
		
		return validator;
	}

}
