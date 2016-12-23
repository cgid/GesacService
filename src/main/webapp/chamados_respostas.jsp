

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









            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading" style="text-align: center;"><strong>USUÁRIOS</strong></div>

                <form method="POST"  action="ExcelServlet"> <input style="margin-left: 100px;" type="submit" class="btn  btn-success  " value="Exportar" />
                    <table class="table-hover table-responsive table-bordered" >
                        <input type="hidden" id="relatorio" name="relatorio" value="servico">
                        <input type="hidden" id="lista" name="lista" value="<c:out value='${param.servico}'/>">
                        <input type="hidden" id="titulo" name="titulo" value="PID">
                        <input type="hidden" id="titulo" name="titulo" value="Estabelecimento">
                        <input type="hidden" id="titulo" name="titulo" value="Obs">
                        <thead class="btn-toolbar" >
                        <th style="margin-left: 100px;width: 100px"><label style="margin-left: 20px;">PID</label></th>
                        <th style="margin-left: 100px;width: 100px"><label style="margin-left: 20px;">Estabelecimento</label></th>
                        <th style="margin-left: 100px;width: 100px"><label style="margin-left: 20px;">Obs</label></th>

                        <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                        <c:forEach items="${sq.getTitulosList(param.servico)}" var="contato">
                            <input type="hidden" id="titulo" name="titulo" value="<c:out value='${contato.toString()}'/>">
                            <th style="margin-left: 100px;width: 100px"><label style="margin-left: 10px;width: 100px"><c:out value="${contato.toString()}"/></label></th>
                            </c:forEach>

                        </thead>


                        <tbody >
                            <c:set var="i" value="0"/>
                            <c:forEach items="${sq.ChamadosERespostas(param.servico)}" var="contato">

                                <tr >




                                    <td style="width: 50px;">
                                        <input type="hidden" id="chamado" name="chamado" value="<c:out value='${contato.getChamado() }'/>">
                                        <input type="hidden" id="pid" name="pid" value="<c:out value='${contato.getPid() }'/>">
                                        <a  target="_blank"  style="margin-left: 20px;width: 100px" href="relatorio_pid.jsp?pid=<c:out value='${contato.getPid() }'/>"><c:out value='${contato.getPid() }'/></a>
                                    </td>
                                    <td style="width: 50px;"> <input type="hidden" id="Estabelecimento" name="Estabelecimento" value="<c:out value='${contato.getEstabelecimento() }'/>">
                                        <p style="margin-left: 20px;width: 100px"> <c:out value="${contato.getEstabelecimento() }"/></p>
                                    </td>
                                    <td style=" margin-left: 10px;width: 50px;"><input type="hidden" id="Obs" name="Obs" value="<c:out value='${contato.getObs() }'/>">
                                        <p style="margin-left: 20px;width: 100px"> <c:out value="${contato.getObs() }"/></p>
                                    </td>
                                    <c:forEach items="${contato.getRepostas()}" var="respostas">

                                        <td style="margin-left: 120px;width: 110px"> <input type="hidden" id="respostas<c:out value='${i }'/>" name="respostas<c:out value='${i }'/>" value="<c:out value='${respostas.toString() }'/>">
                                            <p style="margin-right:  20px;width: 110px"><c:out value="${respostas }"/></p>
                                        </td>
                                    </c:forEach>



                                </tr> 

                                <c:set var="i" value="${i+1}"/>
                            </c:forEach>

                        </tbody>
                    </table>


                </form>
            </div>








            <%@include file="footer.html" %>
        </section>




    </body>
    <% }%>
</html>