<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

    if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
%>

Você não está logado no sistema<br/>
<a href="login.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
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
        <title>UsuÃ¡rios - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">
        <link rel="stylesheet" href="lib/jquery/jquery.mobile-1.4.5.css">
        <script src="lib/jquery/jquery.min.js"></script>
        <script src="lib/jquery/jquery.mobile-1.4.5.js"   </script>
        <script src="https://code.jquery.com/jquery-2.1.4.js"></script>

        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>
    </head>

    <header >  <%@include file="header.html" %></header>

    <body>
        <section>






            <% if (perfil.contains("gerente")) {
            %><%@include file= 'barra_gerente.jsp' %> 

            <% }%>  <% if (perfil.contains("administrador")) {

            %><%@include file= 'barra_administrador.jsp' %> 
            <% }%>  <% if (perfil.contains("atendente")) {

            %><%@include file= 'barra_atendente.jsp' %> 
            <% }%>

            <c:set value="${param.nome}" var="nome"/>
            <c:set value="${param.login}" var="login"/>
            <c:set value="${param.senha}" var="senha"/>
            <c:set value="${param.perfil}" var="perfil"/>

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
                                <th>Senha</th> 
                            </tr> 
                        </thead> 
                        <tbody> 
                            <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                            <jsp:useBean id="usuario1" class="br.com.minicom.scr.entity.Usuario"/>
                            <jsp:useBean id="usuariosrv" class="br.com.minicom.scr.servlet.UsuarioSrv"/>



                            <c:forEach items="${sq.selectList(usuario1)}" var="usuario">
                                <tr>


                                    </div>

                                    <th scope="row"><c:out value="${usuario.getCell(0).getValue()}"/></th> 
                                    <td><c:out value="${usuario.getCell(1).getValue()}"/>   <div data-role="popup" id="editarserv${usuario.getCell(0).getValue()}" class="ui-content" style="min-width:250px;">
                                            <form method="POST" action="update.jsp" enctype="multipart/form-data" >
                                                <h3>Usuario : ${usuario.getCell(1).getValue()}  ${usuario.getCell(0).getValue()}</h3>
                                                <input type="hidden" name="id" id="id" value=<c:out value="${usuario.getCell(0).getValue()}"/> >
                                                <label for="nome" class="ui-btn-inline">Nome:</label>
                                                <input type="text" name="nome" id="nome"  >
                                                <label for="login" class="ui-content">Login:</label>
                                                <input type="text" name="login" id="login" >
                                                <label for="senha" class="ui-content">senha:</label>
                                                <input type="password" name="senha" id="senha" >
                                                <label for="perfil">Perfil</label>
                                                <select name="perfil">
                                                    <option  value="1">atendente</option>
                                                    <option  value="2">gerente</option>
                                                    <option  value="3">administrador</option>
                                                </select>
                                                <button type="submit" class="btn btn-primary text-center">Editar</button>



                                            </form></div></td> 

                                    <td><c:out value="${usuario.getCell(2).getValue()}"/></td> 
                                    <td><c:out value="${usuario.getCell(2).getValue()}"/>
                                    <td>
                                        <div data-role="main" class="ui-content">
                                            <a href="#editarserv${usuario.getCell(0).getValue()} " data-rel="popup" type="button" class=" ui-btn-inline btn btn-default">Editar usuario </a>
                                            <!-- Single button -->
                                            <div class="btn-group">

                                                <button type='button' onclick='' class='btn btn-default'>excluir</button>
                                            </div>
                                        </div></td>


                                </c:forEach>




                            </tr> 
                        <li><a type="button" href="addusuarios.jsp"> Adicionar Usuarios</a></li>
                        </tbody>

                    </table>

                </div>

            </div>





        </div>








        <footer><%@include file="footer.html" %></footer>


    </section>
</body> 

</html><%}%>