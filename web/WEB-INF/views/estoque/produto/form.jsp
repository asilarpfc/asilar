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
        <h1 align="center">Cadastrar Produto</h1>

        <form class="form-horizontal col-lg-offset-2" method="post">
            <input type="hidden" name="id" value="${produto.id}">

            <div class="form-group <c:if test="${not empty errors.produto}">has-error has-feedback</c:if>">
                    <label for="nome" class="col-sm-2 control-label">Nome:</label>
                    <div class="col-sm-6">
                        <input type="text" name="nome" class="form-control" placeholder="Nome" value="${produto.nome}" maxlength="60" required="">
                    <c:if test="${not empty errors.produto}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.produto}"><span class="alert-danger">${errors.produto}</span></c:if>
                    </div>
                </div>


                <div class="form-group <c:if test="${not empty errors.quantidade_minima}">has-error has-feedback</c:if>">
                    <label for="quantidadeMinima" class="col-sm-2 control-label">Quantidade Minima:</label>
                    <div class="col-sm-6">
                        <input type="text" name="quantidadeMinima" class="form-control" placeholder="QUANTIDADE_MINIMA" value="${produto.quantidadeMinima}" maxlength="30" required="" onfocus="$(this).mask('000.000.000-00', {reverse: true});">
                    <c:if test="${not empty errors.quantidade_minima}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.quantidade_minima}"><span class="alert-danger">${errors.quantidade_minima}</span></c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label for="quantidadeMaxima" class="col-sm-2 control-label">Quantidade Máxima:</label>
                    <div class="col-sm-6">
                        <input type="text" name="quantidadeMaxima" class="form-control" placeholder="QUANTIDADE_MAXIMA" value="${produto.quantidadeMaxima}" maxlength="30" required="">
                    <c:if test="${not empty errors.rg}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.rg}"><span class="alert-danger">${errors.quantidade_maxima}</span></c:if>
                    </div>
                </div>


                <div class="form-group">
                    <label for="unidadeMedida" class="col-sm-2 control-label">Unidade De Medida :</label>
                    <div class="col-sm-6">
                        <input id="unidade_medida" type="text" name="unidadeMedida" class="form-control" placeholder="UNIDADE_MEDIDA" maxlength="20" value="${produto.unidadeMedida}" required="">
                    <c:if test="${not empty errors.unidade_medida}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.unidade_medida}"><span class="alert-danger">${errors.unidade_medida}</span></c:if>
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
