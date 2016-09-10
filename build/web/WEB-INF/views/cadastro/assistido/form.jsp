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
        <h1 align="center">Cadastrar Assistido</h1>

        <form class="form-horizontal" method="post">
            <input type="hidden" name="id" value="${assistido.id}">

            <div class="form-group">
                <label for="nome" class="col-sm-2 control-label">Nome:</label>
                <div class="col-sm-6">
                    <input type="text" name="nome" class="form-control" placeholder="Nome" value="${assistido.nome}" maxlength="150" required="">
                    <c:if test="${not empty errors.nome}"><span>${errors.nome}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="rg" class="col-sm-2 control-label">RG:</label>
                <div class="col-sm-6">
                    <input type="text" name="rg" class="form-control" placeholder="RG" value="${assistido.rg}" required="">
                    <c:if test="${not empty errors.rg}"><span>${errors.rg}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="cpf" class="col-sm-2 control-label">CPF:</label>
                <div class="col-sm-6">
                    <input type="text" name="cpf" class="form-control" placeholder="CPF" value="${assistido.cpf}" >
                    <c:if test="${not empty errors.cpf}"><span>${errors.cpf}</span></c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label for="cartaoSus" class="col-sm-2 control-label">Cartão sus:</label>
                    <div class="col-sm-6">
                        <input type="text" name="cartaoSus" class="form-control" placeholder="Nº do cartão do sus" value="${assistido.cartaoSus}">
                        <c:if test="${not empty errors.cartaoSus}"><span>${errors.cartaoSus}</span></c:if>    
                </div>
            </div>

            <div class="form-group">
                <label for="noDoBeneficio" class="col-sm-2 control-label">Nº do Beneficio:</label>
                <div class="col-sm-6">
                    <input type="text" name="noDoBeneficio" class="form-control" placeholder="Nº do Beneficio" value="${assistido.noDoBeneficio}" required="">
                    <c:if test="${not empty errors.noDoBeneficio}"><span>${errors.noDoBeneficio}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="telfixo" class="col-sm-2 control-label">Telefone Fixo:</label>
                <div class="col-sm-6">
                    <input type="text" name="telfixo" class="form-control" placeholder="Telefone Fixo" value="${assistido.telfixo}" required="">
                    <c:if test="${not empty errors.telfixo}"><span>${errors.telfixo}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="celular" class="col-sm-2 control-label">Celular:</label>
                <div class="col-sm-6">
                    <input type="text" name="celular" class="form-control" placeholder="Celular" value="${assistido.celular}" required="">
                    <c:if test="${not empty errors.celular}"><span>${errors.celular}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="estadoCivil" class="col-sm-2 control-label">Estado Civil:</label>
                <div class="col-sm-6">
                    <select class="form-control" name="estadoCivil" required="">
                        <c:if test="${empty assistido.sexo}"><option value="">Selecione...</option></c:if>
                        <option value="Casado" <c:if test="${assistido.estadoCivil eq 'Casado'}"> selected </c:if>>Casado</option>
                        <option value="Solteiro" <c:if test="${assistido.estadoCivil eq 'Solteiro'}"> selected </c:if>>Solteiro</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dataNascimento" class="col-sm-2 control-label">Data de Nascimento:</label>
                    <div class="col-sm-6">
                        <input type="text" name="dataNascimento" class="form-control" placeholder="Data de Nascimento" value="${assistido.dataNascimento}" required="">
                        <c:if test="${not empty errors.dataNascimento}"><span>${errors.dataNascimento}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="profissao" class="col-sm-2 control-label">Profissão:</label>
                <div class="col-sm-6">
                    <input type="text" name="profissao" class="form-control" placeholder="Profissão" value="${assistido.profissao}" required="">
                    <c:if test="${not empty errors.profissao}"><span>${errors.profissao}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="naturalidade" class="col-sm-2 control-label">Naturalidade:</label>
                <div class="col-sm-6">
                    <input type="text" name="naturalidade" class="form-control" placeholder="Naturalidade" value="${assistido.naturalidade}" required="">
                    <c:if test="${not empty errors.naturalidade}"><span>${errors.naturalidade}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="nacionalidade" class="col-sm-2 control-label">Nacionalidade:</label>
                <div class="col-sm-6">
                    <input type="text" name="nacionalidade" class="form-control" placeholder="Nacionalidade" value="${assistido.nacionalidade}" required="">
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

                <div class="form-group">
                    <label for="pai" class="col-sm-2 control-label">Pai:</label>
                    <div class="col-sm-6">
                        <input type="text" name="pai" class="form-control" placeholder="Pai" value="${assistido.pai}" required="">
                        <c:if test="${not empty errors.pai}"><span>${errors.pai}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="mae" class="col-sm-2 control-label">Mãe:</label>
                <div class="col-sm-6">
                    <input type="text" name="mae" class="form-control" placeholder="Mãe" value="${assistido.mae}" required="">
                    <c:if test="${not empty errors.mae}"><span>${errors.mae}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="observacoes" class="col-sm-2 control-label">Observações:</label>
                <div class="col-sm-6">
                    <textarea name="observacoes" class="form-control" rows="3" placeholder="observacoes" required="">${assistido.observacoes}</textarea>
                    <c:if test="${not empty errors.observacoes}"><span>${errors.observacoes}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="procedencia" class="col-sm-2 control-label">Procedencia:</label>
                <div class="col-sm-6">
                    <textarea name="procedencia" class="form-control" rows="3" placeholder="procedencia" required="">${assistido.procedencia}</textarea>
                    <c:if test="${not empty errors.procedencia}"><span>${errors.procedencia}</span></c:if>
                </div>
            </div>

            <h1 align="center">Endereço:</h1>
            <div class="form-group">
                <label for="rua" class="col-sm-2 control-label">Rua:</label>
                <div class="col-sm-6">
                    <input type="text" name="rua" class="form-control" placeholder="Rua" value="${assistido.rua}" required="">
                    <c:if test="${not empty errors.rua}"><span>${errors.rua}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="numero" class="col-sm-2 control-label">Numero:</label>
                <div class="col-sm-6">
                    <input type="text" name="numero" class="form-control" placeholder="Numero" value="${assistido.numero}" required="">
                    <c:if test="${not empty errors.numero}"><span>${errors.numero}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="bairro" class="col-sm-2 control-label">Bairro:</label>
                <div class="col-sm-6">
                    <input type="text" name="bairro" class="form-control" placeholder="Bairro" value="${assistido.bairro}" required="">
                    <c:if test="${not empty errors.bairro}"><span>${errors.bairro}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="cidade" class="col-sm-2 control-label">Cidade:</label>
                <div class="col-sm-6">
                    <input type="text" name="cidade" class="form-control" placeholder="Cidade" value="${assistido.cidade}" required="">
                    <c:if test="${not empty errors.cidade}"><span>${errors.cidade}</span></c:if>
                </div>
            </div>
            <div class="form-group">
                <label for="estade" class="col-sm-2 control-label">Eestado:</label>
                <div class="col-sm-6">
                    <input type="text" name="estado" class="form-control" placeholder="Estado" value="${assistido.estado}" required="">
                    <c:if test="${not empty errors.estado}"><span>${errors.estado}</span></c:if>
                </div>
            </div>

            <h1 align="center">Dados Bancarios:</h1>
            <div class="form-group">
                <label for="banco" class="col-sm-2 control-label">Banco:</label>
                <div class="col-sm-6">
                    <input type="text" name="banco" class="form-control" placeholder="Nº do banco" value="${assistido.banco}" required="">
                    <c:if test="${not empty errors.banco}"><span>${errors.banco}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="agencia" class="col-sm-2 control-label">Agencia:</label>
                <div class="col-sm-6">
                    <input type="text" name="agencia" class="form-control" placeholder="Nº da agencia" value="${assistido.agencia}" required="">
                    <c:if test="${not empty errors.agencia}"><span>${errors.agencia}</span></c:if>
                </div>
            </div>

            <div class="form-group">
                <label for="conta" class="col-sm-2 control-label">Conta Bancaria:</label>
                <div class="col-sm-6">
                    <input type="text" name="conta" class="form-control" placeholder="Nº da conta" value="${assistido.conta}" required="">
                    <c:if test="${not empty errors.conta}"><span>${errors.conta}</span></c:if>
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