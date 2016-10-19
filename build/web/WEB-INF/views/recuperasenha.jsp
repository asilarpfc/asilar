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
            
            <img src="<c:url value="/img/logo.jpg"/>" class="img-responsive center-block">
                    
                  
            <form class="form-signin" method="post">
                <label for="email" class="sr-only">Login</label>
                <input type="email" name="email" class="form-control" placeholder="E-Mail:" required autofocus>
                <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Enviar</button>
            </form>
            <c:if test="${not empty erro}"><h5 align="center">${erro}</h5></c:if>

        </div> <!-- /container -->


        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
    </body>

</html> 
