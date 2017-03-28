/**
 * 
 */

//function createDatePicker() {
//	   $('#datetimepicker1').dateTimePicker();
//}

//function createDatePicker() {
//    $('#sandbox-container .input-group.date').datepicker({
//    	format: "yyyy-mm-dd",
//        todayBtn: "linked",
//        language: "hu",
//        autoclose: true,
//        todayHighlight: true
//    });
//}

function putAlertBox(severity, errorMessage) {
	$("#alertBox").show();
	$("#alertBox").attr('class', "alert alert-"+ severity.toLowerCase());
	$("#alert_severity_text").html(capitalize(severity));
	$("#alert_text").html(errorMessage);	
}

function capitalize( str ) {
    return str.substr(0, 1).toUpperCase() + str.substr(1).toLowerCase();
}