<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Test</title>
</head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	<link rel="stylesheet" href="Ressources/css/search.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Javascript for search handling -->
    <script src="Ressources/js/Search.js"></script>
<body>
	<input type="text" id="searchInput"/>
	<input type="button" value="Search" id="searchButton"/>
	
	<div id="searchResults" class="hidden">
		<h2>Training</h2>
		<div id="training"></div>
		<h2>Exercises</h2>
		<div id="exercises"></div>
	</div>
	
	<div id="newsResults" class="hidden">
		<h2>News</h2>
	</div>
</body>
</html>