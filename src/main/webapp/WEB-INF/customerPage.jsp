<%@ page import="CarportUtil.Initializer" %>
<%@ page import="FunctionLayer.LoginSampleException" %><%--
  Created by IntelliJ IDEA.
  User: monajakobmeshal
  Date: 4/22/20
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/headerCustomer.inc" %>

<div class="container2">
    <div class="col-md-12">
        <br>
        <br>
        <h2>
            <c:set var="msg" value="${requestScope.newRequestMSG}"/>
            <c:choose>
                <c:when test="${msg != null}">
                    ${msg}
                </c:when>
                <c:otherwise>
                    Velkommen til
                </c:otherwise>
            </c:choose></h2>

        <div class="col-md-6 school-options-dropdown text-center">

            <c:if test="${requestScope.orderMSG!= null}">
                <h2>${requestScope.orderReadyMSG}</h2>
   <!--
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
                    <td>${order.transport}</td>
                    <td>

                    <form name="validate" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="validate">
                    <input type="hidden" name="origin" value="newRequests">
                    <input type="hidden" name="orderID" value="${order.orderID}">
                    <input class="mt-3 mb-4 btn btn-outline-dark" type="submit" name="newrequest" value="Til validering">
                    </form>

                    </td>
                    </tr>
                </c:forEach>

                </tbody>
                </table>
     -->
            </c:if>
        </div>
        <img class="mt-4 mb-3" src="./images/logo.png" alt="Logo" width="430" height=auto>
        <h6 class="mb-3" style="font-size: small;">Her kan du designe din egen carport & <br>
            få vejledning, inden du køber</h6>
        <br>
        <a class=" mt-4 mb-3 btn btn-primary" href="FrontController?target=redirect&destination=carportBase"
           role="button">DESIGN CARPORT</a>
    </div>
</div>
<br>
<br>
<%@include file="../includes/footer.inc" %>