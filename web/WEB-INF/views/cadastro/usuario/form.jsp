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
        <jsp:include page="../../navusuarioform.jsp"/>
        <h1 align="center">Cadastrar Usuario</h1>

        <form class="form-horizontal" method="post">
            <input type="hidden" name="id" value="${usuario.id}">
           
            <div class="form-group">
                <label for="nome" class="col-sm-2 control-label">Nome:</label>
                <div class="col-sm-6">
                    <input type="text" name="nome" class="form-control" placeholder="Nome" value="${usuario.nome}" required="">
                    <c:if test="${not empty errors.nome}"><span>${errors.nome}</span></c:if>
                </div>
            </div>
                
            <div class="form-group">
                <label for="login" class="col-sm-2 control-label">Login:</label>
                <div class="col-sm-6">
                    <input type="text" name="login" class="form-control" placeholder="Login" value="${usuario.login}" required="">
                    <c:if test="${not empty errors.login}"><span>${errors.login}</span></c:if>
                </div>
            </div>
                
            <div class="form-group">
                <label for="senha" class="col-sm-2 control-label">Senha:</label>
                <div class="col-sm-6">
                    <input type="password" name="senha" class="form-control" placeholder="Senha" value="${usuario.senha}" required="">
                    <c:if test="${not empty errors.senha}"><span>${errors.senha}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="rg" class="col-sm-2 control-label">RG:</label>
                <div class="col-sm-6">
                    <input type="text" name="rg" class="form-control" placeholder="RG" value="${usuario.rg}" required="">
                    <c:if test="${not empty errors.rg}"><span>${errors.rg}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="cpf" class="col-sm-2 control-label">CPF:</label>
                <div class="col-sm-6">
                    <input type="text" name="cpf" class="form-control" placeholder="CPF" value="${usuario.cpf}" required="">
                    <c:if test="${not empty errors.cpf}"><span>${errors.cpf}</span></c:if>
                </div>
            </div>
            
            <div class="form-group">
                <label for="telfixo" class="col-sm-2 control-label">Telefone Fixo:</label>
                <div class="col-sm-6">
                    <input type="text" name="telfixo" class="form-control" placeholder="Telefone Fixo" value="${usuario.telfixo}" required="">
                    <c:if test="${not empty errors.telfixo}"><span>${errors.telfixo}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="celular" class="col-sm-2 control-label">Celular:</label>
                <div class="col-sm-6">
                    <input type="text" name="celular" class="form-control" placeholder="Celular" value="${usuario.celular}" required="">
                    <c:if test="${not empty errors.celular}"><span>${errors.celular}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">E-Mail:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="Celular" value="${usuario.email}" required="">
                    <c:if test="${not empty errors.email}"><span>${errors.email}</span></c:if>
                </div>
            </div>
                
            <div class="form-group">
                <label for="tipoUsuario" class="col-sm-2 control-label">Tipo de usuario:</label>
                <div class="col-sm-6">
                    <select class="form-control" name="tipoUsuario" required="">
                        <c:if test="${empty usuario.tipoUsuario}"><option value="">Selecione...</option></c:if>
                        <option value="1" <c:if test="${usuario.tipoUsuario eq 1}"> selected </c:if>>Administrador</option>
                        <option value="2" <c:if test="${usuario.tipoUsuario eq 2}"> selected </c:if>>Gerente de Estoque</option>
                        </select>
                </div>
             </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Cadastrar</button>
                    <button type="button" class="btn btn-danger btnCancelar"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                </div>
            </div>

        </form>
                        
        <jsp:include page="../../modais.jsp"/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <jsp:include page="../../scripts.jsp"/>
    </body>
</html>