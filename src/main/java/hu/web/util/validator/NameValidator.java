package hu.web.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import hu.model.user.Name;


public class NameValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Name.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.firstName", "name.firstName.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.lastName", "name.lastName.null");
	}



}
