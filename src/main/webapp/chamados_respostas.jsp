

<html><%@include file="valida.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta charset="utf-8">
        <title>Chamados - SIS CENTRAL REL</title>   
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="css/chamados.css">
        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>
        <script src="lib/jquery/external/jquery/jquery.js"></script>
        <script src="lib/jquery/jquery.min.js"></script>



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


            <div id="painel2" class="container">




                <div  class="container">

                    <center><div class="alert alert-success" id="sucess" hidden="true" role="alert"></center>
                    <div  class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading" style="text-align: center;"><strong>LISTA <%out.print(request.getParameter("idchamado"));%></strong> - <%out.print(request.getParameter("descricao"));%></div>

                        <ul class="list-group">



                            <table class="table">





                                <thead> 
                                    <tr style="background-color: #e2e1e1;"> 
                                        <th>Nome</th> 
                                        <th>Email</th> 
                                        <th>DDD</th> 
                                        <th>Telefone</th> 
                                        <th>Inválido</th> 

                                    </tr>  
                                </thead>
                                <tbody>
                                    <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                                    <c:forEach items="${sq.ChamadosERespostas(2)}" var="contato">





                                        <tr >
                                            <td> <c:out value="${contato.getPid() }"/></td>

                                        </tr>





                                    </c:forEach>

                                </tbody>
                            </table>  
                    </div>




                </div>


                </ul>



            </div>

        </div>





    </div>
    <%@include file="footer.html" %>
</section>




</body>
<% }%>
</html>