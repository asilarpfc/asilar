<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <li><a class="breadcrumb-item" href="<c:url value="/cadastro/assistido/lista"/>">Cadastrados</a></li>
            <li><span class="breadcrumb-item active">${assistido.nome}</span></li>
        </ul>

        <ul class="nav nav-tabs">
            <li <c:if test="${tab eq 'info'}"> class="active"</c:if>><a href="<c:url value="/cadastro/assistido/${assistido.id}/info"/>">Informações</a></li>
            <li <c:if test="${tab eq 'registro'}"> class="active"</c:if>><a href="<c:url value="/cadastro/assistido/${assistido.id}/registro"/>">Registro</a></li>
        </ul>


        <h1 align="center">${assistido.nome}</h1>
        

        <div class="col-sm-offset-2">
            
            <ul class="list-group col-sm-10">
                <li class="list-group-item"><b>Procedência:</b> ${assistido.procedencia}</li>
                <li class="list-group-item"><b>Estado Civil:</b> ${assistido.estadoCivil}</li>
                <li class="list-group-item"><b>Sexo:</b> ${assistido.sexo}</li>
                <li class="list-group-item"><b>Mãe:</b> ${assistido.mae}</li>
                <li class="list-group-item"><b>Pai:</b> ${assistido.pai}</li>
                <li class="list-group-item"><b>CPF:</b> ${assistido.cpf}</li>
                <li class="list-group-item"><b>RG:</b> ${assistido.rg}</li>
                <li class="list-group-item"><b>Cartão SUS:</b> ${assistido.cartaoSus}</li>
                <li class="list-group-item"><b>Nº do benefício:</b> ${assistido.noDoBeneficio}</li>
                <li class="list-group-item"><b>Profissão:</b> ${assistido.profissao}</li>
                <li class="list-group-item"><b>Telefone:</b> ${assistido.telfixo}</li>
                <li class="list-group-item"><b>Celular:</b> ${assistido.celular}</li>
                <li class="list-group-item"><b>Data de Nascimento:</b> <fmt:formatDate value="${assistido.dataNascimento}" pattern="dd/MM/yyyy"/></li>
                <li class="list-group-item"><b>Naturalidade:</b> ${assistido.naturalidade}</li>
                <li class="list-group-item"><b>Nacionalidade:</b> ${assistido.nacionalidade}</li>
                <li class="list-group-item"><b>Observações:</b> ${assistido.observacoes}</li>
                <h1 align="center">Endereço</h1>
                <li class="list-group-item"><b>Rua:</b> ${assistido.rua}</li>
                <li class="list-group-item"><b>Nº:</b> ${assistido.numero}</li>
                <li class="list-group-item"><b>Bairro:</b> ${assistido.bairro}</li>
                <li class="list-group-item"><b>Cidade</b> ${assistido.cidade}</li>
                <li class="list-group-item"><b>Estado:</b> ${assistido.estado}</li>
                <h1 align="center">Dados Bancários</h1>
                <li class="list-group-item"><b>Banco:</b> ${assistido.banco}</li>
                <li class="list-group-item"><b>Agência:</b> ${assistido.agencia}</li>
                <li class="list-group-item"><b>Conta:</b> ${assistido.conta}</li>
            </ul>
            
            
        </div>
           <!--  <div class="modal-footer">
                 <a href="<c:url value="/logout"/>" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Sim</a>
                 <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Não</button>
            </div> -->

       <jsp:include page="../../modais.jsp"/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    </body>
</html>