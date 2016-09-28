
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%

    if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
%>

Voc� n�o est� logado no sistema<br/>
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
        <title>Editar endereço - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/editarend.css">
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
                    <div class="panel-heading">Editar endereço</div>
                    <div class="panel-body">

                        <div id="painel" class="container">	

                            <form method="POST" action="#">

                                <div class="form-group">
                                    <label for="nome">Descrição</label>
                                    <input type="text" name="descricao" id="descricao" class="form-control" placeholder="Descrição do endereço">
                                </div>

                                <div class="form-group">
                                    <label for="nome">Número</label>
                                    <input type="text" name="numero" id="numero" class="form-control" placeholder="Número do endereço">
                                </div>

                                <div class="form-group">
                                    <label for="nome">Bairro</label>
                                    <input type="text" name="bairro" id="bairro" class="form-control" placeholder="Digite o bairro">
                                </div>

                                <div class="form-group">
                                    <label for="nome">CEP</label>
                                    <input type="text" name="cep" id="cep" class="form-control" placeholder="Digite o CEP">
                                </div>

                                <div class="form-group">
                                    <label for="nome">Complemento</label>
                                    <input type="text" name="complemento" id="complemento" class="form-control" placeholder="Digite o complemento">
                                </div>

                                <div class="form-group">
                                    <label for="nome">Município</label>
                                    <input type="text" name="municipio" id="municipio" class="form-control" placeholder="Digite o município">
                                </div>
                            </form>


                                <div class="row text-left">
                                    <div>
                                        <button type="submit" class="btn btn-primary">Editar</button>
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