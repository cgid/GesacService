<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.Entity"%>
<%@page import="br.com.minicom.scr.entity.Servico"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>


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
                                    <td> <c:out value="${servico.getCell(2).getValue()}"/></td>
                                    <td> <c:out value="${servico.getCell(3).getValue()}"/></td>  
                                    <td>
                                        <!-- Single button -->
                                        <div class="btn-group">
                                            <button type='button' onclick='${sq.delete(servico)}' class='btn btn-default'>Deletar</button>
                                            <button type='button' onclick='${sq.delete(servico)}' class='btn btn-default'>encerrar</button>
                                        </div>
                                        
                                    </td>
                                </tr> 
                                

                            </tbody>
                        </c:forEach>
                    </table>


                </div>





            </div>




            <%@include file="footer.html" %>

        </section>




        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>