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
        <ul class="breadcrumb col-sm-offset-2">
            <li><a class="breadcrumb-item" href="<c:url value="/home"/>">Home</a></li>
            <li><a class="breadcrumb-item" href="<c:url value="/estoque/produto/lista"/>">Produtos</a></li>
            <li><span class="breadcrumb-item active">${produto.nome}</span></li>
        </ul>

        <h1 align="center">${produto.nome}</h1>


        <div class="col-sm-offset-2">
            <ul class="list-group col-sm-10">
                <li class="list-group-item"><b>PRODUTO:</b> ${produto.nome}</li>
                <li class="list-group-item"><b>QUANTIDADE:</b> ${produto.quantidade}</li>
                <li class="list-group-item"><b>QUANTIDADE MAXIMA:</b> ${produto.quantidadeMaxima}</li>
                <li class="list-group-item"><b>QUANTIDADE MINIMA:</b> ${produto.quantidadeMinima}</li>
                <li class="list-group-item"><b>UNIDADE DE MEDIDA:</b> ${produto.unidadeMedida}</li>
            </ul>
        </div>

        <c:if test="${empty registroList}">
            <a class="btn btn-primary col-sm-offset-2" href="<c:url value="/estoque/produto/${produto.id}/lote/novo"/>"><span class="glyphicon glyphicon-plus"></span> Adicionar</a>
        </c:if>

        <h1 align="center">Lotes</h1>
            
        <c:if test="${not empty loteList}">

            <table class="table table-striped col-sm-offset-2">
                <thead>
                    <tr>

                        <th>Lote:</th>
                        <th>Validade:</th>
                        <th>Quantidade:</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${loteList}" var="lote">
                        <tr>
                            <td>${lote.id}</td>
                            <td><fmt:formatDate value="${lote.validade}" pattern="dd/MM/yyyy"/></td>
                            <td>${lote.quantidade}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

        <jsp:include page="../../modais.jsp"/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    </body>
</html>
