<%--
  Created by IntelliJ IDEA.
  User: magda
  Date: 11-05-2020
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/headerEmployee.inc" %>

<div class="container2">
    <div class="col-md-12">

        <h2>Sendte Tilbud</h2>


        <form name="showrequests" action="FrontController" method="POST">
            <input type="hidden" name="target" value="showrequests">
            <input type="hidden" name="origin" value="newRequests">
            <input class="mt-3 mb-4 btn btn-outline-dark" type="submit" name="sentoffers" value="Opdater fra databasen">
        </form>

        <table class="table table-striped ">
            <thead>
            <tr class="tr1">
                <th>Ordre nr.</th>
                <th>Dato</th>
                <th>Pris</th>
                <th>Transportomkostninger</th>
                <th> </th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${applicationScope.sentOffersList}">
                <tr class="tr2">
                    <td>${order.orderID}</td>
                    <td>${order.date}</td>
                    <td>${order.salePrice}</td>
                    <td>${order.cost}</td>
                    <td>${order.coverage}</td>
                    <td>${order.transport}</td>
                    <td>
<!--
                        <form name="validate" action="FrontController" method="POST">
                            <input type="hidden" name="target" value="validate">
                            <input type="hidden" name="origin" value="newRequests">
                            <input type="hidden" name="orderID" value="${order.orderID}">
                            <input class="mt-3 mb-4 btn btn-outline-dark" type="submit" name="newrequest" value="Til validering">
                        </form>
-->
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<a href="FrontController?target=redirect&destination=employeePage">Tilbage</a>


<%@include file="../includes/footer.inc" %>