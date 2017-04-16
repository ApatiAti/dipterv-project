/**
 * 
 */

function putAlertBox(severity, errorMessage) {
	$("#alertBox").show();
	$("#alertBox").attr('class', "alert alert-"+ severity.toLowerCase());
	$("#alert_severity_text").html(capitalize(severity));
	$("#alert_text").html(errorMessage);	
}

function capitalize( str ) {
    // #strings.capitalize(menuItem.desFnz)
	return str.substr(0, 1).toUpperCase() + str.substr(1).toLowerCase();
}