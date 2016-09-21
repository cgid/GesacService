<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%

    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>

Você não está logado no sistema<br/>
<a href="index.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
    String userid = String.valueOf(session.getAttribute("userid"));
    String pwd = String.valueOf(session.getAttribute("senha"));

    Usuario usuario = new Usuario();

    String perfil = usuario.autenticarPerfil(userid, pwd);
    perfil = "index_" + perfil + ".jsp";
%>




<html>

    <head>
        <meta charset="utf-8">
        <title>Chamados - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/chamados.css">
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



            <div id="painel" class="container">

                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Adicionar servico</div>

                    <!-- Table -->
                    <ul class="list-group">
                        <li class="list-group-item">


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
                                </li>


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

                    </ul>




                </div>

            </div>


        </section>

      <%@include file="footer.html" %>


        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>
    <% }%>
</html>