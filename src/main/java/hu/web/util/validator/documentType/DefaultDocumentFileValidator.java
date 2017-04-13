package hu.web.util.validator.documentType;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hu.model.document.DocumentType;
import hu.model.hospital.Appointment;

@Component
public class DefaultDocumentFileValidator {
	
	public String validate(Appointment appointment, MultipartFile file, String fileName, DocumentType documentType) throws IOException {
		return defaultValidation(appointment, file, fileName, documentType);
	}
	
	private String defaultValidation(Appointment appointment, MultipartFile file, String fileName,
			DocumentType documentType) throws IOException {
		int length = file.getBytes().length;
		
		if (documentType.getMaxSize() <= length){
			return "A fájl mérete meghaladja a fájl típusához rendelet maximális méretett";
		}
		if (documentType.getMinSize() >= length){
			return "A fájl mérete nem haladja meg a fájl típusához rendelet minimális méretett";
		}
		
		return null;
	}

}
