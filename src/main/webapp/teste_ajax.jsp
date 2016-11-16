<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Untitled Document</title>

        <meta charset="utf-8">
        <title>SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/gerenciador_servico.css">





        <script  type="text/javascript">function bloqueio() {
                if (document.getElementById("divprincipal").style.display == "none") {
                    document.getElementById("divprincipal").style.display = "block";
                } else {
                    document.getElementById("divprincipal").style.display = "none";
                }
            }</script>
    </head>
    <body>

        <label>
            <input type="checkbox" name="checkbox" value="checkbox" onclick="bloqueio()" />  
            EXIBIR</label>
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
                    <c:forEach items="${sq.getTitulosList(aL,jL,c, '2')}" var="titulo">




                        <th><c:out value="${titulo.toString()}"></c:out></th>




                    </c:forEach>
                </tr> 
            </thead><tbody>

                <c:forEach items="${sq.Relatorios(aL,jL,c, where,pesquisa)}" var="consulta">
                    <tr>    

                        ${consulta.toString()}



                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>