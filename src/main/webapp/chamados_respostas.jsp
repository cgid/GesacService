

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








            <table class="table-hover" >

                <thead class="btn-toolbar" >
                <th>pid</th>
                <th>estabelecimento</th>
                <th>Obs</th>

                <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                <c:forEach items="${sq.getTitulosList(param.servico)}" var="contato">
                    <th><label style="width: 100px"><c:out value="${contato.toString()}"/></label></th>
                        </c:forEach>

                </thead>


                <tbody >

                    <c:forEach items="${sq.ChamadosERespostas(param.servico)}" var="contato">

                        <tr >




                            <td style="width: 50px;"> <a href="relatorio_pid.jsp?pid=<c:out value='${contato.getPid() }'/>"><c:out value='${contato.getPid() }'/></a></td>
                            <td style="width: 50px;"> <c:out value="${contato.getEstabelecimento() }"/></td>
                             <td style="width: 50px;"> <c:out value="${contato.getObs() }"/></td>
                            <c:forEach items="${contato.getRepostas()}" var="teste">
                                <td style="width: 50px;"> <p style="width: 50px;"><c:out value="${teste.toString() }"/></p></td>
                            </c:forEach>



                        </tr> 


                    </c:forEach>

                </tbody>
            </table>










            <%@include file="footer.html" %>
        </section>




    </body>
    <% }%>
</html>