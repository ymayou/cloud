<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.lang.String"%> 
<%@ page import="java.util.ArrayList"%> 
<%@ page import="java.util.List"%> 
<%@ page import="Model.Exercise"%> 

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
    <script src="/Ressources/js/login.js" ></script>
    
    <title>Training's exercises</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  </head>
  <body>
  <!-- ------------------------------------------- HEADER ZONE ------------------------------------------- -->
  
  <nav class="navbar navbar-inverse" role="navigation">
  
     <div class=" col-md-10 col-sm-10 col-xs-10 ">
     	Nom du training
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
    
    	<div class=" col-md-1 col-sm-0 col-xs-0 " ></div>     
    	<div class=" col-md-11 col-sm-12 col-xs-12 " >
    		<table class="table table-striped table-condensed">
    			<%
				    ArrayList<Exercise> listProf = new ArrayList();
				    listProf = (ArrayList<Exercise>) request.getAttribute("listExercise");
				    
				    int i = 0;
				    String cmd;
					    for(Exercise e : listProf){
					    	if(i == 0){
					    		cmd = "insert";
					    	} else {
					    		cmd = "update";
					    	}
					    	i++;
		    	%>
		    				<tr>
		                        <td class=" col-md-12 col-sm-12 col-xs-12">
		                        <div class="row">
		                            <div class=" col-md-3 col-sm-12 col-xs-12 ">
		                                <h3><% out.print(e.getTitle()); %></h3>
		                            </div>
		                            <div class=" col-md-1 col-sm-2 col-xs-2 ">
		                                <p id="totalTimeValue" style="margin-top:25px"><span class="glyphicon glyphicon-time"></span> <% out.print(e.getDuration()); %></p>
		                            </div>
		                        </div>
		                        <div class="row">
		                            <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
		                            <div class=" col-md-6 col-sm-12 col-xs-12 ">
		                                <p><% out.print(e.getDescription()); %></p>
		                            </div>
		                            <div class=" col-md-3 col-sm-12 col-xs-12 ">
		                                <div class=" col-md-12 col-sm-12 col-xs-12 ">
		                                    <div id="flipcountdownbox1">                            
		                                    </div>
		                                </div>
		                                <div class=" col-md-12 col-sm-12 col-xs-12 centered">
		                                    <div class="btn-group">
		                                        <button type="button" class="btn btn-default " ><span class="glyphicon glyphicon-play"></span> </button>
		                                        <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-pause"></span> </button>
		                                        <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-stop"></span> </button>
		                                    </div>
		                                    <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-repeat"></span> </button>
		                                </div>
		                                
		                            </div>
		                        </div>
		                            <div class=" col-md-2 ol-sm-5 col-xs-12 text-center" >
		                                <button type="submit" class="btn btn-success btn-lg okExercice" data-cmd="<% out.print(cmd); %>" data-result="false" data-idTraining="${idTraining}"> <span class="glyphicon glyphicon-ok"></span> </button>    
		                                <button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-fast-forward"></span> </button>
		                            </div>
		                            
		                        </td>
		                    </tr>
		    	<%
					    	
					    }
				     
				%>
    		</table>
    	</div>
    
    <div class=" col-md-10 ol-sm-5 col-xs-0 " ></div>
    <div class=" col-md-2 ol-sm-5 col-xs-12 text-center" >
        <button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button>
        <button type="submit" class="btn btn-success btn-lg"> <span class="glyphicon glyphicon-ok"></span> </button>
    </div>
      
    </div>
    
    
    <div id="pagefooter">
      
    </div>
    
    <script src="Ressources/select/select2.min.js"></script>
    <script src="Rssources/flipcountdown/jquery.flipcountdown.js"></script>
    
    <script>
    
    $(document).ready(function(){
    	$( ".okExercice" ).click(function() {
    		var idTraining = $(this).data("idTraining");
    		var result = $(this).data("result");
    		var commande = $(this).data("cmd");
    		$.post("/personaldata",
			    {
    			    id: idTraining,
			        res: result,
			        cmd: commande,
			        time: "0:0:0"
			    },
			    function(data, status){
			        alert('ok');
			    });
   			alert( "Handler for .click() called." );
   		});
    });
    
    </script>
    
  </body>
</html>




