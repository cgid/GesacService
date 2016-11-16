
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
                            <label class="radio-inline" style="margin-left: 10px; margin-top: 2px;">
                                <input type="radio" name="radio" class="magic-radio" id="radio_serv" value="servico"/>
                                <strong>Serviço</strong>
                            </label>
                            <!--<input type="radio" name="radio" id="rad_estado" value="2"/>estado-->
                            <label class="radio-inline" style="margin-top: 2px;">
                                <input style="" type="radio" name="radio" class="magic-radio" id="radio_pid" value="pid"/>
                                <strong>PID</strong>
                            </label>
                            <label class="radio-inline" style="margin-top: 2px;">
                                <input style="" type="radio" name="radio" class="magic-radio" id="radio_usu" value="usuario"/>
                                <strong>Usuário</strong>
                            </label>
                            <label class="radio-inline" style="margin-top: 2px;">
                                <input style="" type="radio" name="radio" class="magic-radio" id="radio_chdo" value="chamado"/>
                                <strong>Chamado</strong>
                            </label>
                            <input style="margin-left: 10px; margin-right: 5px; height: 25px;" type="text" name="where"  id="where" hidden="true" class="input-sm"/>
                        </div>

                        <div style="padding-top:5px; text-align: center; padding-left: 10px; margin-top:20px; margin-left:25px; background-color:#D6DADF; width: 860px; height: 30px;" class="input-group" hidden="true" id="div_usuario" >

                            <label class="checkbox-inline"><input id="end_novo_usu" name="end_novo_usu" type="checkbox" onclick="bloqueio(this.id)">Endereços adicionados&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                            <div id="div_end_novo_usu" style="display: none">

                                <strong>( </strong><label class="checkbox-inline"><input id="Descricao_usu"name="Descricao_usu" checked="true" type="checkbox"><strong>Descricão</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                                <label class="checkbox-inline"><input checked="true" id="Numero_usu"name="Numero_usu" type="checkbox"><strong>Número</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>  
                                <label class="checkbox-inline"><input id="Bairro_usu"name="Bairro_usu" type="checkbox" checked="true"><strong>Bairro</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>  
                                <label class="checkbox-inline"><input id="Cep_usu"name="Cep_usu" checked="true" type="checkbox"><strong>CEP</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>  
                                <label class="checkbox-inline"><input id="Municipio_usu"name="Municipio_usu" type="checkbox" checked="true"><strong>Município )</strong></label>  


                            </div>
                            <label class="checkbox-inline"><input id="telefone_usu"name="telefone_usu" onclick="bloqueio(this.id)" type="checkbox" value="telefoneusu">Telefones&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <div id="div_telefone_usu" style="display: none">

                                <strong>( </strong><label class="checkbox-inline"><input id="Invalidos_usu" type="checkbox"><strong>Inválidos</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label> 

                                <label class="checkbox-inline"><input id="Novos_usu" type="checkbox"><strong>Novos )</strong></label>

                            </div>



                            <label class="checkbox-inline"><input id="chamados_usu" onclick="bloqueio(this.id)" type ="checkbox">Chamados&nbsp;&nbsp;&nbsp;&nbsp;</label>

                            <div id="div_chamados_usu" style="display: none">
                                <label class="checkbox-inline"><input id="end_novo_usu" name="end_novo_usu" type="checkbox" onclick="bloqueio(this.id)">Endereços adicionados&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                                <label class="checkbox-inline"><input id="telefone_usu"name="telefone_usu" onclick="bloqueio(this.id)" type="checkbox" value="telefoneusu">Telefones&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <label class="checkbox-inline"><input id="Respostas_usu" name="Respostas_usu" type="checkbox">Respostas&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <strong>( </strong><label class="checkbox-inline"><input id="duracao_usu" type="checkbox"><strong>Duração&nbsp;&nbsp;&nbsp;&nbsp;</strong></label> 
                                <label class="checkbox-inline"><input id="qtd_atendidos_usu" type="checkbox"><strong>Quantidades de atendidos )</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label> 

                            </div> 
                        </div> <div style="padding-top:5px; text-align: center; margin-top:20px; margin-left:25px; background-color:#D6DADF; width: 860px; height: 30px;" class="input-group" hidden="true" id="div_pid">

                            <label class="checkbox-inline"><input id="end_pid" name="end_pid" type="checkbox" onclick="bloqueio(this.id)">Endereço Atual&nbsp;&nbsp;&nbsp;&nbsp;</label> 

                            <label class="checkbox-inline"><input id="end_novo_pid"name="end_novo_pid" type="checkbox" onclick="bloqueio(this.id)">Endereço Novo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                            <div id="div_end_novo_pid" style="display: none">


                                <strong>(</strong> <label class="checkbox-inline"><input id="Descricao_pid"name="Descricao_pid" checked="true" type="checkbox"><strong>Descricão</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>  
                                <label class="checkbox-inline"><input checked="true" id="Numero_pid" name="Numero_pid" type="checkbox"><strong>Número</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                                <label class="checkbox-inline"><input id="Bairro_pid"name="Bairro_pid" type="checkbox" checked="true"><strong>Bairro</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>  
                                <label class="checkbox-inline"><input id="Cep_pid"name="Cep_pid" checked="true" type="checkbox"><strong>CEP</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>  
                                <label class="checkbox-inline"><input id="Municipio_pid"name="Municipio_pid" type="checkbox" checked="true"><strong>Município )</strong></label>  
                            </div>

                            <label class="checkbox-inline"><input id="telefone_pid" onclick="bloqueio(this.id)"type="checkbox" value="tel">Telefone&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <div id="div_telefone_pid" style="display: none">

                                <strong>(</strong> <label class="checkbox-inline"><input id="Invalidos_pid" type="checkbox"><strong>Inválidos</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                <label class="checkbox-inline"><input id="Novos_pid" type="checkbox"><strong>Novos</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                <label class="checkbox-inline"><input id="total_pid" type="checkbox"><strong>Total )</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            </div>

                            <label class="checkbox-inline"><input id="chamado_pid" type="checkbox" onclick="bloqueio(this.id)">Chamados</label>
                            <div id="div_chamado_pid" style="display: none">


                                <strong>(</strong> <label class="checkbox-inline"><input id="Respostas_pid" type="checkbox"><strong>Respostas</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                <label class="checkbox-inline"><input id="Observações_pid" type="checkbox"><strong>Observações</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                <label class="checkbox-inline"><input id="duracao_pid" type="checkbox"><strong>Duração</strong>&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                <label class="checkbox-inline"><input id="qtd_atendidos_pid" type="checkbox"><strong>Quantidades de tentativas )</strong></label>
                            </div> 
                        </div>
                        <div id="div_chamado"  hidden="true" style="text-align: center; padding-top: 5px; padding-left: 10px; margin-top:20px; margin-left:25px; background-color:#D6DADF; width: 860px; height: 30px;">

                            <label class="checkbox-inline"><input id="Data_chdo" checked="true" type="checkbox">Data de abertura&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                            <label class="checkbox-inline"><input id="Respostas_chdo" checked="true" type="checkbox">Respostas&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                            <label class="checkbox-inline"><input id="Observação_chdo" checked="true" type="checkbox">Observação&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                            <label class="checkbox-inline"><input id="duracao_chdo" checked="true" type="checkbox">Duração&nbsp;&nbsp;&nbsp;&nbsp;</label> 




                        </div>
                        <div class="row text-right">

                            <br><br><button type="submit" id="submit"class="btn btn-primary text-center">Gerar Relatórios</button>

                        </div> </form> 
                    <!-- Table -->
                    <c:set var="aL" value='${requestScope["atributosList"]}'/>
                    <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                    <table class="table">
                        <c:set var="jL" value='${requestScope["JoinList"]}'/>

                        <c:set var="where" value='${requestScope["where"]}'/>
                        <c:set var="pesquisa" value='${requestScope["pesquisa"]}'/>
                        <c:set var="c" value='${requestScope["contadores"]}'/>
                        <c:set var="i" value='${0}'/>
                        <thead> 
                            <tr> 
                                <c:forEach items="${sq.getTitulosList(aL,jL,c, '2')}" var="titulo">




                                    <th><c:out value="${titulo.toString()}"></c:out></th>




                                </c:forEach>
                            </tr> 
                        </thead><tbody>

                            <c:forEach items="${sq.Relatorios(aL,jL,c, where,pesquisa)}" var="consulta">
                                <tr>    

                                    ${consulta.toString()}



                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>






            <%@include file="footer.html" %>
        </section>





    </body>

</html>

<%}%>