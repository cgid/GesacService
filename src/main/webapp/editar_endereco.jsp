
<html>
    <%@include file="valida.jsp" %>
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