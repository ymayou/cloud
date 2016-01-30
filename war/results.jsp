<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
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