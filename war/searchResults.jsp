<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	
    <jsp:attribute name="header"></jsp:attribute>
        
    <jsp:attribute name="footer">
      <p id="copyright">Copyright 2016</p>
    </jsp:attribute>
    
    <jsp:body>
        <!-- Javascript for search handling -->
    	<script src="Ressources/js/Search.js"></script>
		
		<div id="searchResults" class="hidden">
			<h2>Training</h2>
			<div id="training"></div>
			<h2>Exercises</h2>
			<div id="exercises"></div>
		</div>
		
		<div id="newsResults" class="hidden">
			<h2>News</h2>
			<div id="news"></div>
		</div>
        
    </jsp:body>
</t:genericpage>
