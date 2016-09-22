<%@page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#datNasc').mask("00/00/0000");
        $('#cpf').mask('000.000.000-00', {reverse: true});
        $('#cep').mask('00000-000', {reverse: true});
        $('#telefone').mask('(00) 0000-00009');
        $('#celular').mask('(00) 00000-0009');
    });

    function fctValidaData(obj)
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
            $("#divDataNasc").append("<span class='alert-danger'>Data de nascimento invalida</span>");
            $("#formDatNasc").attr("class", "form-group has-error has-feedback");
            $("#datNasc").focus();
            return false;
        } else if (novaData > maiorData) {
            $("#divDataNasc").append("<span class='glyphicon glyphicon-remove form-control-feedback'></span>");
            $("#divDataNasc").append("<span class='alert-danger'>Data de nascimento deve ser menor quea data atual</span>");
            $("#formDatNasc").attr("class", "form-group has-error has-feedback");
            $("#datNasc").focus();
            return false;
        }

        return true;
    }
</script>
