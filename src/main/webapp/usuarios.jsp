


<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%@include file="valida.jsp" %>
    <head>
        <meta charset="utf-8">
        <title>Usu√°rios - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">

        <script src="lib/jquery/jquery.min.js"></script>

        <script src="https://code.jquery.com/jquery-2.1.4.js" ></script>

        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>


    </head>



    <body>
        <header >  <%@include file="header.html" %></header>
        <section>






            <% if (perfil.contains("gerente")) {
            %><%@include file= 'barra_gerente.jsp' %> 

            <% }%>  <% if (perfil.contains("administrador")) {

            %><%@include file= 'barra_administrador.jsp' %> 
            <% }%>  <% if (perfil.contains("atendente")) {

            %><%@include file= 'barra_atendente.jsp' %> 
            <% }%>



            <div id="painel" class="container">
                <div id="painel" class="container">

                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading" style="text-align: center;"><strong>USU¡RIOS</strong></div>

                        <!-- Table -->
                        <table class="table">
                            <thead> 
                                <tr> 
                                    <th>id</th> 
                                    <th>Nome</th> 
                                    <th>Login</th> 
                                    <th>Ativo</th> 
                                </tr> 
                            </thead> 
                            <tbody> 
                                <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                                <jsp:useBean id="usuario1" class="br.com.minicom.scr.entity.Usuario"/>




                                <c:forEach items="${sq.selectList(usuario1)}" var="usuario2">


                                    <tr>





                                        <th scope="row"> 
                                            <div data-role="main"> <c:out value="${usuario2.getCell(0).getValue()}"/>
                                            </div>
                                        </th> 




                                <div hidden="true"  class="alert alert-success" id="sucesso" role="alert" >
                                </div>
                                <td>
                                    <div data-role="main" > <a href="relatorio_usuario.jsp?nome=<c:out value="${usuario2.getCell(1).getValue()}"/> ">      <c:out value="${usuario2.getCell(1).getValue()}"/> </a>
                                        
                                    </div>


                                </td> 

                                <td>

                                    <div data-role="main" >

                                        <c:out value="${usuario2.getCell(2).getValue()}"/> 
                                    </div>
                                </td> 
                                <td><div class="ui-content" id="divativo<c:out value='${usuario2.getCell(0).getValue()}'/>" ></div></td> 
                                <td style="width: 12px"id="tdbdesaivar<c:out value='${usuario2.getCell(0).getValue()}'/>">
                                    <form id="form2" >
                                        <input type="hidden" name="id" id="id" value=<c:out value="${usuario2.getCell(0).getValue()}"/> >
                                        <input type="hidden" name="ativo" id="ativo" value="<c:out value="${usuario2.getCell(5).getValue()}"/>" >


                                        <div data-role="main" class="ui-content">
                                            <button type="button" id="desativar<c:out value='${usuario2.getCell(0).getValue()}'/>" name="desativar" class="ui-btn-inline btn btn-default"   >

                                            </button>

                                        </div>
                                    </form> </td>

                                <td style="width: 12px"id="tdbeditar<c:out value='${usuario2.getCell(0).getValue()}'/>"> <div data-role="main" class="ui-content">
                                        <button id="editarserv<c:out value='${usuario2.getCell(0).getValue()}'/>" type="button" class=" ui-btn-inline btn btn-default" >Editar usuario </button>
                                        <!-- Single button -->



                                    </div></td>   

                                <script src="http://code.jquery.com/jquery-latest.js">
                                </script>
                                <script>
                                    $(document).ready(function () {
                                        $('#editarserv<c:out value="${usuario2.getCell(0).getValue()}"/>').click(function (event) {
                                            $('#tdbeditar<c:out value="${usuario2.getCell(0).getValue()}"/>').hide();
                                            $('#tdbdesaivar<c:out value="${usuario2.getCell(0).getValue()}"/>').hide();
                                            $('#tdform<c:out value="${usuario2.getCell(0).getValue()}"/>').show();
                                            hide();





                                        });



                                        var ativo = $('#ativo<c:out value="${usuario2.getCell(0).getValue()}"/>').val();
                                        if (ativo == 1) {
                                            $("#divativo<c:out value='${usuario2.getCell(0).getValue()}'/>").text('ativo');
                                            $('#desativar<c:out value="${usuario2.getCell(0).getValue()}"/>').text('desativar');
                                        } else {

                                            $('#desativar<c:out value="${usuario2.getCell(0).getValue()}"/>').text('ativar');
                                            $("#divativo<c:out value='${usuario2.getCell(0).getValue()}'/>").text('desativado');
                                        }

                                        $('#submit${usuario2.getCell(0).getValue()}').click(function (event) {
                                            var id = $('#id<c:out value="${usuario2.getCell(0).getValue()}"/>').val();
                                            var login = $('#login<c:out value="${usuario2.getCell(0).getValue()}"/>').val();
                                            var senha = $('#senha<c:out value="${usuario2.getCell(0).getValue()}"/>').val();
                                            var perfil = $('#perfil<c:out value="${usuario2.getCell(0).getValue()}"/>').val();
                                            var ativo = $('#ativo<c:out value="${usuario2.getCell(0).getValue()}"/>').val();
                                            var nome = $('#nome<c:out value="${usuario2.getCell(0).getValue()}"/> ').val();
                                            $.get('UsuarioSrv', {id: id, senha: senha, perfil: perfil}, function (responseText) {
                                                $('#sucesso').text(responseText).show();
                                                $('#tdbeditar<c:out value="${usuario2.getCell(0).getValue()}"/>').show();
                                                $('#tdbdesaivar<c:out value="${usuario2.getCell(0).getValue()}"/>').show();
                                                $('#tdform<c:out value="${usuario2.getCell(0).getValue()}"/>').hide();

                                            });
                                        });
                                        $('#desativar<c:out value="${usuario2.getCell(0).getValue()}"/>').click(function (event) {
                                            var id = $('#id<c:out value="${usuario2.getCell(0).getValue()}"/>').val();

                                            var ativo = $('#ativo<c:out value="${usuario2.getCell(0).getValue()}"/>').val();

                                            var operacao;
                                            if (ativo == 1) {
                                                operacao = "desativar";
                                                ativo = 0;
                                            } else {
                                                operacao = "ativar";
                                                ativo = 1
                                            }


                                            $.get('UsuarioSrv', {operacao: operacao, id: id, ativo: ativo, }, function (responseText) {
                                                $('#sucesso<c:out value="${usuario2.getCell(0).getValue()}"/>').text(responseText).show();
                                                $('#trsucesso<c:out value="${usuario2.getCell(0).getValue()}"/>').show();
                                            });
                                        });

                                    });
                                </script>
                                <td hidden="true" id="tdform<c:out value="${usuario2.getCell(0).getValue()}"/>">  <div  id="editarserv${usuario2.getCell(0).getValue()}" class="ui-content" style="min-width:250px;" >
                                        <form id="form1">

                                            <input type="hidden" name="id" id="id<c:out value='${usuario2.getCell(0).getValue()}'/>" value=<c:out value="${usuario2.getCell(0).getValue()}"/> >
                                            <label for="senha" class="ui-content">senha:</label>
                                            <input type="password" name="senha" id="senha<c:out value='${usuario2.getCell(0).getValue()}'/>" value=<c:out value="${usuario2.getCell(3).getValue()}"/> >


                                            <input type="hidden" name="nome" id="nome<c:out value='${usuario2.getCell(0).getValue()}'/>" value=<c:out value="${usuario2.getCell(1).getValue()}"/> >


                                            <input type="hidden" name="login" id="login<c:out value='${usuario2.getCell(0).getValue()}'/>" value=<c:out value="${usuario2.getCell(2).getValue()}"/> >
                                            <input type="hidden" name="ativo" id="ativo<c:out value='${usuario2.getCell(0).getValue()}'/>" value=<c:out value="${usuario2.getCell(5).getValue()}"/> >
                                            <label for="perfil">Perfil</label>
                                            <select name="perfil"id="perfil<c:out value='${usuario2.getCell(0).getValue()}'/>">
                                                <option  value="1">atendente</option>
                                                <option  value="2">gerente</option>
                                                <option  value="3">administrador</option>
                                            </select>


                                            <button type="button" id="submit<c:out value='${usuario2.getCell(0).getValue()}'/>" class="ui-btn-inline btn btn-default">Aplicar</button>




                                        </form>
                                    </div></td>
                                </tr>
                                <tr id="trsucesso<c:out value="${usuario2.getCell(0).getValue()}"/>" hidden="true">    
                                    <td  colspan="6" id="td<c:out value='${usuario2.getCell(0).getValue()}'/>"> 
                                        <div hidden="true"  class="alert alert-success" id="sucesso<c:out value='${usuario2.getCell(0).getValue()}'/>" role="alert" >
                                            <c:out value='${usuario2.getCell(0).getValue()}'/></div>
                                    </td>
                                </tr>
                            </c:forEach>



                            <!-- Single button -->






                            </tbody>


                        </table>

                    </div>

                </div>



            </div>









            <footer><%@include file="footer.html" %></footer>


        </section>
    </body> 

</html><%}%>