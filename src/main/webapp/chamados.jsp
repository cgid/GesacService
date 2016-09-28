<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    String servico = request.getParameter("idchamado");
    String perfil = usuario.autenticarPerfil(login, pwd);
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
                    <div class="panel-heading">Chamado <%out.print(servico);%></div>

                    <!-- Table -->
                    <ul class="list-group">
                        <li class="list-group-item">


                            <form method="POST" action="ChamadoSrv" enctype="multipart/form-data">
                                <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                                <c:forEach items="${sq.getChamado(param.idchamado)}" var="consulta">
                                    <div class="form-group">
                                        <label for="nome">PID: <c:out value="${consulta.getCodPid()}"/></label>
                                    </div>
                                    <div  class="form-group">
                                        <label for="nome"> Endereço: <c:out value="${consulta.getNomeEstabelecimento()}"/></label>



                                        <label for="nome"><c:out value="${consulta.getDescricao()}"/></label>
                                        <label for="nome"><c:out value="${consulta.getNumero()}"/></label>
                                        <label for="nome"><c:out value="${consulta.getComplemento()}"/></label>
                                        <label for="nome"><c:out value="${consulta.getBairro()}"/></label>
                                        <label for="nome"><c:out value="${consulta.getNomeMunicipio()}"/></label>
                                        <label for="nome"><c:out value="${consulta.getUf()}"/></label>

                                    </div>

                                    <div class="form-group">

                                        <input type="hidden" name="idSolicitacao" id="idSolicitacao" class="form-control" value="<c:out value='${consulta.getId_solicitacao()}'/>">
                                    </div>

                                </c:forEach>
                                <c:forEach items="${sq.getContatos(param.idchamado)}" var="contato">


                                    <div class="form-group">
                                        <label for="nome">  <c:out value="${contato.getNome()}"/> :</label>
                                        <label for="nome">  <c:out value="${contato.getDdd()}"/></label>
                                        <label for="nome">  <c:out value="${contato.getTelefone()}"/></label>

                                    </div>

                                </c:forEach>
                                <div class="form-group">
                                    <label for="nome">Observação</label>
                                    <input type="text" name="observacao" id="observacao" class="form-control" placeholder="observacao">
                                </div>

                                <c:forEach items="${sq.getPerguntas(param.idchamado)}" var="Perguntas">
                                    <div class="form-group">
                                        <label for="nome"><c:out value="${Perguntas.getCell(1).getValue()}"/> <c:out value="${Perguntas.getCell(0).getValue()}"/></label>
                                        <input type="text" name="resposta <c:out value="${Perguntas.getCell(0).getValue()}"/>" id="resposta <c:out value="${Perguntas.getCell(0).getValue()}"/>" class="form-control" placeholder="Resposta">
                                    </div>

                                </c:forEach>
                                <div class="form-group">
                                    <label for="nome">Contato ok?</label>
                                    <input type="radio" name="ok" id="ok " size="10px"  value="1">Sim 
                                    <input type="radio" name="ok" id="ok " class="form-group" value="0">Não
                                </div> <div class="form-group">
                                    <label for="nome">Contato Realizado?</label>
                                    <input type="radio" name="sucesso" id="sucesso " size="10px"  value="1">Sim 
                                    <input type="radio" name="sucesso" id="sucesso " class="form-group" value="0">Não
                                </div>
                        </li>


                        <div class="row text-right">

                            <td><button type="submit" class="btn btn-primary text-center">Adicionar serviços</button></td>

                        </div>
                        </form>


                        <script src="https://code.jquery.com/jquery-2.1.4.js"></script>



                    </ul>




                </div>

            </div>
            <%@include file="footer.html" %>
        </section>





        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>
    <% }%>
</html>