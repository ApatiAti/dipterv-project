

function putAlertBox(severity, errorMessage) {
	var severityText = getSeverityText(severity);
	$("#alertBox").show();
	$("#alertBox").attr('class', "alert alert-"+ severityText);
	$("#alert_severity_text").html(capitalize(severity));
	$("#alert_text").html(errorMessage);	
}


function getSeverityText(severity){
	switch (severity) {
	  case "SUCCES":
		  return "success";
		  
	  case "INFO":
		  return "info";
		  
	  case "WARNING":
		  return "warning";
		  
	  case "ERROR":
		  return "danger";
		  
	  default:
		  return "info";
	}
}

function capitalize( str ) {
    // #strings.capitalize(menuItem.desFnz)
	return str.substr(0, 1).toUpperCase() + str.substr(1).toLowerCase();
}