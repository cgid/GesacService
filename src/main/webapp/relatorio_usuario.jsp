<%@include file="valida.jsp" %>
<head>
    <meta charset="utf-8">
    <title>Chamados - SIS CENTRAL REL</title>
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/chamados.css">
    <link rel="stylesheet" href="https://opensource.keycdn.com/fontawesome/4.7.0/font-awesome.min.css ">
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


        <!-- Table -->

        <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
        <div class="container">                        <br>

            <div  class="panel panel-default">


                <!-- Default panel contents -->
                <div class="panel-heading" style="text-align: center;"><strong>Relatorio por Usuário</div>
                <div class="panel-heading" style="text-align: center;"><strong>${param.nome}</div>
                <br><div  style="text-align: right ;margin-right: 10px;" class="panel-default"> 



                    <form method="POST"  action="ExcelServlet"> <input type="submit" class="btn  btn-success  " value="Exportar" />
                        <input type="hidden" id="relatorio" name="relatorio" value="usuario">
                        <table class="table">


                            <c:set var="nome" value='${requestScope["nome"]}'/>
                            <input type="hidden" name="usuario" id="usuario"  value="<c:out value='${param.nome}'/>"/>


                            <thead class=" btn-toolbar "> 
                                <tr> 
                                    <th >Lista</th>
                                    <th>PID</th>
                                    <th><label >N° Chamado</label></th>
                                    <th><label >Data de abertura</label> </th>
                                    <th> <label >Duração</label></th>
                                    <th><label >Observação</label></th>
                                </tr> 

                            </thead><tbody>


                                <c:forEach items="${sq.RelatorioUsuario(param.nome)}" var="consulta">
                                    <tr>  
                                        <td class="active">   
                                            <a  target="_blank"  href="chamados_respostas.jsp?servico=<c:out value='${consulta.getLista() }'/>"><c:out value='${consulta.getLista() }'/></a>


                                            <input type="hidden" value='<c:out value="${consulta.getLista() }"/>' name="lista" id="lista">
                                        </td>
                                        <td>    <input type="hidden" value='<c:out value="${consulta.getPID() }"/>' name="pid" id="pid">  
                                            <a  target="_blank"  href="relatorio_pid.jsp?pid=<c:out value='${consulta.getPID() }'/>"><c:out value='${consulta.getPID() }'/></a>

                                        </td>
                                        <td class="active">   
                                            <input type="hidden" value='<c:out value="${consulta.getChamado() }"/>' name="chamado" id="chamado">
                                            <c:out value="${consulta.getChamado() }"/>
                                        </td>
                                        <td  >   
                                            <input type="hidden" value='<c:out value="${consulta.getDataAbertura() }"/>' name="data" id="data">
                                            <c:out value="${consulta.getDataAbertura() }"/>
                                        </td>
                                        <td class="active">   
                                            <input type="hidden" value='<c:out value="${consulta.getDuracao() }"/>' name="duracao" id="duracao">
                                            <c:out value="${consulta.getDuracao() }"/>
                                        </td>
                                        <td >  <input type="hidden" value=' <c:out value="${consulta.getObs() }"/>' name="obs" id="obs"> 
                                            <c:out value="${consulta.getObs() }"/>
                                        </td>




                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table></form>
                </div>
            </div>
        </div>



        <%@include file="footer.html" %>



    </section>




</body>



<%}%>
</html>