<%--
        Created by IntelliJ IDEA.
        User: magda
        Date: 15-05-2020
        Time: 19:57
        To change this template use File | Settings | File Templates.
        --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/headerEmployee.inc" %>


<h1>Wall drawigs</h1>
<div class="col-md-6">
    <div class="col-md-6 text-center mt-md-4">
        <a class="btn btn-dark mt-3" href="FrontController?target=redirect&destination=prepareOffer">
            Tilbage til Ordre detaljer</a>
    </div>
</div>
<br>
<br>
${requestScope.wallsFromAbove}
<%@include file="../includes/footer.inc" %>

