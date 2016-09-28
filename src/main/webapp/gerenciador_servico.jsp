
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.Entity"%>
<%@page import="br.com.minicom.scr.entity.Servico"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>


<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%

    if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
%>

Você não está logado no sistema<br/>
<a href="index.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
    String login = String.valueOf(session.getAttribute("login"));
    String pwd = String.valueOf(session.getAttribute("senha"));

    Usuario usuario = new Usuario();

    String perfil = usuario.autenticarPerfil(login, pwd);
    perfil = "index_" + perfil + ".jsp";
%>


<html>

    <head>
        <meta charset="utf-8">
        <title> SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/gerenciador_servico.css">
    </head><link rel="stylesheet" href="lib/jquery/jquery.mobile-1.4.5.css">



    <script>
        function form() {

            var form = document.getElementById("form");
            form.style.hidden = 'false';
        }
        ;


    </script>
    <body>
        <header> <%@include file="header.html" %></header>


        <section>

            <% if (perfil.contains("gerente")) {
            %><%@include file= 'barra_gerente.jsp' %> 

            <% }%>  <% if (perfil.contains("administrador")) {

            %><%@include file= 'barra_administrador.jsp' %> 
            <% }%>  <% if (perfil.contains("atendente")) {

            %><%@include file= 'barra_atendente.jsp' %> 
            <% }%>
            <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>


            <div id="painel" class="container">

                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Servicos</div>

                    <!-- Table -->
                    <table class="table">

                        <thead> 
                            <tr> 
                                <th>ID</th> 
                                <th>Descrição</th> 
                                <th>Data</th> 
                                <th>Ações</th> 
                            </tr> 

                        </thead>
                        <c:forEach items="${sq.selectList()}" var="servico">


                            <tbody> 
                                <tr> 
                                    <th scope=row'><c:out value="${servico.getCell(0).getValue()}"/></th>
                                    <td> <c:out value="${servico.getCell(2).getValue()}"/><div data-role="popup" id="editarserv${servico.getCell(0).getValue()}" class="ui-content" style="min-width:250px;">
                                            <form method="POST" action="servicoSrv" enctype="multipart/form-data">
                                                <div class="form-group" hidden="true">
                                                    <label hidden="true" for="id">id</label>
                                                    <input value="${servico.getCell(0).getValue()}"type="hide" name="id" id="id" class="form-control" >
                                                </div>
                                                <div class="form-group">
                                                    <label for="nome">Descrição</label>
                                                    <input type="text" name="descricao" id="descricao" class="form-control" placeholder="Digite a descrição do serviço">
                                                </div>

                                                <div class="form-group">
                                                    <label for="nome">Intervalo de ligaçoes</label>
                                                    <input type="text" name="intervaloligacoes" id="intervaloligacoes" class="form-control" placeholder="Digite o intervalo das ligações">
                                                </div>


                                                <label for="nome">Adicionar lista de PIDS</label>
                                                <input type="file" name="planilha" accept=".xlsx"><br>
                                                </li>


                                                <div class="row text-right">

                                                    <button type="submit" class="btn btn-primary text-center">Adicionar serviços</button>

                                                </div>
                                            </form>
                                        </div></td>
                                    <td> <c:out value="${servico.getCell(3).getValue()}"/></td>  

                                    <td>

                                        <div data-role="main" class="ui-content">
                                            <a href="#editarserv${servico.getCell(0).getValue()} " data-rel="popup" type="button" class=" ui-btn-inline btn btn-default">Editar servico </a>

                                        </div> </td>
                                    <td>
                                        <div data-role="main" class="ui-content">
                                            <a href="#adicionarperguntas" data-rel="popup" type="button" class="btn btn-default btn-group- ">Adicionar perguntas </a>

                                            <div data-role="popup" id="adicionarperguntas" class="ui-content" style="min-width:250px;">
                                                <form method="post" action="demoform.asp">
                                                    <div>

                                                        <label for="perguntas" class="ui-btn-inline">perguntas:</label>
                                                        <input type="text" name="pergunta" id="pergunta" >
                                                        <label for="nome">Mais Perguntas</label>
                                                        <div id="fields"></div>

                                                        <div class="row text-left">
                                                            <div>          
                                                                <button id="btn-add-input-text" type="button" class="btn btn-primary besquerda">Adicionar pergunta</button>
                                                            </div>
                                                        </div>	

                                                        <input type="submit" data-inline="true" value="Editar">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                    </td>

                                </tr> 

                            </tbody>


                        </c:forEach>
                        <td>

                            <div data-role="main" class="ui-content">
                                <a href="#addServico" data-rel="popup" type="button" class=" ui-btn-inline btn btn-default">Editar servico </a>

                            </div> </td>
                        <td>
                    </table>

                    <script src="lib/jquery/external/jquery/jquery.js"></script>
                    <script src="lib/jquery/jquery.min.js"></script>
                    <script src="lib/jquery/jquery.mobile-1.4.5.js"   </script>
                    <script src="https://code.jquery.com/jquery-2.1.4.js"></script>
                    <script>
        $(function () {
            var count = 0;

            $("#btn-add-input-text").click(function () {
                $("#fields").append($("<input/>").attr({
                    class: "form-control",
                    type: "text",
                    placeholder: "Digite a pergunta",
                    name: "pergunta" + count++
                }));

                $("#fields").append("<br/><br/>");
            });
        });
                    </script>

                </div>





            </div>

            <div data-role="popup" id="addServico" class="ui-content" style="min-width:250px;">
              
           
                <form method="POST" action="servicoSrv" enctype="multipart/form-data">

                    <div class="form-group">
                        <label for="nome">Descrição</label>
                        <input type="text" name="descricao" id="descricao" class="form-control" placeholder="Digite a descrição do serviço">
                    </div>

                    <div class="form-group">
                        <label for="nome">Intervalo de ligaçoes</label>
                        <input type="text" name="intervaloligacoes" id="intervaloligacoes" class="form-control" placeholder="Digite o intervalo das ligações">
                    </div>

                    <div class="form-group">
                        <label for="nome">Pergunta</label>
                        <input type="text" name="pergunta" id="pergunta" class="form-control" placeholder="Digite a pergunta">
                    </div>

                    <div class="form-group">
                        <label for="nome">Pergunta</label>
                        <input type="text" name="pergunta" id="pergunta" class="form-control" placeholder="Digite a pergunta">
                    </div>


                    <label for="nome">Mais Perguntas</label>
                    <div id="fields"></div>

                    <div class="row text-left">
                        <div>          
                            <button id="btn-add-input-text" type="button" class="btn btn-primary besquerda">Adicionar pergunta</button>
                        </div>
                    </div>	
                    <br>
                    <label for="nome">Adicionar lista de PIDS</label>
                    <input type="file" name="planilha" accept=".xlsx"><br>



                    <div class="row text-right">

                        <td><button type="submit" class="btn btn-primary text-center">Adicionar serviços</button></td>

                    </div>
                </form>


                <script src="https://code.jquery.com/jquery-2.1.4.js"></script>

                <script>
        $(function () {
            var count = 0;

            $("#btn-add-input-text").click(function () {
                $("#fields").append($("<input/>").attr({
                    class: "form-control",
                    type: "text",
                    placeholder: "Digite a pergunta",
                    name: "pergunta" + count++
                }));

                $("#fields").append("<br/><br/>");
            });
        });
                </script>



            </div>
            <footer><%@include file="footer.html" %></footer>

        </section




        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>