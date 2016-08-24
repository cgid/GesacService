<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
Voc� n�o est� logado no sistema<br/>
<a href="index.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
%>

<html>

    <head>
        <meta charset="utf-8">
        <title>Chamados - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/chamados.css">
    </head>


    <body>

        <header>
            <div class="container">

                <div id="banner">

                    <h1>SIS CENTRAL REL<small>Ministério das Comunicações</small></h1>

                </div>
            </div>

        </header>


        <section>

            <div class="container">

                <div class="menu">

                    <div class="row">

                        <nav id="menu" class="pull-left">
                            <ul>
                                <li><a href="index.html"> Início</a></li>
                                <li><a href="chamados.html"> Chamados</a></li></a>  
                                <li><a href="addservico.jsp"> Serviços</a></li></a> 


                            </ul>

                        </nav>

                    </div>

                </div>


            </div>	

            <div id="painel" class="container">

                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Chamado - <b>PID: 39847</b></div>

                    <!-- Table -->
                    <ul class="list-group">
                        <li class="list-group-item">Contato: Fulano</li>
                        <li class="list-group-item">Endereço: Endereço Teste</li>
                        <li class="list-group-item">Telefone: Telefone Teste</li>

                        <li class="list-group-item">


                            <form method="POST" action="#">

                                <div class="form-group">
                                    <label for="nome">Pergunta 1</label>
                                    <div class="well well-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                                    <input type="text" name="resposta" id="resposta" class="form-control" placeholder="Resposta">
                                </div>

                                <div class="form-group">
                                    <label for="nome">Pergunta 2</label>
                                    <div class="well well-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                                    <input type="text" name="resposta" id="resposta" class="form-control" placeholder="Resposta">
                                </div>

                                <div class="row text-left">
                                    <div>          
                                        <button type="submit" class="btn btn-primary besquerda">Enviar Repostas</button>
                                    </div>
                                </div>	
                        </li>

                        </form>

                    </ul>

                    <div class="row text-right"> 

                        <td><a href="editarend.html"><button class="btn btn-primary text-center">Editar endereço</button></a></td>

                    </div>

                </div>

            </div>


        </section>


        <footer>

            <div class="row row-cinza-escuro">

                <div class="container">

                    <p class="pull-left">Todos os direitos reservados © SIS CENTRAL REL.</p>

                </div>

            </div>

        </footer>


        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html><%}%>