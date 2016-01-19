<%--
  Created by IntelliJ IDEA.
  User: You
  Date: 19/01/2016
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <title>Add training plan</title>
</head>
<body>

<form role="form">
    <div class="row" style="margin-bottom:10px">
        <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
        <div class=" col-md-2 col-sm-2 col-xs-12 ">
            <label for="inputTitle">Title</label>
        </div>
        <div class=" col-md-6 col-sm-6 col-xs-12 ">
            <input type="email" class="form-control" id="inputTitle" placeholder="Enter your Training plan title">
        </div>
        <div class=" col-md-3 col-sm-4 hidden-xs">
            <div class=" col-md-4 col-sm-4 hidden-xs">
                <p id="totalTimeValue"><span class="glyphicon glyphicon-time"></span>0:00:00</p>
            </div>
        </div>
    </div>

    <div class="row">
        <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
        <div class=" col-md-2 col-sm-2 col-xs-12 ">
            <label for="inputDescription">Description</label>
        </div>
        <div class=" col-md-6 col-sm-6 col-xs-12 ">
            <textarea id="inputDescription" class="form-control" rows="4"></textarea>
        </div>
    </div>

    <div class="row" style="margin-top:10px">
        <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
        <div class=" col-md-2 col-sm-2 col-xs-12 ">
            <label for="inputDescription">Domain</label>
        </div>
        <div class=" col-md-6 col-sm-6 col-xs-12 " >
            <p>
                <select id="e1" class="select2-container populate" style="width:100%">
                    <option value="Running">Running</option>
                    <option value="Volleyball">Volleyball</option>
                    <option value="Boxe">Boxe</option>
                    <option value="BaseBall">BaseBall</option>
                </select>
            </p>
        </div>
    </div>

    <div class="row">
        <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
        <div class=" col-md-9 col-sm-12 col-xs-12 " >
            <div class="jumbotron" style="padding:10px">
                <div class="row">
                    <div class="col-md-2 col-sm-2 col-xs-12 col-md-offset-2">
                        <label for="titleDescription">Ex. Title</label>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12 ">
                        <input type="text" class="form-control" id="titleDescription" placeholder="Enter your exercice title">
                    </div>
                </div>
                <div class="row" style="margin-top:10px">
                    <div class=" col-md-2 col-sm-2 col-xs-12 col-md-offset-2">
                        <label for="exerciceDescription">Ex. Description</label>
                    </div>
                    <div class=" col-md-6 col-sm-6 col-xs-12 ">
                        <!--<input type="email" class="form-control" id="exerciceDescription" placeholder="Enter your exercice title">-->
                        <textarea id="exerciceDescription" class="form-control" rows="4"></textarea>
                    </div>
                </div>

                <div class="row" style="margin-top:10px">
                    <div class=" col-md-2 col-sm-4 hidden-xs col-md-offset-2">
                        <p style="text-align: center;"> <span class="glyphicon glyphicon-time"></span> </p>
                    </div>
                    <div class=" col-md-6 col-sm-6 col-xs-12 ">
                        <div class="input-group">
                            <input id="hour" type="number" min="0" step="1" max="60" class="form-control"/>
                            <span class="input-group-addon" style="padding:0px">:</span>
                            <input id="minute" type="number" min="0" step="1" max="60" class="form-control"/>
                            <span class="input-group-addon" style="padding:0px">:</span>
                            <input id="sec" type="number" min="0" step="1" max="60" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-top:10px">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <button id="btnAddExercise" type="button" class="btn btn-success center-block">Add an Exercice</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
    <div class=" col-md-9 col-sm-12 col-xs-12 " >
        <table id="exerciceTable" class="table table-striped table-condensed">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Duration</th>
                    <th></th>
                </tr>
            </thead>
            <tbody id="excerciseTableBody"></tbody>
        </table>
    </div>

    <div class=" col-md-2 ol-sm-0 col-xs-0 " ></div>
    <div class=" col-md-10 ol-sm-5 col-xs-0 " ></div>
    <div class=" col-md-2 ol-sm-5 col-xs-12 text-center" >
        <button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button>
        <button type="submit" class="btn btn-success btn-lg"> <span class="glyphicon glyphicon-ok"></span> </button>
    </div>
</form>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<script src ="/Ressources/js/excercise.js"></script>
</html>
