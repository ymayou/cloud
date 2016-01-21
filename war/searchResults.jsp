<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>

	<jsp:attribute name="header">
     <form method="get" action="/searchResults.jsp"
			class="navbar-form navbar-left" style="width: 100%" role="search">
             <button type="submit" class="btn btn-default">Search</button>
             <div class="form-group" style="width: 90%">
                 <input type="text" style="width: 100%"
					class="form-control" placeholder="Search" name="keywords">
             </div>
       </form>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <p id="copyright">Copyright 2016</p>
    </jsp:attribute>

	<jsp:body>
        <!-- Javascript for search handling -->
    	<script src="Ressources/js/Search.js"></script>
		
		<div id="searchResults" class="hidden">
			<h2 id="trainingTitle">Training</h2>
			<div id="training"></div>
			<h2 id="exercisesTitle">Exercises</h2>
			<div id="exercises"></div>
		</div>
		
		<div id="newsResults" class="hidden">
			<h2>News</h2>
			<div id="news"></div>
		</div>
        
    </jsp:body>
</t:genericpage>
