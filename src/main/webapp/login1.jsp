
<html>

    <head>
        <meta charset="utf-8">
        <title>Login - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>


    <body>

        <header>
            <div class="container">

                <div id="banner">

                    <h1>SIS CENTRAL REL<small>Minist�rio das Comunica��es</small></h1>

                </div>
            </div>

        </header>


        <section>

            <div id="painellogin" class="container">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Login</h3>
                    </div>
                    <div class="panel-body">

                        <form method="post" action="login.jsp">
                            <div class="form-group">
                                <label for="login">Usuario</label>
                                <input type="text" name="login" id="login" class="form-control" placeholder="Digite seu usuário" required>
                            </div>

                            <div class="form-group">
                                <label for="senha">Senha</label>
                                <input type="password" name="senha" id="senha" class="form-control" placeholder="Digite sua senha" required>
                            </div>


                            <div class="row text-center">
                                <button type="submit" class="btn btn-primary">Entrar</button>
                                <button type="reset" class="btn btn-primary">Limpar</button>
                            </div>


                        </form>

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

</html>