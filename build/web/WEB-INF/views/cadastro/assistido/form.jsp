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
        <h1 align="center">Cadastrar Assistido</h1>

        <form class="form-horizontal col-lg-offset-2" method="post" onsubmit="return fctValidaData(datNasc.value);">
            <input type="hidden" name="id" value="${assistido.id}">

            <div class="form-group <c:if test="${not empty errors.nome}">has-error has-feedback</c:if>">
                <label for="nome" class="col-sm-2 control-label">Nome:</label>
                <div class="col-sm-6">
                    <input type="text" name="nome" class="form-control" placeholder="Nome" value="${assistido.nome}" maxlength="150" >
                    <c:if test="${not empty errors.nome}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.nome}"><span class="alert-danger">${errors.nome}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.rg}">has-error has-feedback</c:if>">
                    <label for="rg" class="col-sm-2 control-label">RG:</label>
                    <div class="col-sm-6">
                        <input type="text" name="rg" class="form-control" placeholder="RG" value="${assistido.rg}" maxlength="20">
                        <c:if test="${not empty errors.rg}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.rg}"><span class="alert-danger">${errors.rg}</span></c:if>
                    
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.cpf}">has-error has-feedback</c:if>">
                    <label for="cpf" class="col-sm-2 control-label">CPF:</label>
                    <div class="col-sm-6">
                        <input id="cpf" type="text" name="cpf" class="form-control" placeholder="CPF" value="${assistido.cpf}" maxlength="20" >
                        <c:if test="${not empty errors.cpf}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.cpf}"><span class="alert-danger">${errors.cpf}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.cartaoSus}">has-error has-feedback</c:if>">
                    <label for="cartaoSus" class="col-sm-2 control-label">Cartão sus:</label>
                    <div class="col-sm-6">
                        <input type="text" name="cartaoSus" class="form-control" placeholder="Nº do cartão do sus" value="${assistido.cartaoSus}" maxlength="20" >
                        <c:if test="${not empty errors.cartaoSus}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.cartaoSus}"><span class="alert-danger">${errors.cartaoSus}</span></c:if>    
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.noDoBeneficio}">has-error has-feedback</c:if>">
                    <label for="noDoBeneficio" class="col-sm-2 control-label">Nº do Beneficio:</label>
                    <div class="col-sm-6">
                        <input type="text" name="noDoBeneficio" class="form-control" placeholder="Nº do Beneficio" value="${assistido.noDoBeneficio}" maxlength="20">
                        <c:if test="${not empty errors.noDoBeneficio}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.noDoBeneficio}"><span class="alert-danger">${errors.noDoBeneficio}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.telfixo}">has-error has-feedback</c:if>">
                    <label for="telfixo" class="col-sm-2 control-label">Telefone Fixo:</label>
                    <div class="col-sm-6">
                        <input id="telefone" type="text" name="telfixo" class="form-control" placeholder="Telefone Fixo" value="${assistido.telfixo}" maxlength="15">
                        <c:if test="${not empty errors.telfixo}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.telfixo}"><span class="alert-danger">${errors.telfixo}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.celular}">has-error has-feedback</c:if>">
                    <label for="celular" class="col-sm-2 control-label">Celular:</label>
                    <div class="col-sm-6">
                        <input id="celular" type="text" name="celular" class="form-control" placeholder="Celular" value="${assistido.celular}" maxlength="15">
                        <c:if test="${not empty errors.celular}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.celular}"><span class="alert-danger">${errors.celular}</span></c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label for="estadoCivil" class="col-sm-2 control-label">Estado Civil:</label>
                    <div class="col-sm-6">
                        <select class="form-control" name="estadoCivil" required="">
                            <c:if test="${empty assistido.sexo}"><option value="">Selecione...</option></c:if>
                            <option value="Casado" <c:if test="${assistido.estadoCivil eq 'Casado'}"> selected </c:if>>Casado</option>
                            <option value="Solteiro" <c:if test="${assistido.estadoCivil eq 'Solteiro'}"> selected </c:if>>Solteiro</option>
                            <option value="Separado" <c:if test="${assistido.estadoCivil eq 'Separado'}"> selected </c:if>>Separado</option>
                            <option value="Divorciado" <c:if test="${assistido.estadoCivil eq 'Divorciado'}"> selected </c:if>>Divorciado</option>
                            <option value="Viúvo" <c:if test="${assistido.estadoCivil eq 'Viúvo'}"> selected </c:if>>Viúvo</option>
                        </select>
                    </div>
                </div>

                            <div id="formDatNasc" class="form-group <c:if test="${not empty errors.dataNascimento}"> has-error has-feedback</c:if>">
                    <label for="dataNascimento" class="col-sm-2 control-label">Data de Nascimento:</label>
                    <div class="col-sm-6" id="divDataNasc">
                        <input id="datNasc" type="text" name="dataNascimento" class="form-control" placeholder="Data de Nascimento" value="<fmt:formatDate value="${assistido.dataNascimento}" pattern="dd/MM/yyyy"/>" maxlength="15">
                        <c:if test="${not empty errors.dataNascimento}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.dataNascimento}"><span class="alert-danger">${errors.dataNascimento}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.profissao}">has-error has-feedback</c:if>">
                    <label for="profissao" class="col-sm-2 control-label">Profissão:</label>
                    <div class="col-sm-6">
                        <input type="text" name="profissao" class="form-control" placeholder="Profissão" value="${assistido.profissao}" maxlength="50">
                    <c:if test="${not empty errors.profissao}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.profissao}"><span class="alert-danger">${errors.profissao}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.naturalidade}">has-error has-feedback</c:if>">
                    <label for="naturalidade" class="col-sm-2 control-label">Naturalidade:</label>
                    <div class="col-sm-6">
                        <input type="text" name="naturalidade" class="form-control" placeholder="Naturalidade" value="${assistido.naturalidade}" maxlength="50">
                    <c:if test="${not empty errors.naturalidade}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                    <c:if test="${not empty errors.naturalidade}"><span class="alert-danger">${errors.naturalidade}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.nacionalidade}">has-error has-feedback</c:if>">
                    <label for="nacionalidade" class="col-sm-2 control-label">Nacionalidade:</label>
                    <div class="col-sm-6">
                        <input type="text" name="nacionalidade" class="form-control" placeholder="Nacionalidade" value="${assistido.nacionalidade}" maxlength="20">
                        <c:if test="${not empty errors.nacionalidade}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.nacionalidade}"><span class="alert-danger">${errors.nacionalidade}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="sexo" class="col-sm-2 control-label">Sexo:</label>
                <div class="col-sm-6">
                    <select class="form-control" name="sexo" required="">
                        <c:if test="${empty assistido.sexo}"><option value="">Selecione...</option></c:if>
                        <option value="Masculino" <c:if test="${assistido.sexo eq 'Masculino'}"> selected </c:if>>Masculino</option>
                        <option value="Feminino" <c:if test="${assistido.sexo eq 'Feminino'}"> selected </c:if>>Feminino</option>
                        </select>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.pai}">has-error has-feedback</c:if>">
                    <label for="pai" class="col-sm-2 control-label">Pai:</label>
                    <div class="col-sm-6">
                        <input type="text" name="pai" class="form-control" placeholder="Pai" value="${assistido.pai}" maxlength="150">
                        <c:if test="${not empty errors.pai}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.pai}"><span class="alert-danger">${errors.pai}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.mae}">has-error has-feedback</c:if>">
                    <label for="mae" class="col-sm-2 control-label">Mãe:</label>
                    <div class="col-sm-6">
                        <input type="text" name="mae" class="form-control" placeholder="Mãe" value="${assistido.mae}" maxlength="150">
                        <c:if test="${not empty errors.mae}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.mae}"><span class="alert-danger">${errors.mae}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.observacoes}">has-error has-feedback</c:if>">
                    <label for="observacoes" class="col-sm-2 control-label">Observações:</label>
                    <div class="col-sm-6">
                        <textarea name="observacoes" class="form-control" rows="3" placeholder="observacoes" required="">${assistido.observacoes}</textarea>
                        <c:if test="${not empty errors.observacoes}"><span>${errors.observacoes}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.procedencia}">has-error has-feedback</c:if>">
                    <label for="procedencia" class="col-sm-2 control-label">Procedencia:</label>
                    <div class="col-sm-6">
                        <textarea name="procedencia" class="form-control" rows="3" placeholder="procedencia" required="">${assistido.procedencia}</textarea>
                        <c:if test="${not empty errors.procedencia}"><span>${errors.procedencia}</span></c:if>
                    </div>
                </div>

                <h1 align="center">Endereço:</h1>
                <div class="form-group <c:if test="${not empty errors.rua}">has-error has-feedback</c:if>">
                    <label for="rua" class="col-sm-2 control-label">Rua:</label>
                    <div class="col-sm-6">
                        <input type="text" name="rua" class="form-control" placeholder="Rua" value="${assistido.rua}" maxlength="150">
                        <c:if test="${not empty errors.rua}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.rua}"><span class="alert-danger">${errors.rua}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.numero}">has-error has-feedback</c:if>">
                    <label for="numero" class="col-sm-2 control-label">Numero:</label>
                    <div class="col-sm-6">
                        <input type="text" name="numero" class="form-control" placeholder="Numero" value="${assistido.numero}" maxlength="10">
                        <c:if test="${not empty errors.numero}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.numero}"><span class="alert-danger">${errors.numero}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.bairro}">has-error has-feedback</c:if>">
                    <label for="bairro" class="col-sm-2 control-label">Bairro:</label>
                    <div class="col-sm-6">
                        <input type="text" name="bairro" class="form-control" placeholder="Bairro" value="${assistido.bairro}" maxlength="150">
                        <c:if test="${not empty errors.bairro}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.bairro}"><span class="alert-danger">${errors.bairro}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.estado}">has-error has-feedback</c:if>">
                    <label for="estado" class="col-sm-2 control-label">Eestado:</label>
                    <div class="col-sm-6">
                        <select class="form-control" name="estado" id="uf" required="">
                    
                        </select>
                    </div>
                </div>
                
               <div class="form-group <c:if test="${not empty errors.cidade}">has-error has-feedback</c:if>">
                    <label for="cidade" class="col-sm-2 control-label">Cidade:</label>
                    <div class="col-sm-6">
                    <select class="form-control" name="cidade" id="cidade2" required="">
                        </select>
                    </div>
                </div>

                <h1 align="center">Dados Bancarios:</h1>
                <div class="form-group <c:if test="${not empty errors.banco}">has-error has-feedback</c:if>">
                    <label for="banco" class="col-sm-2 control-label">Banco:</label>
                    <div class="col-sm-6">
                        <input type="text" name="banco" class="form-control" placeholder="Nº do banco" value="${assistido.banco}" maxlength="20">
                      <c:if test="${not empty errors.banco}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                      <c:if test="${not empty errors.banco}"><span class="alert-danger">${errors.banco}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.agencia}">has-error has-feedback</c:if>">
                    <label for="agencia" class="col-sm-2 control-label">Agencia:</label>
                    <div class="col-sm-6">
                        <input type="text" name="agencia" class="form-control" placeholder="Nº da agencia" value="${assistido.agencia}" maxlength="20">
                        <c:if test="${not empty errors.agencia}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.agencia}"><span class="alert-danger">${errors.agencia}</span></c:if>
                    </div>
                </div>

                <div class="form-group <c:if test="${not empty errors.conta}">has-error has-feedback</c:if>">
                    <label for="conta" class="col-sm-2 control-label">Conta Bancaria:</label>
                    <div class="col-sm-6">
                        <input type="text" name="conta" class="form-control" placeholder="Nº da conta" value="${assistido.conta}" maxlength="30">
                        <c:if test="${not empty errors.conta}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
                        <c:if test="${not empty errors.conta}"><span class="alert-danger">${errors.conta}</span></c:if>
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