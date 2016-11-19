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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="<c:url value="/js/html5shiv.min.js"/>"></script>
            <script src="<c:url value="/js/respond.min.js"/>"></script>
        <![endif]-->
    </head>
    <body>
        
        <jsp:include page="../../navusuariolista.jsp"/>
        
        <h1 align="center">${instituicao.nome}</h1>
        
        
        <div class="col-sm-offset-1">
            <ul class="list-group col-sm-10">
                <li class="list-group-item"><b>CNPJ:</b> ${instituicao.cnpj}</li>
                <li class="list-group-item"><b>Telefone:</b> ${instituicao.telefone}</li>
                <li class="list-group-item"><b>E-mail:</b> ${instituicao.email}</li>
                <li class="list-group-item"><b>Rua:</b> ${instituicao.rua}</li>
                <li class="list-group-item"><b>Número:</b> ${instituicao.numero}</li>
                <li class="list-group-item"><b>Bairro:</b> ${instituicao.bairro}</li>
                <li class="list-group-item"><b>Município:</b> ${instituicao.cidade}</li>
                <li class="list-group-item"><b>CEP:</b> ${instituicao.cep}</li>
                <li class="list-group-item"><b>Estado:</b> ${instituicao.estado}</li>
            </ul>
        </div>
        
        <jsp:include page="../../modais.jsp"/>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    </body>
</html>