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
                    <div class="panel-heading">Adicionar Usuario</div>
                    <div class="panel-body">

                        <div id="painel" class="container">	

                            <form method="POST" action="UsuarioSrv" enctype="multipart/form-data">

                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" name="nome" id="nome" class="form-control" placeholder="Nome do Usuario">
                                </div>

                                <div class="form-group">
                                    <label for="login">Login</label>
                                    <input type="text" name="login" id="login" class="form-control" placeholder="Login">
                                </div>

                                <div class="form-group">
                                    <label for="senha">Senha</label>
                                    <input type="password" name="senha" id="senha" class="form-control" placeholder="Senha">
                                </div>

                                <br />
                                <label for="perfil">Perfil</label>
                                <select name="perfil">
                                    <option  value="1">atendente</option>
                                    <option  value="2">gerente</option>
                                    <option  value="3">administrador</option>
                                </select>
                                <br /><br /><br />
                                <div class="row text-left">
                                    <div>
                                        <button type="submit" class="btn btn-primary">Registrar</button>
                                    </div>
                                </div>	




                            </form>


                        </div>
                    </div>






                </div>
            </div>

        </section>


        <%@include file="footer.html" %>


        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>