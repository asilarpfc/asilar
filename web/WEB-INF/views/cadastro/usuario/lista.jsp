<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="<c:url value="/img/logo.jpg"/>">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Asilar</title>

        <!-- Bootstrap -->
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/css/estilos.css"/>" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="<c:url value="/js/html5shiv.min.js"/>"></script>
            <script src="<c:url value="/js/respond.min.js"/>"></script>
        <![endif]-->
    </head>
    <body>
        
        <jsp:include page="../../navusuariolista.jsp"/>
        <jsp:include page="../../barralateral.jsp"/>
        <ul class="breadcrumb col-sm-offset-2">
            <li><a class="breadcrumb-item" href="<c:url value="/home"/>">Home</a></li>
            <li><span class="breadcrumb-item active">Usuários</span></li>
        </ul>
        <h1>Usuários</h1>
        
        <a class="btn btn-primary" href="<c:url value="/cadastro/usuario/novo"/>"><span class="glyphicon glyphicon-plus"></span> Adicionar</a>

        <div class="table-responsive col-sm-offset-2">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nome:</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${usuarioList}" var="usuario">
                        <tr>
                            <td><a href="<c:url value="/cadastro/usuario/${usuario.id}/info"/>">${usuario.nome}</a></td>
                            <td><a class="btn btn-primary" href="<c:url value="/cadastro/usuario/${usuario.id}/alterar"/>"><span class="glyphicon glyphicon-edit"></span> Alterar</a></td>
                            <td><button class="btn btn-danger" href="" onclick="$('#confirma-exclusao').attr('href', '<c:url value="/cadastro/usuario/${usuario.id}/excluir"/>')" data-toggle="modal" data-target="#modal-confirmar"><span class="glyphicon glyphicon-trash"></span> Excluir</button></td>
                            <c:if test="${usuarioLogado.tipoUsuario eq 1}"><td><a class="btn btn-primary" href="<c:url value="/redefinir/${usuario.id}/${usuario.senha}"/>"><span class="glyphicon glyphicon-user"></span> Redefinir</a></td></c:if>
                        </tr>
                    </c:forEach>
                </tbody>
           </table>
            <c:if test="${count > 10}">
                <nav>
                <ul class="pagination">
                    <li <c:if test="${offset <= 0}"> class="disabled" </c:if>>
                        <a <c:if test="${offset > 0}">href="<c:url value="/cadastro/usuario/lista?offset=${offset - 10}"/>"</c:if>aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                        <c:forEach var="conta" begin="0" step="10" end="${count -1}">
                            <li <c:if test="${offset == conta}">class="active"</c:if>><a href="<c:url value="/cadastro/usuario/lista?offset=${conta}"/>"><fmt:formatNumber value="${(conta + 10) / 10}" pattern="#"/></a></li>
                        </c:forEach>
                    <li <c:if test="${offset > count - 10}"> class="disabled" </c:if>>
                        <a <c:if test="${offset < count - 10}"> href="<c:url value="/cadastro/usuario/lista?offset=${offset + 10}"/>" </c:if> aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        
        </div>
        
        <jsp:include page="../../modais.jsp"/>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    </body>
</html>