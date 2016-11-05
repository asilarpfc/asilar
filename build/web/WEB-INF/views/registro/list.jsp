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
    <body <c:if test="${not empty errors}"> onload="$('#modal-registro${idr}').modal('show');"</c:if>>
        <jsp:include page="../navusuariolista.jsp"/>
        <jsp:include page="../barralateral.jsp"/>

        <ul class="nav nav-tabs">
            <li <c:if test="${tab eq 'info'}"> class="active"</c:if>><a href="<c:url value="/cadastro/assistido/${assistido.id}/info"/>">Informações</a></li>
            <li <c:if test="${tab eq 'registro'}"> class="active"</c:if>><a href="<c:url value="/cadastro/assistido/${assistido.id}/registro"/>">Registro</a></li>
            </ul>


            <h1 align="center">${assistido.nome}</h1>

        <c:if test="${empty registroList}">
            <button type="button" class="btn btn-primary col-lg-offset-2" data-toggle="modal" data-target="#modal-registro"><span class="glyphicon glyphicon-plus"></span> Novo registro</button>
        </c:if>
            <div class="modal fade" id="modal-registro">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">${assistido.nome}</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" method="post" action="<c:url value="/cadastro/assistido/${assistido.id}/novoregistro"/>">
                                <div class="form-group<c:if test="${not empty errors.dataEntrada}">has-error has-feedback</c:if>">
                                    <label for="dataEntrada" class="col-sm-2 control-label">Data de entrada</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="dataEntrada" class="form-control" placeholder="Data de entrada" value="<fmt:formatDate value="${registroModal.dataEntrada}" pattern="dd/MM/yyyy"/>" onfocus="$(this).mask('00/00/0000');">
                                        <c:if test="${not empty errors.dataEntrada}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                                        <c:if test="${not empty errors.dataEntrada}"><span class="alert-danger">${errors.dataEntrada}</span></c:if> 
                                    </div>
                                </div>
                                <div class="form-group<c:if test="${not empty errors.dataSaida}">has-error has-feedback</c:if>">
                                    <label for="dataSaida" class="col-sm-2 control-label">Data de saída</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="dataSaida" class="form-control" placeholder="Data de saida" value="<fmt:formatDate value="${registroModal.dataSaida}" pattern="dd/MM/yyyy"/>" onfocus="$(this).mask('00/00/0000');">
                                        <c:if test="${not empty errors.dataSaida}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                                        <c:if test="${not empty errors.dataSaida}"><span class="alert-danger">${errors.dataSaida}</span></c:if> 
                                    </div>
                                </div>
                                <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button type="submit" class="btn btn-primary">Registrar</button>
                                        </div>    
                                </div>  
                                
                            </form>
                            
                        </div>
                        
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

        
        <c:if test="${not empty registroList}">
            <div class="table-responsive col-lg-offset-2">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Data de entrada:</th>
                            <th>Registrado por:</th>
                            <th>Data de saída:</th>
                            <th>Registrado por:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${registroList}" var="registro">
                            <tr>
                                <td><fmt:formatDate value="${registro.dataEntrada}" pattern="dd/MM/yyyy"/></td>
                                <td>${registro.usuarioEntrada.nome}</td>
                                <td><fmt:formatDate value="${registro.dataSaida}" pattern="dd/MM/yyyy"/></td>
                                <td>${registro.usuarioSaida.nome}</td>
                                <td><button type="button" class="btn btn-primary col-lg-offset-2" data-toggle="modal" data-target="#modal-registro${registro.id}"><span class="glyphicon glyphicon-pencil"></span></button></td>
                            </tr>
                            <c:if test="${not empty registro.dataSaida}">
                                <c:set var="novo" value="true"/>
                            </c:if>
                            <c:if test="${empty registro.dataSaida}">
                                <c:set var="novo" value="false"/>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${novo eq true}">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-registro"><span class="glyphicon glyphicon-plus"></span> Novo registro</button>
                </c:if>
            </c:if>

            <c:forEach items="${registroList}" var="registro">
                <div class="modal fade" id="modal-registro${registro.id}">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" onclick="window.location.href='<c:url value="/cadastro/assistido/${assistido.id}/registro"/>'" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">${assistido.nome}</h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" method="post" action="<c:url value="/cadastro/assistido/${assistido.id}/${registro.id}/editarregistro"/>">
                                <div class="form-group<c:if test="${not empty errors.dataEntrada}">has-error has-feedback</c:if>">
                                    <input type="hidden" name="usuarioEntrada" value="${registro.usuarioEntrada.id}">
                                    <label for="dataEntrada" class="col-sm-2 control-label">Data de entrada</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="dataEntrada" class="form-control" placeholder="Data de entrada" value="<c:if test="${empty registroModal}"><fmt:formatDate value="${registro.dataEntrada}" pattern="dd/MM/yyyy"/></c:if> <c:if test="${not empty registroModal}"><fmt:formatDate value="${registroModal.dataEntrada}" pattern="dd/MM/yyyy"/></c:if>" onfocus="$(this).mask('00/00/0000');">
                                        <c:if test="${not empty errors.dataEntrada}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                                        <c:if test="${not empty errors.dataEntrada}"><span class="alert-danger">${errors.dataEntrada}</span></c:if> 
                                    </div>
                                </div>
                                <div class="form-group<c:if test="${not empty errors.dataSaida}">has-error has-feedback</c:if>">
                                    <input type="hidden" name="usuarioSaida" value="${registro.usuarioSaida.id}">
                                    <label for="dataSaida" class="col-sm-2 control-label">Data de saída</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="dataSaida" class="form-control" placeholder="Data de saida" value="<c:if test="${empty registroModal}"><fmt:formatDate value="${registro.dataSaida}" pattern="dd/MM/yyyy"/></c:if> <c:if test="${not empty registroModal}"><fmt:formatDate value="${registroModal.dataSaida}" pattern="dd/MM/yyyy"/></c:if>" onfocus="$(this).mask('00/00/0000');">
                                        <c:if test="${not empty errors.dataSaida}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                                        <c:if test="${not empty errors.dataSaida}"><span class="alert-danger">${errors.dataSaida}</span></c:if> 
                                    </div>
                                </div>
                                <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button type="submit" class="btn btn-primary">Registrar</button>
                                        </div>    
                                </div>  
                                
                            </form>
                            </div>
                            <div class="modal-footer">
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->

            </c:forEach>

            <c:if test="${count > 10}">
                <nav>
                    <ul class="pagination">
                        <li <c:if test="${offset <= 0}"> class="disabled" </c:if>>
                            <a <c:if test="${offset > 0}">href="<c:url value="/cadastro/assistido/lista?offset=${offset - 10}"/>"</c:if>aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        <c:forEach var="conta" begin="0" step="10" end="${count -1}">
                            <li <c:if test="${offset == conta}">class="active"</c:if>><a href="<c:url value="/cadastro/assistido/lista?offset=${conta}"/>"><fmt:formatNumber value="${(conta + 10) / 10}" pattern="#"/></a></li>
                            </c:forEach>
                        <li <c:if test="${offset > count - 10}"> class="disabled" </c:if>>
                            <a <c:if test="${offset < count - 10}"> href="<c:url value="/cadastro/assistido/lista?offset=${offset + 10}"/>" </c:if> aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
            </c:if>

        </div>

        <jsp:include page="../modais.jsp"/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/js/jquery.mask.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>