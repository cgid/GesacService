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

        <body>
            <!-- Table -->
            <c:set var="aL" value='${requestScope["atributosList"]}'/>
            <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
            <table class="table">
                <c:set var="jL" value='${requestScope["JoinList"]}'/>

                <c:set var="where" value='${requestScope["where"]}'/>
                <c:set var="pesquisa" value='${requestScope["pesquisa"]}'/>
                <c:set var="c" value='${requestScope["contadores"]}'/>
                <c:set var="i" value='${0}'/>
                <thead> 
                    <tr> 
                        <c:forEach items="${sq.getTitulosList(aL,jL)}" var="titulo">




                            <th><c:out value="${titulo.toString()}"></c:out></th>




                        </c:forEach>
                    </tr> 
                </thead><tbody>

                    <c:forEach items="${sq.RelatorioUsuario(aL,jL, where,pesquisa)}" var="consulta">
                        <tr>    

                            ${consulta.toString()}



                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </body>

        <%@include file="footer.html" %>



    </section>




</body>

</html>

<%}%>
</html>