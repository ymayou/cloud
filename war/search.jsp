<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:genericpage>
	
	

    <jsp:attribute name="header">
     <form method="get" action="/searchResults.jsp" class="navbar-form navbar-left"  style="width:100%" role="search">
             <button type="submit" class="btn btn-default">Search</button>
             <div class="form-group"  style="width:90%">
                 <input type="text"  style="width:100%" class="form-control" placeholder="Search" name="keywords">
             </div>
       </form>
    </jsp:attribute>
    
    
    <jsp:attribute name="footer">
       <!-- ------------------------------------------- FOOTER ZONE ------------------------------------------- -->
      <nav class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
        <div class=" col-md-10 col-sm-10 col-xs-10 ">
            <form class="navbar-form navbar-left"  style="width:100%" role="search">
                <a href="/addPlan" type="submit" class="btn btn-danger"> <span class="glyphicon glyphicon-plus-sign"></span> </a>
               <label style="color:white">Add a Training Plan</label>
          </form>
        </div>
      </nav>
      <!-- ------------------------------------------- FOOTER ZONE END ------------------------------------------- -->
    </jsp:attribute>
    <jsp:body>
        <div class=" col-md-12 col-sm-12 col-xs-12 " style="margin-bottom:40px">
          <h3> Your Domain</h3>
      </div>
      
      <div class=" col-md-12 col-sm-12 col-xs-12" style="margin-bottom:80px">
          <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=run" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Run </label>
          </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=fitness" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Fitness </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=swimming" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Swimming </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=tennis" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Tennis </label>
           </div>
       </div>
      
      <div class=" col-md-12 col-sm-12 col-xs-12 " style="margin-bottom:80px">
          <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=box" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Box </label>
          </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
                    <a href="#?domain=soccer" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Soccer </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=soccer" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Soccer </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=pingpong" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Ping Pong </label>
           </div>
       </div>
      
      <div class=" col-md-12 col-sm-12 col-xs-12 ">
          <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=voleyball" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Voley Ball </label>
          </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=baseball" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Baseball </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=drink" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> Drink </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <a href="/searchResults.jsp?domain=basketball" type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></a> <label> BasketBall </label>
           </div>
       </div>
        
    </jsp:body>
</t:genericpage>