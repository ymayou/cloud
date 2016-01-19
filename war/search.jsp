<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="header">
      	<div class=" col-md-10 col-sm-10 col-xs-10 ">
            <form class="navbar-form navbar-left"  style="width:100%" role="search">
                <button type="submit" class="btn btn-default">Search</button>
                <div class="form-group"  style="width:90%">
                    <input type="text"  style="width:100%" class="form-control" placeholder="Search">
                </div>
          </form>
        </div>
          
        <div class=" col-md-2 col-sm-2 col-xs-2 ">
            <form class="navbar-form navbar-left"  style="width:100%" role="search">
                <button type="submit" class="btn btn-primary">G</button>
                <button type="submit" class="btn btn-success">Y</button>
                <button type="submit" class="btn btn-warning">O</button>
          </form>
        </div>
    </jsp:attribute>
    
    
    <jsp:attribute name="footer">
      <p id="copyright">Csearch</p>
    </jsp:attribute>
    <jsp:body>
        <p>search</p>
    </jsp:body>
</t:genericpage>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

  </head>
  <body>
    <h1>Hello, world!</h1>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>