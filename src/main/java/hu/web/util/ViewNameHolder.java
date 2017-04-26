package hu.web.util;

import hu.model.hospital.ConsultationHour;

public class ViewNameHolder {

	public static final String VIEW_HOME = "homePage";
	public static final String VIEW_INDEX = "index";
	public static final String VIEW_LOGIN =  "login";
	
	public static final String REDIRECT_TO_HOME ="redirect:/home";
	
	/*
	 * user mappában található view-k elérése és hozzájuk kapcsolódó redirect stringek
	 */
	public static final String VIEW_PERSONAL_DATA =  "user/personalData";

	public static final String REDIRECT_TO_PERSONAL_DATA =  "redirect:/personalData/{pdId}";
	public static final String REDIRECT_TO_PERSONAL_DATA_MODIFY =  "redirect:/personalData/{pdId}/edit";
	
	
	/*
	 *  hospital/department mappában található view-k elérése és hozzájuk kapcsolódó redirect stringek
	 */
	public static final String VIEW_DEPARTMENTS = "hospital/department/departmentList";
	public static final String VIEW_DEPARTMENT_MODIFICATION = "hospital/department/departmentModification";
	
	public static final String REDIRECT_TO_DEPARTMENT_MODIFICATION = "redirect:/department/{depId}";
	
	
	/*
	 *	document mappában található view-k elérése és hozzájuk kapcsolódó redirect stringek
	 */
	public static final String VIEW_MY_DOCUMENTS = "document/myDocumentfiles";
	
	/*
	 * hospital/consultationHour mappában található view-k elérése és hozzájuk kapcsolódó redirect stringek
	 */
	public static final String VIEW_CONSULTATION_HOUR_DETAILS = "hospital/consultationHour/consultationHourDetails";
	public static final String VIEW_CONSULTATION_HOUR_LIST = "hospital/consultationHour/consultationHourList";
	public static final String VIEW_CONSULTATION_HOUR_MODIFICATION = "hospital/consultationHour/consultationHourModification";

	public static final String REDIRECT_TO_CONSULTATION_HOUR_LIST = "redirect:/{depId}/consultationHour/list";
	public static final String REDIRECT_TO_CONSULTATION_HOUR_DETAILS = "redirect:/{depId}/consultationHour/{chId}";
	public static final String REDIRECT_TO_CONSULTATION_HOUR_DETAILS_MODIFY = "redirect:/consultationHour/{chId}/edit";
	
	/*
	 * hospital/apppointment mappában található view-k elérése és hozzájuk kapcsolódó redirect stringek
	 */
	public static final String VIEW_APPOINTMENT = "hospital/appointment/appointment";
	public static final String VIEW_APPOINTMENT_MODIFY = "hospital/appointment/appointmentDetails";
	public static final String VIEW_MY_APPOINTMENT = "hospital/appointment/myAppointmentList";
	
	public static final String REDIRECT_TO_MY_APPOINTMENTS = "redirect:/myAppointments";
	public static final String REDIRECT_TO_APPOINTMENT = "redirect:/appointment/{appId}";
	
	
	
	public static String redirectToConsultationHourDetails(ConsultationHour consultationHour) {
		return ViewNameHolder.REDIRECT_TO_CONSULTATION_HOUR_DETAILS
				.replace("{depId}", consultationHour.getDepartment().getId().toString())
				.replace("{chId}", consultationHour.getId().toString())
				;
	}
	
	public static String redirectToConsultationHourDetailsModify(ConsultationHour consultationHour) {
		return ViewNameHolder.REDIRECT_TO_CONSULTATION_HOUR_DETAILS_MODIFY
				.replace("{depId}", consultationHour.getDepartment().getId().toString())
				.replace("{chId}", consultationHour.getId().toString())
				;
	}
	
}
