

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

                <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>


                <div  class="container">

                    <center><div class="alert alert-success" id="sucess" hidden="true" role="alert"></center>
                    <div  class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading" style="text-align: center;"><strong>Relatorio por PID</div>


                        <c:forEach items="${sq.getRelatorioPid(param.pid)}" var="consulta"> 

                            <li  id="painel" class="list-group-item" style="border-width: 0px 0;">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-6"><div >
                                                <div  class="panel panel-default">
                                                    <!-- Default panel contents -->
                                                    <div class="panel-heading" style="text-align: center;"><strong>Endereço Atual</div>
                                                    <label for="nome">PID:&nbsp;</label><c:out value="${consulta.getCodPid()}"/>

                                                    <div  >
                                                        <label for="nome"> Descrição:&nbsp;</label><c:out value="${consulta.getNomeEstabelecimento()}"/><br>



                                                        <label for="nome">Endereço:&nbsp;</label><c:out value="${consulta.getDescricao()}"/><br>

                                                        <label for="nome">Numero:&nbsp;</label><c:out value="${consulta.getNumero()}"/><br>
                                                        <label for="nome">Complemento:&nbsp;</label><c:out value="${consulta.getComplemento()}"/><br>
                                                        <label for="nome">Bairro:&nbsp;</label><c:out value="${consulta.getBairro()}"/><br>                                      
                                                        <label for="nome">Municipio:&nbsp;</label><c:out value="${consulta.getNomeMunicipio()}"/><br>
                                                        <label for="nome">UF:&nbsp;</label><c:out value="${consulta.getUf()}"/><br>





                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">   
                                        <c:forEach items="${sq.getRelatorioEnderecoNovo(param.pid)}" var="consulta"> 


                                            <div class="container">
                                                <div class="row">

                                                    <div  class="panel panel-default" style="width: 500px;">
                                                        <!-- Default panel contents -->
                                                        <div class="panel-heading" style="text-align: center; "><strong>Endereço Novo</div>
                                                        <label for="nome">PID:&nbsp;</label><c:out value="${consulta.getCodPid()}"/>

                                                        <div  >
                                                            <label for="nome"> Descrição:&nbsp;</label><c:out value="${consulta.getNomeEstabelecimento()}"/><br>



                                                            <label for="nome">Endereço:&nbsp;</label><c:out value="${consulta.getDescricao()}"/><br>

                                                            <label for="nome">Numero:&nbsp;</label><c:out value="${consulta.getNumero()}"/><br>
                                                            <label for="nome">Complemento:&nbsp;</label><c:out value="${consulta.getComplemento()}"/><br>
                                                            <label for="nome">Bairro:&nbsp;</label><c:out value="${consulta.getBairro()}"/><br>                                      
                                                            <label for="nome">Municipio:&nbsp;</label><c:out value="${consulta.getNomeMunicipio()}"/><br>
                                                            <label for="nome">UF:&nbsp;</label><c:out value="${consulta.getUf()}"/><br>


                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                        </c:forEach>



                                    </div>
                                </div>
                            </div>
                        </li>

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

                                <c:forEach items="${sq.getContatosRelatorio(param.pid)}" var="contato">





                                    <tr id="tr<c:out value='${contato.getId()}'/>">

                                        <td>
                                            <c:out value='${contato.getNome()}'/> 
                                            <input type="hidden" name="contato" id="contato"  value='<c:out value="${contato.getNome()}"/>' >
                                        </td>  <td>
                                            <c:out value="${contato.getEmail()}"/> 

                                        </td>

                                        <td>  

                                            <c:out value="${contato.getDdd()}"/>
                                        </td>

                                        <td>    <c:out value="${contato.getTelefone()}"/>  
                                        </td>   
                                        <td id="<c:out value='${contato.getId()}'/>">
                                            <input type="hidden" name="situacao" id="situacao<c:out value='${contato.getId()}'/>"  value='<c:out value="${contato.getSituacao()}"/>' >
                                            <c:out value="${contato.getSituacao()}"/>
                                        </td> 
                                    </tr>





                                </c:forEach>

                            </tbody>
                        </table>  <table class="table"> <thead> 
                                <tr style="background-color: #e2e1e1;"> 
                                    <th>Lista</th> 
                                    <th>Usuario</th> 
                                    <th>duracao</th> 
                                    <th>dt abertura </th> 
                                    <th>obs</th> 


                                </tr>  
                            </thead>     <tbody>
                                <c:forEach items="${sq.selectChamadoPorPid(param.pid)}" var="contato">
                                    <tr> <td> <a href=chamados_respostas.jsp?servico=<c:out value='${contato.getServico()}'/>"><c:out value='${contato.getServico()}'/></a>
                                           

                                        </td>  



                                        <td>   <a href="relatorio_usuario.jsp?nome=<c:out value='${contato.getUsuario()}'/> "> <c:out value="${contato.getUsuario()}"/> </a>
                                        </td>   
                                        <td>   <c:out value="${contato.getDuracao()}"/> 
                                        </td>    
                                        <td>  
                                            <c:out value="${contato.getDtAbertura()}"/> 
                                        </td> 
                                        <td>  
                                            <c:out value="${contato.getObs()}"/> 
                                        </td> 
                                    </tr>
                                </c:forEach></tbody>
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