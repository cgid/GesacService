
<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
Voc� n�o est� logado no sistema<br/>
<a href="login.html">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
%>
<html>

		<head>
			<meta charset="utf-8">
			<title>Login - SIS CENTRAL REL</title>
			<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css" href="css/index.css">
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
                            <li><a href="#"> Início</a></li>
                            <li><a href="#"> Perfil</a></li></a>
                            <li><a href="#"> Chamados</a></li></a>  
                            <li><a href="#"> Solicitações</a></li></a> 
                            <li><a href="#"> Opção 4</a></li></a> 
                            <li><a href="#"> Opção 5</a></li></a> 
                            <li><a href="#"> Opção 6</a></li></a> 
                            <li><a href="#"> Opção 7</a></li></a> 
                            <li><a href="#"> Opção 8</a></li></a>                                        

                        </ul>

                  </nav>

                  </div>

				</div>


			</div>	


			<div class="container">
			<div class="alert alert-success" role="alert">
  			<a href="#" class="alert-link">Bem vindo ao SIS CENTRAL REL. Logado com sucesso!</a>
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
<%
    }
%>
