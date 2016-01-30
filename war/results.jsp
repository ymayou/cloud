<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
        <span style="color: white">
            <h1>
                <a href="/index">
                    <span class="glyphicon glyphicon-home" aria-hidden="true">
                    </span>
                </a>
                Personal Data
            </h1>
        </span>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        ${error}
        <table id="tabResults" class="table table-striped">
            <thead>
            <tr>
                <th>Date</th>
                <th>Training Plan</th>
                <th>Expected</th>
                <th>Time</th>
                <th>Completed</th>
            </tr>
            </thead>
            <tbody id="resultTableBody">
                ${result}
            </tbody>
        </table>
    </jsp:body>
</t:genericpage>