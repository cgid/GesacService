
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.Entity"%>
<%@page import="br.com.minicom.scr.entity.Servico"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>


<%@include file="valida.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html >

    <head>
        <meta charset="utf-8">
        <title> SIS CENTRAL REL</title>
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
            <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
            <jsp:useBean id="srv" class="br.com.minicom.scr.entity.Servico"/>


            <div id="painel" class="container" >
                <script src="http://code.jquery.com/jquery-latest.js">
                </script>






                <div class="panel panel-default">

                    <!-- Default panel contents -->
                    <div class="panel-heading" style="text-align: center;"><strong>SERVIÇOS</strong></div>
                    <div id="sucess" class="alert-success">
                    </div>


                    <!-- Table -->
                    <table class="table">

                        <thead> 
                            <tr> 
                                <th>ID</th> 
                                <th>Descrição</th> 
                                <th>Data</th> 
                                <th>Status</th> 
                                <th>Ações</th> 
                                <th>Tentativas</th> 
                                <th>Concluidos</th>


                                <th>Total</th>
                            </tr> 

                        </thead>



                        <tbody> 



                            <c:forEach items="${sq.selectList(srv)}" var="servico">
                                <tr> 
                                    <th scope=row'><c:out value="${servico.getCell(0).getValue()}"/></th>

                                    <td style="width: 300px">
                                        <div id="descricao<c:out value='${servico.getCell(0).getValue()}'/>"><c:out value="${servico.getCell(2).getValue()}"/>
                                        </div>



                                    </td>
                                    <td style="width: 250px">
                                        <div data-role="main" >
                                            <label><c:out value="${servico.getCell(3).getValue()}"/>  </label>
                                        </div>
                                    <td>
                                        <div id="divstatus<c:out value="${servico.getCell(0).getValue()}"/>" ></div> 
                                    </td>


                                    <td style="width: 700px">

                                        <form  method="POST" id="form1" action="GerenciadorServico">

                                            <input type="hidden" name="editarservico" id="editarservico" value="editar" >
                                            <input type="hidden" name="idservico" id="idservico<c:out value='${servico.getCell(0).getValue()}'/>" value='<c:out value="${servico.getCell(0).getValue()}"/>' >
                                            <input type="hidden" name="status" id="status<c:out value='${servico.getCell(0).getValue()}'/>" value='<c:out value="${servico.getCell(4).getValue()}"/>' >

                                            <button id="editar<c:out value='${servico.getCell(0).getValue()}'/>"type="submit" class="ui-content btn btn-default btn-group- small">editar</button>
                                            <button id="submit<c:out value='${servico.getCell(0).getValue()}'/>" type="button" class="ui-content btn btn-default btn-group- small">Encerrar</button>
                                        </form>



                                    </td>

                            <script>
                                $(document).ready(function () {
                                    var status = $('#status<c:out value="${servico.getCell(0).getValue()}"/>').val();
                                    if (status == "0") {
                                        $('#submit<c:out value="${servico.getCell(0).getValue()}"/>').hide();
                                        $('#editar<c:out value="${servico.getCell(0).getValue()}"/>').hide();
                                        $('#divstatus<c:out value="${servico.getCell(0).getValue()}"/>').text("encerrado");

                                    } else {
                                        $('#divstatus<c:out value="${servico.getCell(0).getValue()}"/>').text("ativo");
                                    }
                                    var descricao = $('#descricao<c:out value="${servico.getCell(0).getValue()}"/>').text().slice(0, 30);
                                    $('#descricao<c:out value="${servico.getCell(0).getValue()}"/>').text(descricao + "...");

                                    $('#submit<c:out value="${servico.getCell(0).getValue()}"/>').click(function (event) {
                                        var id = $('#idservico<c:out value="${servico.getCell(0).getValue()}"/>').val();
                                        $('#sucess').hide();
                                        $.get('GerenciadorServico', {id: id, editarservico: "encerrar"}, function (responseText) {
                                            $('#sucess').text("o servico:" + id + " " + responseText).show();
                                        });
                                    });
                                });
                            </script>
                            <c:forEach items="${sq.contaChamadosAtendidos(servico.getCell(0).getValue())}" var="cA">
                                <td style="width: 10px"><c:out value="${cA.getChamadosRealizados()}"/>
                                </td> <td>  <c:out value="${cA.getChamadosConcluidos()}"/></td>

                                <td><c:out value="${cA.getTotalDeChamados()}"/></td></c:forEach>
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