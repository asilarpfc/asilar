<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="<c:url value="/img/logo.jpg"/>">
        <title>Asilar</title>

        <!-- Bootstrap -->
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="<c:url value="/css/signin.css"/>" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="<c:url value="/js/html5shiv.min.js"/>"></script>
          <script src="<c:url value="/js/respond.min.js"/>"></script>
        <![endif]-->
    </head>
    <body>

        <div class="container">

            <c:if test="${not empty usuarioLogado.nome}">
                <ul class="breadcrumb col-sm-offset-2">
                    <li><a class="breadcrumb-item" href="<c:url value="/home"/>">Home</a></li>
                    <li><a class="breadcrumb-item" href="<c:url value="/cadastro/usuario/lista"/>">Usuários</a></li>
                    <li><span class="breadcrumb-item active">Redefinir</span></li>
                </ul>
            </c:if>

            <img src="<c:url value="/img/logo.jpg"/>" class="img-responsive center-block">

            <h1 align="center">Usuario: ${usuario}</h1>     
            <form class="form-signin" method="post">
                <label for="senha" class="sr-only">Senha:</label>
                <input type="password" name="senha" class="form-control" placeholder="Senha" required autofocus>
                <br>
                <label for="confirma" class="sr-only">Confirmar senha</label>
                <input type="password" class="form-control" placeholder="Repetir senha" name="confirma" required>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Redefinir</button>
            </form>
            <h5 align="center">${erro}</h5>

        </div> <!-- /container -->


        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
    </body>

</html>