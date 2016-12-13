<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#datNasc').mask("00/00/0000");
        $('#cpf').mask('000.000.000-00', {reverse: true});
        $('#cep').mask('00000-000', {reverse: true});
        $('#telefone').mask('(00) 0000-00009');
        $('#celular').mask('(00) 00000-0009');
        
        	
        $.getJSON('<c:url value="/js/estados_cidades.json"/>', function (data){
        
        $.each(data, function (key, val) {
            
            <c:if test="${not empty assistido.estado}">uf = '${assistido.estado}'</c:if>
            if (val.sigla === uf) {
                $("#uf").append('<option value="' + val.sigla + '" selected>' + val.nome + '</option>');
                //$("#ufValue").text(val.nome);
                
            } else {
                $("#uf").append('<option value="' + val.sigla + '">' + val.nome + '</option>');
            }

        });
        <c:if test="${not empty assistido.estado}"> uf = '${assistido.estado}';</c:if>
        var uf = $("#uf option:selected").val();
        $("#cidade2").empty();
        
        $.each(data, function (i, val){
            if(val.sigla === uf){
                //$("#cidade2").append('<option value="0">Nenhuma específica</option>');
                $.each(val.cidades ,function (k, cidade){
                    $("#cidade2").append('<option value="'+cidade+'">'+cidade+'</option>');
                });
            }
        });
        
        $("#uf").change(function (){
            var uf = $("#uf option:selected").val();
            $("#cidade2").empty();
            
            $.each(data, function (j, val){
                if(val.sigla === uf){
                    //$("#cidade2").append('<option value="0">Nenhuma específica</option>');
                    $.each(val.cidades ,function (k, cidade){
                        $("#cidade2").append('<option value="'+cidade+'">'+cidade+'</option>');
                    });
                }
            });
        });
        <c:if test="${not empty assistido.cidade}"> $("#cidade2").append('<option value="${assistido.cidade}" selected>${assistido.cidade}</option>');</c:if>
    });

    });

    function fctValidaData(obj, flag)
    {
        var data = obj;
        var dia = data.substring(0, 2)
        var mes = data.substring(3, 5)
        var ano = data.substring(6, 10)

        //Criando um objeto Date usando os valores ano, mes e dia.
        var novaData = new Date(ano, (mes - 1), dia);
        var maiorData = new Date();

        var mesmoDia = parseInt(dia, 10) == parseInt(novaData.getDate());
        var mesmoMes = parseInt(mes, 10) == parseInt(novaData.getMonth()) + 1;
        var mesmoAno = parseInt(ano) == parseInt(novaData.getFullYear());

        if (!((mesmoDia) && (mesmoMes) && (mesmoAno)))
        {
            $("#divDataNasc").append("<span class='glyphicon glyphicon-remove form-control-feedback'></span>");
            $("#divDataNasc").append("<span class='alert-danger'>Data invalida</span>");
            $("#formDatNasc").attr("class", "form-group has-error has-feedback");
            $("#datNasc").focus();
            return false;
        } else if (novaData > maiorData && flag == 1) {
            $("#divDataNasc").append("<span class='glyphicon glyphicon-remove form-control-feedback'></span>");
            $("#divDataNasc").append("<span class='alert-danger'>Data de nascimento deve ser menor quea data atual</span>");
            $("#formDatNasc").attr("class", "form-group has-error has-feedback");
            $("#datNasc").focus();
            return false;
        }

        return true;
    }
</script>
