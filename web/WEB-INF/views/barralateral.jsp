<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-lg-2">
    <br><br>
    <ul class="nav nav-pills nav-stacked">
        <li role="presentation" <c:if test="${ativo eq 'usuario'}">class="active"</c:if>><a href="<c:url value="/cadastro/usuario/lista"/>"/>Usuário</a></li>
        <li role="presentation" <c:if test="${ativo eq 'assistido'}">class="active"</c:if>><a href="<c:url value="/cadastro/assistido/lista"/>">Assistido</a></li>
    </ul>
</div>