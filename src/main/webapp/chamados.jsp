<%@page import="br.com.minicom.scr.entity.Municipio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.query.Queries"%>
<%@page import="br.com.minicom.scr.consultas.ChamadoConsulta"%>
<%@page import="br.com.minicom.scr.entity.Usuario;"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries;"%>
<%@page import ="java.util.HashMap;"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.minicom.scr.consultas.ChamadoConsulta"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%

    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>

Você não está logado no sistema<br/>
<a href="index.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
    String userid = String.valueOf(session.getAttribute("userid"));
    String pwd = String.valueOf(session.getAttribute("senha"));
    String pid = String.valueOf(session.getAttribute("pid"));

    Usuario usuario = new Usuario();

    String perfil = usuario.autenticarPerfil(userid, pwd);
    perfil = "index_" + perfil + ".jsp";
    SimpleQueries queries = new SimpleQueries();

    List<ChamadoConsulta> consultas = queries.getChamado("12209", "17");
    String[][] filtro = new String[consultas.size()][3];
    for (int i = 0; i < consultas.size(); i++) {
        for (int j = 0; j < 3; j++) {
            if (j == 0) {

                filtro[i][j] = consultas.get(i).getNome();
            }
            if (j == 1) {
                filtro[i][j] = consultas.get(i).getDdd();
            }
            if (j == 2) {
                filtro[i][j] = consultas.get(i).getTelefone();
            }
        }
    }
    List<String> perguntasList = queries.getPerguntas("12208", "17");


%>





<html>

    <head>
        <meta charset="utf-8">
        <title>Chamados - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/chamados.css">
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
                    <!-- Default panel contents -->
                    <div class="panel-heading">Chamado - <b>PID:<%out.print(consultas.get(1).getCodPid());%></b></div>

                    <!-- Table -->
                    <ul class="list-group">

                        <li class="list-group-item">Endereço:<% out.print(consultas.get(1).getDescricao()
                                    + ",Bairro: " + consultas.get(1).getBairro() + ", Numero: " + consultas.get(1).getNumero()
                                    + ", Complemento:" + consultas.get(1).getComplemento() + ", Municipio:" + consultas.get(1).getNomeMunicipio()
                                    + ", UF:" + consultas.get(1).getUf());%> </li><%for (int i = 0; i < consultas.size(); i++) {
                                            out.print("    <li class='list-group-item'>Contato: " + consultas.get(i).getNome() + " Numero: " + consultas.get(i).getDdd() + "-" + consultas.get(i).getTelefone() + "</li>");
                                        }%>



                        <li class="list-group-item">


                            <form method="POST" action="#">
                                <%for (int i = 0; i < perguntasList.size(); i++) {
                                        out.print("<div class='form-group'>");
                                        out.print("  <div class='form-group'>      <label for='nome'>Pergunta " + i + ":</label>");
                                        out.print(" <div class='well well-sm'>" + perguntasList.get(i) + "</div>");
                                        out.print("  <input type='text' name='resposta' id='resposta" + i + "' class='form - control' placeholder='Resposta'> </div> ");
                                    }%>




                                <div class="row text-left">
                                    <div>          
                                        <button type="submit" class="btn btn-primary besquerda">Enviar Repostas</button>
                                    </div>
                                </div>	
                        </li>

                        </form>

                    </ul>

                    <div class="row text-right"> 

                        <td><a href="editar_endereco.jsp"><button class="btn btn-primary text-center">Editar endereço</button></a></td>

                    </div>

                </div>

            </div>


        </section>


      <%@include file="footer.html" %>


        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>

</html>
<%}%>