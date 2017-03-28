package hu.web.util;

public class ViewNameHolder {

	/*
	 * Relativ paths of the views
	 */
	public static final String VIEW_HOME = "homePage";
	public static final String VIEW_INDEX = "index";
	public static final String VIEW_LOGIN =  "login";

	
	public static final String REDIRECT_TO_HOME ="redirect:/home";
	
	/*
	 * User és a hozzá tartozó view nevek
	 */
	public static final String VIEW_PERSONAL_DATA =  "user/personalData";
	
	public static final String REDIRECT_TO_PERSONAL_DATA =  "redirect:/personalData/{pdId}";
	
	
	/*
	 * Osztályokhoz tartozó
	 */
	public static final String VIEW_DEPARTMENTS = "department/departmentList";
	
	// Department modification
	public static final String VIEW_DEPARTMENT_MODIFICATION = "department/departmentModification";
	public static final String REDIRECT_TO_DEPARTMENT_MODIFICATION = "redirect:/department/{depId}";
	
	public static final String VIEW_CONSULTATION_HOUR_DETAILS = "department/consultationHourDetails";
	public static final String VIEW_CONSULTATION_HOUR_LIST = "department/consultationHourList";
	
	

	public static final String VIEW_APPOINTMENT = "department/appointment";
	public static final String VIEW_APPOINTMENT_MODIFY = "department/appointmentDetails";
	
	public static final String VIEW_MY_DOCUMENTS = "department/myDocumentfiles";
	public static final String VIEW_MY_APPOINTMENT = "department/myAppointmentList";
	
	public static final String REDIRECT_TO_CONSULTATION_HOUR_LIST = "redirect:/department/{depId}/consultationHour/list";
	public static final String REDIRECT_TO_MY_APPOINTMENTS = "redirect:/myAppointments";
	public static final String REDIRECT_TO_APPOINTMENT = "redirect:/appointment/{appId}";
	
	
	
/*	
	public static final String VIEW_CITIZEN = "citizen";
	public static final String VIEW_REGION = "region";
	public static final String VIEW_COUNTRY = "country";
	
	public static final String VIEW_DIPLOMATIC_CENTER = "diplomaticCenter";
	public static final String VIEW_PRESIDENDATALVOTE = "presidentalVote";
	
	public static final String VIEW_BATTLES = "battles";
	public static final String VIEW_BATTLEROUND = "battleRound";
	
	public static final String VIEW_COMPANY = "company/company";
	public static final String VIEW_DETAILED_COMPANY = "company/detailedCompany";
	
	public static final String VIEW_JOBOFFER = "company/jobOffers";

	public static final String VIEW_INVERTORY = "market/invertory";
	public static final String VIEW_MARKETPLACE = "market/marketplace";

	//			
			
	public static final String REDIRECT_TO_REGION ="redirect:/region";
	public static final String REDIRECT_TO_DIPLOMATIC_CENTER_WITHPARAM  ="redirect:/diplomaticCenter/";
	public static final String REDIRECT_TO_BATTLEROUND_WITH_BATTLEID = "redirect:/battleRound?battleId=";
	public static final String REDIRECT_TO_PRESIDENTALVOTE ="redirect:/presidentalVote";
	
	public static final String REDIRECT_TO_COMPANY = "redirect:/company/list";
	public static final String REDIRECT_TO_DETAILED_COMPANY = "redirect:/company/";
	
	public static final String REDIRECT_TO_JOBOFFER = "redirect:/company/jobOffers";
	
	public static final String REDIRECT_TO_INVERTORY = "redirect:/invertory";
	public static final String REDIRECT_TO_MARKETPLACE = "redirect:/marketplace";

	//	
	
	public static final String FRAGMENT_REGIONPICKER = "fragments/regionPicker :: regionPicker";
	*/
	
}
