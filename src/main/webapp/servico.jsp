<html>

    <%@page import="br.com.minicom.scr.persistence.Entity"%>
    <%@page import="br.com.minicom.scr.entity.Servico"%>
    <%@page import="br.com.minicom.scr.entity.Municipio"%>

    <%@page import="java.util.List"%>


    <%@page import="br.com.minicom.scr.entity.Usuario;"%>
    <%@page import="br.com.minicom.scr.persistence.query.SimpleQueries;"%>
    <%@page import ="java.util.HashMap;"%>

    <%@page import="java.util.ArrayList"%>




    <%@include file="valida.jsp" %>

    <head>
        <meta charset="utf-8">
        <title>Chamados - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/chamados.css">
    </head>
   
    <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <body >
        <%@include file="header.html" %>
        <script src="http://code.jquery.com/jquery-latest.js"></script>


        <section>
            <% if (perfil.contains("gerente")) {
            %><%@include file= 'barra_gerente.jsp' %> 

            <% }%>  <% if (perfil.contains("administrador")) {

            %><%@include file= 'barra_administrador.jsp' %> 
            <% }%>  <% if (perfil.contains("atendente")) {

            %><%@include file= 'barra_atendente.jsp' %> 
            <% }%>


            
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading" style="text-align: center;" ><strong>SERVIÇOS</strong></div>

                    <!-- Table -->
                    <ul class="list-group">
                        <li class="list-group-item">

                            <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                            <jsp:useBean id="srv" class="br.com.minicom.scr.entity.Servico"/>

                            <c:forEach items="${sq.selectList(srv)}" var="servico">
                              <script>
                                    $(document).ready(function () {
                                        ativo = $('#status<c:out value="${servico.getCell(0).getValue()}"/>').text();
                                        if (ativo == 1) {
                                            $('#status<c:out value="${servico.getCell(0).getValue()}"/>').hide();
                                            var texto = $('#descricao').text().slice(0, 10);
                                            $('#descricao').text(texto + '...');
                                        } else {

                                            $('#status<c:out value="${servico.getCell(0).getValue()}"/>').hide();
                                            $('#h<c:out value="${servico.getCell(0).getValue()}"/>').hide();

                                        }




                                    });
                                </script>
                                <a  id="h<c:out value='${servico.getCell(0).getValue()}'/>" href="chamados.jsp?idchamado=<c:out value='${servico.getCell(0).getValue()}'/>&descricao=<c:out value='${servico.getCell(2).getValue()}'/>"class='list-group-item'>
                                    <h4 class='list-group-item-heading' >Serviço <c:out value="${servico.getCell(0).getValue()}"/> </h4>
                                    <h5 class='list-group-item-heading' id="descricao" ><c:out value="${servico.getCell(2).getValue()}"/></h5>
                                    <p id="status<c:out value='${servico.getCell(0).getValue()}'/>" class='list-group-item-text'><c:out value='${servico.getCell(4).getValue()}'/></p>

                                </a>
                                
                                
                              
                            </c:forEach>

                      


                        </li>
                    </ul>

                </div>
            </div>


            <%@include file="footer.html" %>



        </section>




    </body>

</html>

<%}%>