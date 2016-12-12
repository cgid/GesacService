
<%@page import="javax.xml.transform.OutputKeys"%>
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.Entity"%>
<%@page import="br.com.minicom.scr.entity.Servico"%>

<%@page import="br.com.minicom.scr.entity.Usuario"%>


<%@include file="valida.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html >

    <head>
        <meta charset="utf-8">
        <title>SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/gerenciador_servico.css">

    </head>



    <body>
        <%@include file="header.html" %>


        <section>

            <% if (perfil.contains("gerente")) {
            %><%@include file= 'barra_gerente.jsp' %> 

            <% }%>  <% if (perfil.contains("administrador")) {

            %><%@include file= 'barra_administrador.jsp' %> 
            <% }%>  <% if (perfil.contains("atendente")) {

            %><%@include file= 'barra_atendente.jsp' %> 
            <% }%>




            <div id="painel" class="container" >
                <script src="http://code.jquery.com/jquery-latest.js">
                </script>
                <script>  $(document).ready(function () {
                        $("#where").hide();
                        $("#div_pid").hide();
                        $("#div_usuario").hide();

                        var pid = $("#pesquisa").val();
                        var radio = $("#radio").val();
//                     
                        $(document.getElementById('radio_pid')).click(function (event) {
                            $("#where").attr({required: true, placeholder: "Código Pid"});
                            $("#where").show();
                            $("#div_usuario").hide();
                            $("#div_pid").show();
                            $("#div_chamado").hide();


                        });
                        $(document.getElementById('radio_chdo')).click(function (event) {
                            $("#where").attr({required: true, placeholder: "Id do chamado"});
                            $("#where").show();
                            $("#div_chamado").show();
                            $("#div_usuario").hide();


                        });
                        $(document.getElementById('radio_usu')).click(function (event) {

                            $("#where").attr({required: true, placeholder: "nome do usuario"});
                            $("#div_pid").hide();
                            $("#div_usuario").show();
                            $("#where").show();
                            $("#div_chamado").hide();

                        });


                        $('#submit').click(function (event) {


                            var pid = $('#pid').val();
                            var ibge = $('#ibge1').val();
                            var descricao = $('#descricao').val();
                            var numero = $('#numero').val();
                            var bairro = $('#bairro').val();
                            var cep = $('#cep').val();
                            var complemento = $('#complemento').val();
                            $.get('ActionServlet', {pid: pid, descricao: descricao, cep: cep, ibge: ibge, complemento: complemento, numero: numero, bairro: bairro}, function (responseText) {

                                $('#sucess').show().text(responseText);
                                $('#divtabform').show();
                                $('#editarendereco').hide("now");

                            });
                        });
                    });
                </script>
                <script  type="text/javascript">function bloqueio(n) {
                        n = "div_" + n;


                        if (document.getElementById(n).style.display == "none") {
                            document.getElementById(n).style.display = "block";
                        } else {
                            document.getElementById(n).style.display = "none";
                        }
                    }</script>
                <div class="panel panel-default">

                    <!-- Default panel contents -->
                    <div class="panel-heading" style="text-align: center;"><strong>RELATÓRIOS</strong></div>
                    <form method="POST" action="GerenciadorRelatorios">



                        <div style="text-align: center; padding-top: 5px; margin-top:20px; margin-left:25px; background-color:#eaeaea; width: 860px; height: 36px;">

                            <!--<input type="radio" name="radio" id="rad_estado" value="2"/>estado-->
                            <label class="radio-inline" style="margin-top: 2px;">
                                <input style="" type="radio" name="radio" class="magic-radio" id="radio_pid" value="pid"/>
                                <strong>PID</strong>
                            </label>
                            <label class="radio-inline" style="margin-top: 2px;">
                                <input style="" type="radio" name="radio" class="magic-radio" id="radio_usu" value="usuario"/>
                                <strong>Usuário</strong>
                            </label>

                            <input style="margin-left: 10px; margin-right: 5px; height: 25px;" type="text" name="where"  id="where" hidden="true" class="input-sm"/>
                        </div>



                        <div class="row text-right">

                            <br><br><button type="submit" id="submit"class="btn btn-primary text-center">Gerar Relatórios</button>

                        </div> </form> 
                </div>

            </div>






            <%@include file="footer.html" %>
        </section>





    </body>

</html>

<%}%>