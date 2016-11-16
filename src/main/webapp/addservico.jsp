




<html id="html" >
    <%@include file="valida.jsp" %>
    <head><base id="base">
        <meta charset="utf-8">
        <title>Chamados - SIS CENTRAL REL</title>
        <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/chamados.css">
    </head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="http://code.jquery.com/jquery-latest.js">
    </script>


    <script src="https://code.jquery.com/jquery-2.1.4.js"></script>



    <script>
        $(function () {
            var count = 0;
            $("#btn-add-input-text").click(function () {
                $("#fields").append($("<input/>").attr({
                    class: "form-control",
                    type: "text",
                    placeholder: "Digite a pergunta",
                    id: "pergunta",
                    name: "pergunta"
                }));
                $("#fields").append("<br/><br/>");
            });
        });
    </script>      
    <script src="lib/jquery/jquery.maskedinput.js">

        $(document).ready(function () {

            $(document.getElementById("intervaloligacoes")).mask("99");
        });
    </script>


    <script>
        $(document).ready(function () {

            var id = $('#id').val();
            if (id != 'null') {
                $('#titulo').text("EDITAR SERVIÇO");
                $('#submit').text("Aplicar");


            } else {
                $('#descricao').val("");
                $('#intervaloligacoes').val("");
                $('#id').val("0");
                $("#pergunta").attr({required: true});
                $("#planiha").attr({required: true});
            }

        });
    </script>          


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


            <div class="container">





                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div  id="titulo" class="panel-heading" style="text-align: center;"><strong>ADICIONAR SERVIÇOS</strong></div>

                    <form method="POST" action="servicoSrv" enctype="multipart/form-data">

                        <div class="form-group">

                            <input type="hidden" name="id" id="id" class="form-control" value="<% out.print(request.getParameter("idservico"));%>" >
                        </div>

                        <div class="form-group" style="margin-left: 10px; margin-right: 10px;">
                            <label for="nome">Descrição</label>
                            <input required="true" type="text" name="descricao" id="descricao" class="form-control" placeholder="Digite a descrição" value="<% out.print(request.getParameter("descricao"));%>">
                        </div>
                        
                        <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                        <jsp:useBean id="pergunta" class="br.com.minicom.scr.entity.Perguntas"/>




                        <c:forEach items="${sq.Perguntas(param.idservico)}" var="perguntas">
                            <div class="form-group" style="margin-left: 10px; margin-right: 10px;">

                                <input type="hidden" name="idp" id="idp" class="form-control" value="${perguntas.getCell(0).getValue()}" >
                                <label for="nome">Pergunta</label> 
                                <input type="text" name="pergunta" id="pergunta" class="form-control" value="<c:out value='${perguntas.getCell(1).getValue()}'/>" >


                            </div>

                        </c:forEach>
                        <div class="form-group">
                            <div class="panel panel-default" style="border-radius: 0px;">
                                <div class="panel-heading"><center><label for="nome" style="text-align: center;">PERGUNTAS</label></center></div><br>
                            <input  type="text" name="pergunta" id="pergunta" class="form-control" placeholder="Digite a pergunta" >
                            </div>
                        </div>





                        <div id="fields"></div>

                        <div class="row text-left">
                            <div>          
                                <button id="btn-add-input-text" type="button" class="btn btn-primary besquerda">Adicionar pergunta</button>
                            </div>
                        </div>	
                        <br>
                        
                        <div class="panel panel-default" style="border-radius: 0px;">
                            <div class="panel-heading"><center><label for="nome">Adicionar lista de PIDS</label></center></div><br><br>
                        <input  id="planiha" type="file" name="planilha" accept=".xls"><br>
                        </div>
                        
                       



                        <div class="row text-right">

                            <button type="submit" id="submit"class="btn btn-primary text-center">Adicionar serviços</button>

                        </div>



                    </form>



                </div>
            </div>
            <%@include file="footer.html" %>
        </section>


        <script type="text/javascript" src="lib/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>

    </body>



</html><%}%>

