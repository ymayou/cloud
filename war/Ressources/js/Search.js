var searchButton = "#searchButton";
var searchInput = "#searchInput";

$(document).ready(function() {
	// Get the token
	var token = getCookie("token_user");

	getResults(token);
	getRss(token);
});

function getCookie(name) {
	var re = new RegExp(name + "=([^;]+)");
	var value = re.exec(document.cookie);
	return (value != null) ? unescape(value[1]) : null;
}

function getRss(token) {
	// Look for RSS feed
	$.get("rss", {
		token : token
	}, function(data, status) {
		// Display the news section
		var news = $("#news");
		$("#newsResults").removeClass("hidden");
		news.empty();
		for (var i = 0; i < data.length; i++) {
			if (data[i].title != null)
				news.append("<h3>" + data[i].title + "</h3>");
			if (data[i].description != null)
				news.append("<p>" + data[i].description + "</p>");
		}

	});
}

function getResults(token) {
	var keywords = getUrlParameter('keywords');
	var domain = getUrlParameter('domain');
	var url, params;

	if (keywords != null && keywords.length > 0) {

		url = "search/titles";

		// Build the request parameter
		var array = keywords.split(" ").filter(Boolean);
		keywords = array.join(",");

		params = {
			keywords : keywords,
			token : token
		};

	} else if (domain != null && domain.length > 0) {
		url = "search/domain";

		params = {
			domain : domain,
			token : token
		};

	} else {
		return;
	}

	// Make the request
	$.get(url, params, function(data, status) {

		// Display the result section
		var result = $("#searchResults");
		result.removeClass("hidden");
		var training = $("#training");
		var exercises = $("#exercises");
		training.empty();
		exercises.empty();
		for (var i = 0; i < data.length; i++) {

			for (var j = 0; j < data[i].values.length; j++) {
				var element = data[i].values[j];
				var link = "<a href=\"/rss\">" + element.title + "</a>";
				var duration = "<p>" + element.duration + "</p>";
				if (data[i].type === "training") {
					training.append(link);
					training.append(duration);
				} else {
					exercises.append(link);
					exercises.append(duration);
				}
			}
		}
	});
}

function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
			.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
}