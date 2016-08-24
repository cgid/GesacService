<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
Voc� n�o est� logado no sistema<br/>
<a href="login1.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
%>
<html>

    <head>
        <meta charset="utf-8">
        <title>Serviço - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/addservico.css">
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
                                <li><a href="solicitante_index.html"> inicio</a></li>
                                <li><a href="addservico.html"> Servi�os</a></li></a>                                       

                            </ul>

                        </nav>

                    </div>

                </div>


            </div>	

            <div id="painel" class="container">

                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Adicionar serviço</div>

                    <!-- Table -->
                    <ul class="list-group">
                        <li class="list-group-item">


                            <form method="post" action="servicoSrv"  enctype="multipart/form-data">

                                <div class="form-group">
                                    <label for="descri�ao">Descrição</label>
                                    <input type="text" name="descricao" id="descricao" class="form-control" placeholder="Digite a descrição do serviço" >
                                </div>

                                <div class="form-group">
                                    <label for="pergunta">Intervalo de ligações</label>
                                    <input type="text" name="intervalo" id="intervalo" class="form-control" placeholder="Digite o intervalo das ligações">
                                </div>

                                <div class="form-group">
                                    <label for="pergunta">Pergunta</label>
                                    <input type="text" name="pergunta" id="pergunta" class="form-control" placeholder="Digite a pergunta">
                                </div>

                                <div class="form-group">
                                    <label for="pergunta">Pergunta</label>
                                    <input type="text" name="pergunta" id="pergunta" class="form-control" placeholder="Digite a pergunta">
                                </div>


                                <label for="nome">Mais Perguntas</label>
                                <div id="fields"></div>

                                <div class="row text-left">
                                    <div>          
                                        <button id="btn-add-input-text" type="button" class="btn btn-primary besquerda">Adicionar pergunta</button>
                                    </div>
                                </div>	
                                <br>
                                <label for="nome">Adicionar lista de PIDS</label>
                                <input name= "planilha" type="file" accept=".xls,.xlsx"><br>
                                </li>


                                <div class="row text-right">

                                    <td><button type="submit" id="upload" name="upload"  class="btn btn-primary text-center">Adicionar serviço</button></td>

                                </div>
                            </form>


                            <script src="https://code.jquery.com/jquery-2.1.4.js"></script>

                            <script>
                                $(function () {
                                    var count = 0;

                                    $("#btn-add-input-text").click(function () {
                                        $("#fields").append($("<input/>").attr({
                                            class: "form-control",
                                            type: "text",
                                            placeholder: "Digite a pergunta",
                                            name: "pergunta" + count++
                                        }));

                                        $("#fields").append("<br/><br/>");
                                    });
                                });
                            </script>

                    </ul>



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

</html><% }%>