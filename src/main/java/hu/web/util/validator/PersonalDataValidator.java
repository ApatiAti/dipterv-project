package hu.web.util.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import hu.model.user.PersonalData;
import hu.web.util.CalendarUtil;


public class PersonalDataValidator implements Validator {

	Validator nameValidator;
	
	public PersonalDataValidator(NameValidator nameValidator) {
		this.nameValidator = nameValidator;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return PersonalData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PersonalData personalData = (PersonalData) target;
		
		nameValidator.validate(personalData.getName(), errors);
		
		String tajNumber = personalData.getTajNumber();
		if (tajNumber != null && tajNumber.length() > 9){
			errors.rejectValue("tajNumber", "personalData.error.tajNumber.long");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "personalData.birthDate.null");
		Date birthDate = personalData.getBirthDate();
		if (birthDate != null 
				&& CalendarUtil.addYearsToCurrentDate(-120).after(birthDate)){
			errors.reject("birthData", "personalData.birthDate.tooOld"); 
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motherName", "personalData.motherName.null");
		
		String phoneNumber = personalData.getPhoneNumber();
		if (phoneNumber != null && phoneNumber.length() > 12){
			errors.rejectValue("phoneNumber", "personalData.phoneNumber.tooLong");
		}
		
	}
}
