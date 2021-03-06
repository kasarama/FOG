
<%--
Created by IntelliJ IDEA.
User: monajakobmeshal
Date: 10/05/20
Time: 17:03
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="../includes/headerCustomer.inc" %>

<div class="row">


    <div class="col-md-4"></div>
    <div class="col-md-4">
        <h4 class="text-center">Du har valgt:</h4>

        <!-- *** NOTE: attributes are on session level so sessionScope is used.  *** -->

        <!-- ** Creating a table that will contain rows/columns to display the chosen construction ** -->
        <!-- "table"  puts the table in a table form (with dividers) -->
        <!-- "table-striped" makes the table grey and white (stripes-pattern) -->
        <table class="table table-striped">
            <tr>
                <td>Carport længde:</td>
                <!-- getting the "attributeName" attribute from the "Frontcontroller" who got it from "Class" -->
                <td>${sessionScope.carportBase.carportLength}</td>
            </tr>

            <tr>
                <td>Carport bredde:</td>
                <td>${sessionScope.carportBase.carportWidth}</td>
            </tr>
            <tr>
                <td>Tagtype:</td>
                <td>${sessionScope.carportBase.roof.optionForTypeRoof()}</td>
            </tr>

            <tr>
                <td>Tagdækning:</td>
                <td>${sessionScope.carportBase.roof.cover}</td>
            </tr>


            <td>Beklæd væg:</td>
            <td>
                <c:forEach var="wallSide" items="${sessionScope.carportBase.wallsToDanish()}">
                    ${wallSide}
                </c:forEach>
            </td>

            <tr>
                <td>Beklædning til væg:</td>
                <td>${sessionScope.carportBase.color} ${sessionScope.carportBase.overlay}</td>
            </tr>

            <tr>
                <td>Skur placering:</td>
                <td>${sessionScope.carportBase.shed.side}</td>
            </tr>

            <tr>
                <td>Skur bredde:</td>
                <td>${sessionScope.carportBase.shed.width}</td>
            </tr>

            <tr>
                <td>Skur dybde:</td>
                <td>${sessionScope.carportBase.shed.depth}</td>

            </tr>
        </table>

        <!-- ** Forms ** -->

        <form name="sendnewrequest" action="FrontController" method="POST">
            <input type="hidden" name="target" value="sendnewrequest">
            <input type="hidden" name="origin" value="customerChoiceResult">
            <input class="mt-3 mb-4 btn btn-outline-dark" type="submit" name="sendnewrequest"
                   value="Send forespørgelse">
        </form>


        <form name="startOver" action="FrontController" method="POST">
            <input type="hidden" name="target" value="newrequest">
            <input type="hidden" name="origin" value="customerChoiceResult">
            <input class="mt-3 mb-4 btn btn-outline-dark" type="submit" name="newrequest" value="Start forfra">
        </form>

        <form name="drawingCustomer" action="FrontController" method="POST">
            <input type="hidden" name="target" value="drawingCustomer">
            <input type="hidden" name="origin" value="customerChoiceResult">
            <input class="btn btn-primary mt-3" type="submit" name="svgSketch" value="Se din carport">
        </form>


    </div>
    <div class="col-md-4"></div>
</div>
<!-- row closing tag -->

<%@include file="../includes/footer.inc" %>