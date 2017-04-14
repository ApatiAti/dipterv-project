package hu.component.documentFile.validator;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hu.model.document.DocumentType;
import hu.model.document.enums.DocumentTypeEnum;
import hu.model.hospital.Appointment;

@Component
@Scope("singleton")
public class DefaultDocumentFileValidator implements DocumentFileValidator {

	@Override
	public DocumentTypeEnum getSupport() {
		return null;
	}
	
	/**
	 * @see hu.component.documentFile.validator.documentType.validator.DocumentFileValidator#validate(hu.model.hospital.Appointment, org.springframework.web.multipart.MultipartFile, java.lang.String, hu.model.document.DocumentType)
	 */
	@Override
	public String validate(Appointment appointment, MultipartFile file, String fileName, DocumentType documentType) throws IOException {
		return defaultValidation(appointment, file, fileName, documentType);
	}
	
	/**
	 * A {@link DocumentType}-ban megadott property-k alapján történő alapszintű validálás.  
	 */
	protected String defaultValidation(Appointment appointment, MultipartFile file, String fileName,
			DocumentType documentType) throws IOException {
		int lengthKB = file.getBytes().length/1024;
		
		if (documentType.getMaxSizeKB() <= lengthKB){
			return "A fájl mérete meghaladja a fájl típusához rendelet maximális méretett";
		}
		if (documentType.getMinSizeKB() >= lengthKB){
			return "A fájl mérete nem haladja meg a fájl típusához rendelet minimális méretett";
		}
		
		return null;
	}

}
