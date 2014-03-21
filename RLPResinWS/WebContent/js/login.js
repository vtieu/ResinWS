var sessionId;

function loginClicked(e)
{
	var loginRequest = {
		userName: $("#name").val(),
		password: $("#password").val()
	};
	var loginJson = JSON.stringify(loginRequest);
	var helloParams = $.param({loginRequest: loginJson});
    jQuery.ajax({
	    type: "POST",
	    url: "RLPResinWS/login",
	    data: helloParams,
	    dataType: "json",
	    success: function(result) {
	    	if (result.error != null && result.error.length > 0) {
	    		loginError(result.error);
	    	}
	    	else {
	    		sessionId = result.sessionId;
	    		$("#loginMessage").css("color",  "black");
	    		$("#loginMessage").text("Login successful");
	    	}
	    },
	    error: function(request, status) {
	    	loginError(request.statusText);
	    }
    });
}

function loginError(message)
{
	$("#loginMessage").css("color",  "red");
	$("#loginMessage").text("Login failed: " + message);
}

function initLogin()
{
	$(window).load(function() {
		$( "#loginButton" ).button().click(function( event ) {
	        loginClicked(event);
	    });
	});
}
