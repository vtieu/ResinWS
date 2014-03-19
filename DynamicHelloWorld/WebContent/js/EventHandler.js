function addEventHandler(oNode, sEvt, fHandler, bCapture)
{
	if ( typeof (oNode.attachEvent) != "undefined" )
		oNode.attachEvent("on" + sEvt, fHandler);
	else
		oNode.addEventListener(sEvt, fHandler, bCapture);
}

function onOkClicked( e) {
	var helloRequest = { name: $("#name").val() };
	var helloJson = JSON.stringify(helloRequest);
	var helloParams = $.param({helloRequest: helloJson});
    jQuery.ajax({
	    type: "POST",
	    url: "hello_test/hello/json",
	    data: helloParams,
	    dataType: "json",
	    success: function(result) {
	    	$("#hello").text(result.helloResult);
	    }
    });
}


function setUpClickHandler( ) {
	var oNode = $("#okButton")[0];
	addEventHandler(oNode, "click", onOkClicked, false);
}


addEventHandler(window, "load", setUpClickHandler, false);


//alert("You click ok.");