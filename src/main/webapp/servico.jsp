<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.com.minicom.scr.persistence.Entity"%>
<%@page import="br.com.minicom.scr.entity.Servico"%>
<%@page import="br.com.minicom.scr.entity.Municipio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.query.Queries"%>
<%@page import="br.com.minicom.scr.consultas.ChamadoConsulta"%>
<%@page import="br.com.minicom.scr.entity.Usuario;"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries;"%>
<%@page import ="java.util.HashMap;"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.minicom.scr.consultas.ChamadoConsulta"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%

    if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
%>

Você não está logado no sistema<br/>
<a href="index.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
    String login = String.valueOf(session.getAttribute("login"));
    String pwd = String.valueOf(session.getAttribute("senha"));
    String pid = String.valueOf(session.getAttribute("pid"));

    Usuario usuario = new Usuario();

    String perfil = usuario.autenticarPerfil(login, pwd);
    perfil = "index_" + perfil + ".jsp";
    SimpleQueries queries = new SimpleQueries();
    Servico servico = new Servico();
    List<Entity> consultas = queries.selectList(servico);


%>




<html>

    <head>
        <meta charset="utf-8">
        <title>Chamados - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/chamados.css">
    </head>


    <body>

        <header><%@include file="header.html" %></header>
        


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
                    <div class="panel-heading">Serviços</div>

                    <!-- Table -->
                    <ul class="list-group">
                        <li class="list-group-item">


                            <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>

                            <c:forEach items="${sq.selectList()}" var="servico">

                                <a  href="chamados.jsp?idchamado=<c:out value="${servico.getCell(0).getValue()}"/>"class='list-group-item'onclick=''>
                                    <h4 class='list-group-item-heading' >Servico <c:out value="${servico.getCell(0).getValue()}"/> </h4>
                                    <h4 class='list-group-item-heading' id = "<c:out value="${servico.getCell(0).getValue()}"/>}"><c:out value="${servico.getCell(1).getValue()}"/> </h4>
                                    <p class='list-group-item-text'>${servico.getCell(2).getValue()}</p>
                                </a>
                            </c:forEach>




                        </li>
                    </ul>

                </div>
            </div>
  
                            <footer><%@include file="footer.html" %></footer>      
            

        </section>




        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>
    <%}%>
</html>

