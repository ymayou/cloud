<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="23620194878-afclr41aopnkhg8s8qp4qmt72oktho8q.apps.googleusercontent.com">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="../../Ressources/js/login.js" ></script>
    
    <title>Personal Trainer</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  </head>
  <body>
  <!-- ------------------------------------------- HEADER ZONE ------------------------------------------- -->
  
  <nav class="navbar navbar-inverse" role="navigation">
  
     <div class=" col-md-10 col-sm-10 col-xs-10 ">
        <jsp:invoke fragment="header"/>
     </div>
       
     <div class=" col-md-2 col-sm-2 col-xs-2 ">
         <%
	        Cookie[] cookies = request.getCookies();
	        boolean foundCookie = false;
	        String nameUser = "";
	        String emailUser = "";
	        
	        if (cookies != null && cookies.length > 0) {
	
		        for(int i = 0; i < cookies.length; i++) { 
		            Cookie c = cookies[i];
		            if (c.getName().equals("name_user")) {
		                foundCookie = true;
		                nameUser = c.getValue();
		            }
		            if (c.getName().equals("email_user")) {
		            	emailUser = c.getValue();
		            }
		        }  
	        }
		
	        if (foundCookie) {
	    %>
	    	<a class="btn btn-default" href="#" onclick="signOut();">Sign out</a>
	    	<div class="alert alert-success" role="alert">
	    		<%= nameUser.replace("%20", " ") %>
	    		<%= emailUser.toString() %>
			</div>
	    	<div id="btnConnect" class="g-signin2" data-onsuccess="onSignIn" data-theme="dark" style="display: none;"></div>
	    <%
	        } else {
	    %>
	    	<div id="btnConnect" class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
	    	
	    <%
	        }
	    %> 
     </div>
   </nav>
  
      <!-- ------------------------------------------- HEADER ZONE END ------------------------------------------- -->
  
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter">
		<jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>
