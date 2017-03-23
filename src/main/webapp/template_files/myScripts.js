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
    return str.substr(0, 1).toUpperCase() + str.substr(1).toLowerCase();
}


/*
function updateCitizen(data) {
	if ( data.hasOwnProperty('lastWorkTime')){
		$("#myLastWorkDate").html(data.lastWorkTime);
	}
	if ( data.hasOwnProperty('energy')){
		$("#currentUserEnergy").html(data.energy + ' / 100');
		$("#currentUserEnergyBar").attr('style', 'width: ' +  data.energy + '%');
	}
	if ( data.hasOwnProperty('currentMoney')){
		$("#money").html(data.currentMoney);
	}
}

function substractCitizensMoney(money){
	var currentMoney = $("#money").html();
	$("#money").html(currentMoney - money);
}
*/