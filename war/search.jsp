<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	
	

    <jsp:attribute name="header">
    
    	<div id="btnConnect" class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
    	
    	
    	<a href="#" onclick="signOut();">Sign out</a>
    	
    </jsp:attribute>
    
    
    <jsp:attribute name="footer">
      <p id="copyright">Csearch</p>
    </jsp:attribute>
    <jsp:body>
        <p>search</p>
        
    </jsp:body>
</t:genericpage>