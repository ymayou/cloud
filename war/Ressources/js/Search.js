var searchButton = "#searchButton";
var searchInput = "#searchInput";

$(document).ready(function() {
	$(searchButton).click(function() {

		// Get the search request
		var keywords = $(searchInput).val();
		if (keywords.length > 0) {

			// Build the request parameter
			var array = keywords.split(" ").filter(Boolean);
			keywords = array.join(",");
			console.log(array);

			// Make the request
			$.get("search", {
				keywords : keywords
			}, function(data, status) {
				console.log(data);
				
				// Display the result section
				var result = $("#searchResults");
				result.removeClass("hidden");
				var training = $("#training");
				var exercises = $("#exercises");
				training.empty();
				exercises.empty();
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
					console.log(data[i].values.length)
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
		
		// Look for RSS feed
		$.get("rss", {}, function(data, status) {		
			// Display the news section
			var news = $("#newsResults");
			news.removeClass("hidden");
			news.empty();
			for (var i = 0; i < data.length; i++) {
				news.append("<h3>" + data[i].title + "</h3>");
				news.append("<p>" + data[i].description + "</p>");
			}

		});
	});
});