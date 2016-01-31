var token = '{{ token }}';
getToken = function() {
	$.post("/token", {
		login : getCookie('email_user'),
		CMD : "GET_TOKEN"
	}, function(m) {
		// Retreive the token generated associated with the created Channel
		token = m.token;
		// Get the messages history
		histo = m.histo;
		openChannel(histo);
	});
};

openChannel = function(histo) {
	// retrieve the communication channel associated with the given token
	var channel = new goog.appengine.Channel(token);
	// define function handler associated with the channel
	var handler = {
		'onopen' : onOpened(histo),
		'onmessage' : onMessage,
		'onerror' : function(err) {
			alert("An error occurred. Make sure you are connected before joining the chat.");
		},
		'onclose' : function() {
			alert("Chat is now closed.");
		}
	};
	// open the channel with associated function handler
	var socket = channel.open(handler);
	socket.onopen = onOpened;
	socket.onmessage = onMessage;
}
onOpened = function(histo) {
	$("#connectButton").hide();
	$("#chatDiv").removeClass("hidden");
	$("#historyId").append(histo);
};
onMessage = function(m) {
	$("#historyId").append(m.data);
}
sendMessage = function() {
	var message = {};
	message.token = token;
	message.CMD = "SEND_MSG";
	message.message = $("#txtId").val();
	// Send message to the servlet at the URL /token with message to send
	$.post("/token", message, function(m) {
	});
	// remove text from input box
	$("#txtId").val("");
};

// Get a cookie value
function getCookie(name) {
	var dc = document.cookie;
	var prefix = name + "=";
	var begin = dc.indexOf("; " + prefix);
	if (begin == -1) {
		begin = dc.indexOf(prefix);
		if (begin != 0)
			return null;
	} else {
		begin += 2;
		var end = document.cookie.indexOf(";", begin);
		if (end == -1) {
			end = dc.length;
		}
	}
	return unescape(dc.substring(begin + prefix.length, end));
}