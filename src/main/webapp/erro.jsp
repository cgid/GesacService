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
        <title>Chamados - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/chamados.css">
    </head>


    <body>


        <header 
   <%@include file="header.html" %>

    </header>
        <section>
            <div>
            <% if (perfil.contains("gerente")) {
            %><%@include file= 'barra_gerente.jsp' %> 

            <% }%>  <% if (perfil.contains("administrador")) {

            %><%@include file= 'barra_administrador.jsp' %> 
            <% }%>  <% if (perfil.contains("atendente")) {

            %><%@include file= 'barra_atendente.jsp' %> 
            <% }%>

            </div>


            <div class="container">



                <div class="erro">
                    <h2>ERRO</h2>
                    <p>A operação não pode ser concluida! Caso o erro persista entre em contato com o suporte.</p>
                </div>   


            </div><footer><%@include file="footer.html" %></footer>
     
        </section>

   


        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>