<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>UsuÃ¡rios - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">
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
                    <div class="panel-heading">Usuarios</div>

                    <!-- Table -->
                    <table class="table">
                        <thead> 
                            <tr> 
                                <th>id</th> 
                                <th>Nome</th> 
                                <th>Login</th> 
                                <th>Açes</th> 
                            </tr> 
                        </thead> 
                        <tbody> 
                            <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                            <jsp:useBean id="usuario1" class="br.com.minicom.scr.entity.Usuario"/>
                            <jsp:useBean id="usuariosrv" class="br.com.minicom.scr.servlet.UsuarioSrv"/>



                            <c:forEach items="${sq.selectList(usuario1)}" var="usuario">
                                <tr> 
                                    <th scope="row"><c:out value="${usuario.getCell(0).getValue()}"/></th> 
                                    <td><c:out value="${usuario.getCell(1).getValue()}"/></td> 
                                    <td><c:out value="${usuario.getCell(2).getValue()}"/></td> 
                                    <td>
                                        <!-- Single button -->
                                      <div class="btn-group">
                                            <button type='button' onclick='' class='btn btn-default'>Deletar</button>
                                            <button type='button' onclick='' class='btn btn-default'>editar</button>
                                        </div>
                                    </td>

                                </tr> 
                            </c:forEach>

                        </tbody>

                    </table>
                    <div class="row text-right">
                        <a href="addusuarios.jsp" class="btn btn-primary">Adicionar Usuario</a>
                    </div>
                </div>





            </div>




            <%@include file="footer.html" %>


        </section>





        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>