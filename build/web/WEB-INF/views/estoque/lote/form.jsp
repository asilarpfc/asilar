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
            <li><a class="breadcrumb-item" href="<c:url value="/estoque/produto/${produto.id}/info"/>">${produto.nome}</a></li>
            <li><span class="breadcrumb-item active">Novo Lote</span></li>
        </ul>
        <h1 align="center">${produto.nome}</h1>

        <form class="form-horizontal col-lg-offset-2" method="post" onsubmit="return fctValidaData(datNasc.value, 2);">
            <input type="hidden" name="id" value="${produto.id}">

            <div class="form-group <c:if test="${not empty errors.validade}">has-error has-feedback</c:if>">
                    <label for="validade" class="col-sm-2 control-label">Validade:</label>
                    <div class="col-sm-6" id="divDataNasc">
                        <input id="datNasc" type="text" name="validade" class="form-control" placeholder="validade" value="<fmt:formatDate value="${lote.validade}" pattern="dd/MM/yyyy"/>" maxlength="10">
                    <c:if test="${not empty errors.validade}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.validade}"><span class="alert-danger">${errors.validade}</span></c:if>
                    </div>
                </div>


                <div class="form-group <c:if test="${not empty errors.quantidade}">has-error has-feedback</c:if>">
                    <label for="quantidade" class="col-sm-2 control-label">Quantidade: (${produto.unidadeMedida})</label>
                    <div class="col-sm-6">
                        <input type="number" name="quantidade" class="form-control" placeholder="Quantidade" value="${lote.quantidade}" maxlength="30">
                    <c:if test="${not empty errors.quantidade}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.quantidade}"><span class="alert-danger">${errors.quantidade}</span></c:if>
                    </div>
                </div>

                


                                

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Cadastrar</button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal-cancelar"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                    </div>
                </div>

            </form>

        <jsp:include page="../../modais.jsp"/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/js/jquery.mask.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <jsp:include page="../../scripts.jsp"/>
    </body>
</html>
