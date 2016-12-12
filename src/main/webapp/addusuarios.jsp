


<html>
    <%@include file="valida.jsp" %>
    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta charset="utf-8">
        <title>Usu√°rios - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/usuarios.css">
        <script src="http://code.jquery.com/jquery-latest.js">
        </script>
        <script>
            $(document).ready(function () {
                $('#submit').click(function (event) {
                    var login = $('#login').val();
                    var senha = $('#senha').val();
                    var perfil = $('#perfil').val();
                    var ativo = $('#ativo').val();
                    var nome = $('#nome').val();
                    $.get('UsuarioSrv', {login: login, senha: senha, perfil: perfil, ativo: ativo, nome: nome}, function (responseText) {
                        $('#teste').text(responseText);
                        $('#teste').show();
                    });
                });
            });
        </script>
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
                <div class="alert alert-success" id="teste" role="alert" hidden="true">



                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center;"><strong>ADICIONAR USU¡RIO</strong></div>
                    <div class="panel-body">

                        <div id="painel" class="container">	

                            <form  >

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

                                <br /> <div class="form-group">
                                    <label for="perfil">Perfil</label>
                                    <select id="perfil" name="perfil">
                                        <option  value="1">atendente</option>
                                        <option  value="2">gerente</option>
                                        <option  value="3">administrador</option>
                                    </select>
                                </div>
                                <br /><br /><br />
                                <div class="row text-left">
                                    <div>
                                        <button type="button" id="submit" class="btn btn-primary">Registrar</button>
                                    </div>
                                </div>	




                            </form>


                        </div>
                    </div>






                </div>
            </div>
            <%@include file="footer.html" %>
        </section>





        
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>