<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <form role="form" id ="addPlan">
            <div class="row" style="margin-bottom:10px">
                <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
                <div class=" col-md-2 col-sm-2 col-xs-12 ">
                    <label for="inputTitle">Title</label>
                </div>
                <div class=" col-md-6 col-sm-6 col-xs-12 ">
                    <input type="text" class="form-control" id="inputTitle" placeholder="Enter your Training plan title">
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
                                    <input id="hour" type="number" min="0" step="1" max="60" class="form-control" placeholder="hour"/>
                                    <span class="input-group-addon" style="padding:0px">:</span>
                                    <input id="minute" type="number" min="0" step="1" max="60" class="form-control" placeholder="min"/>
                                    <span class="input-group-addon" style="padding:0px">:</span>
                                    <input id="sec" type="number" min="0" step="1" max="60" class="form-control" placeholder="sec"/>
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
                <input id="btCancel" type="button" class="btn btn-danger btn-sm" value="Cancel"/>
                <input id="btSave" type="submit" class="btn btn-success btn-lg"/>
            </div>
        </form>

        <script src="/Ressources/js/jquery.validate.min.js"></script>
        <script src ="/Ressources/js/excercise.js"></script>
    </jsp:body>
</t:genericpage>