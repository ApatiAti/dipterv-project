package hu.web.util.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hu.model.hospital.dto.ConsultationHourSearch;
import hu.web.util.CalendarUtil;

public class ConsultationHourSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ConsultationHourSearch.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ConsultationHourSearch obj = (ConsultationHourSearch) target;
		
		Date startDate = obj.getStartDate();
		Date endDate = obj.getEndDate();

		if (CalendarUtil.afterNotNull(startDate, endDate)){
			errors.reject("beginDate", "consultationHourSearch.dates.wrongOrder");
		}
		
		if (startDate == null && endDate != null){
			errors.reject("beginDate", "consultationHourSearch.dates.notInterval");
		}
		
	}

}
