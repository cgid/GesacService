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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
       

        <script src="https://code.jquery.com/jquery-2.1.4.js"></script>

        <script src="http://code.jquery.com/jquery-latest.js">
        </script>
        <script>
            $(document).ready(function () {
                var data = new Date();
                var ano = data.getFullYear();
                var mes = data.getMonth() + 1;
                var hora = data.getHours();
                var min = data.getMinutes();
                var dia = data.getDate();
                var sec = data.getSeconds();
                if (dia.toString().length == 1) {
                    dia = "0" + dia;
                }
                if (hora.toString().length == 1) {
                    hora = "0" + hora;
                }
                if (min.toString().length == 1) {
                    min = "0" + min;
                }
                if (mes.toString().length == 1) {
                    mes = "0" + mes;
                }
                if (sec.toString().length == 1) {
                    sec = "0" + sec;
                }
                var data2 = ano + '-' + mes + '-' + dia;
                var data3 = hora + ':' + min + ':' + sec;
                data = ano + '-' + mes + '-' + dia + ' ' + hora + ':' + min + ':' + sec;
//                yyyy-MM-dd HH:mm:ss
                $('#dt_chamado_aberto').val(data);
                var contador2 = 0;
                var contador = 0;
                var idS = $('#idSolicitacao').val();
                if (idS == null) {
                    $('#painel').hide();
                    $('#divcontador').hide();
                    $('#sucess').text("Lista concluída no momento.");
                    $('#sucess').show();
                }
                $('#botaoeditar').click(function (event) {
                    contador++;
                    var resultado = contador % 2;
                    if (resultado == 1) {
                        $('#editarendereco').show();
                        $('#divtabform').hide("now");
                    } else {
                        $('#divtabform').show();
                        $('#editarendereco').hide("now");
                    }
                });
                $("#realizado").click(function (event) {
                    var radio = $('#realizado').val();
                    $("#resposta").show();
                    $('#divdate').hide();
                    $('#divtime').hide();
                    $('#datepicker').hide();
                    $('#timepicker').hide();
                    $(document.getElementsByName('resposta')).attr({required: true});
                    $(document.getElementsByName('resposta')).attr({disabled: false});
                });
                $("#realizado2").click(function (event) {
                    var radio = $('#realizado2').val();
                    $('#divdate').show();
                    $('#divtime').show();
                    $('#datepicker').attr({required: true, min: data2.trim()});
//                    $('#timepicker').attr({required: true, min: data3.trim()});
                    $(document.getElementsByName('resposta')).attr({disabled: true});
                    document.getElementById("resposta").required = false;
                    $('#datepicker').show();
//          
                    $('#timepicker').show();
                });
                $('#addcontato').click(function (event) {
                    $("#addcontato").show();
                    var contatos = $(document.getElementsByName("contatonovo")).val();
                    var tels = $(document.getElementsByName("telefonenovo")).val();
                    var emails = $(document.getElementsByName("emailnovo")).val();
                    console.log(tels);
                    console.log(contatos);
                    console.log(emails);
                    var pid2 = $('#pid').val();
                    console.log(pid2);
                    $.get('ContatoActionServlet', {emailnovo: emails, telefonenovo: tels, pid2: pid2, contatonovo: contatos}, function (responseText) {
                        $("#fields").hide();
                        $("#fields").text("");
                        $('#sucess2').show().text(responseText);
                        $("#addcontato").hide();
                    });
                });
                $('#submit').click(function (event) {
                    var pid = $('#pid').val();
                    var ibge = $('#ibge1').val();
                    var descricao = $('#descricao_end').val();
                    var numero = $('#numero').val();
                    var bairro = $('#bairro').val();
                    var cep = $('#cep').val();
                    var complemento = $('#complemento').val();
                    $.get('ActionServlet', {pid: pid, descricao: descricao, cep: cep, ibge: ibge, complemento: complemento, numero: numero, bairro: bairro}, function (responseText) {
                        $('#sucess').show().text(responseText);
                        $('#divtabform').show();
                        $('#editarendereco').hide("now");
                    });
                });
            });
        </script>
        <script language=JavaScript>
            <!-- begin
            function getSecs(sHors, sMins, sSecs, campo) {
                sSecs++;
                if (sSecs == 60) {
                    sSecs = 0;
                    sMins++;
                    if (sMins <= 9)
                        sMins = "0" + sMins;
                }
                if (sMins == 60) {
                    sMins = "0" + 0;
                    sHors++;
                    if (sHors <= 9)
                        sHors = "0" + sHors;
                }
                if (sSecs <= 9)
                    sSecs = "0" + sSecs;
                document.getElementById(campo).innerHTML = sHors + "<font color=#000000>:</font>" + sMins + "<font color=#000000>:</font>" + sSecs;
                setTimeout("getSecs(" + sHors + ", " + sMins + "," + sSecs + ", '" + campo + "')", 1000);
                document.getElementById("duracao").value = sHors + ":" + sMins + ":" + sSecs;
            }
            //-->
        </SCRIPT>
        <script src = "lib/jquery/jquery.min.js"></script>

    </head>   


    <script type="text/javascript">
            function get(id) {
                return document.getElementById(id);
            }
            function mask_phone() {
                var n_char = get("telefonenovo").value.length;
                if (n_char == 2) {
                    get("telefonenovo").value = "(" + get("telefonenovo").value + ") ";
                } else if (n_char == 10) {
                    get("telefonenovo").value = get("telefonenovo").value + "-";
                }
            }
    </script>   


    <script>
        $(function () {
            var count = 0;
            $("#botaoadd").click(function () {
                $('#addcontato').attr({style: "display:inline"});
                $("#fields").show();
                $("#fields").append($("<input/>").attr({
                    class: "form-control",
                    type: "text",
                    style: "width:350; margin-left:10; text-transform:uppercase;",
                    placeholder: "Digite o contato",
                    id: "contatonovo",
                    name: "contatonovo"
                })
                        );
                $("#fields").append("<br/>");
                $("#fields").append($("<input  />").attr({
                    class: "form-control",
                    type: "text",
                    style: "width:350;  margin-left:10;",
                    maxlength: "15",
                    onkeyup: "mask_phone()",
                    placeholder: "DIGITE O TELEFONE",
                    id: "telefonenovo",
                    name: "telefonenovo"
                }));
                $("#fields").append("<br/>");
                $("#fields").append($("<input  />").attr({
                    class: "form-control",
                    type: "email",
                    style: "width:350;  margin-left:10;",
                    placeholder: "DIGITE O EMAIL",
                    id: "emailnovo",
                    name: "emailnovo"
                }));
                $("#fields").append("<br/><br/>");
                $("#fields").append("<br/><br/>");
            });
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


            <div id="painel2" class="container">




                <div  class="container">

                    <center><div class="alert alert-success" id="sucess" hidden="true" role="alert"></center>
                    <div  class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading" style="text-align: center;"><strong>LISTA <%out.print(request.getParameter("idchamado"));%></strong> - <%out.print(request.getParameter("descricao"));%></div>

                        <!--                                                 Table
                        
                        --><div id="divcontador" style="text-align: right; margin-top: 10px; margin-right: 10px;">                             &nbsp; &nbsp;Duração<br>
                            <font color="#FF0000" size="3" face="Arial Black"><span  id="clock1"></span>
                            <script>setTimeout("getSecs(0,0,-1, \"clock1\")", 1000);</script></font></div>
                        <ul class="list-group">

                            <li  id="painel" class="list-group-item" style="border-width: 0px 0;">


                                <jsp:useBean id="sq" class="br.com.minicom.scr.persistence.query.SimpleQueries"/>
                                <c:forEach items="${sq.getChamado(param.idchamado)}" var="consulta">
                                    <div >
                                        <label for="nome">PID:&nbsp;</label><c:out value="${consulta.getCodPid()}"/>
                                    </div>
                                    <div  >
                                        <label for="nome"> Descrição:&nbsp;</label><c:out value="${consulta.getNomeEstabelecimento()}"/><br>



                                        <label for="nome">Endereço:&nbsp;</label><c:out value="${consulta.getDescricao()}"/><br>

                                        <label for="nome">Numero:&nbsp;</label><c:out value="${consulta.getNumero()}"/><br>
                                        <label for="nome">Complemento:&nbsp;</label><c:out value="${consulta.getComplemento()}"/><br>
                                        <label for="nome">Bairro:&nbsp;</label><c:out value="${consulta.getBairro()}"/><br>                                      
                                        <label for="nome">Municipio:&nbsp;</label><c:out value="${consulta.getNomeMunicipio()}"/><br>
                                        <label for="nome">UF:&nbsp;</label><c:out value="${consulta.getUf()}"/><br>


                                        <button id="botaoeditar" type="button" class="ui-btn-inline btn btn-primary">Editar endereço </button>

                                    </div>
                                </li><div id="divtabform" >
                                    <form method="POST" action="ChamadoSrv" id="formchamado">
                                        <div class="form-group">
                                            <input type="hidden" name="idchamado" id="idchamado"  value="<%out.print(request.getParameter("idchamado"));%>" >
                                        </div>
                                        <div class="form-group">
                                            <input type="hidden" name="idSolicitacao" id="idSolicitacao"  value="<c:out value='${consulta.getId_solicitacao()}'/>" >
                                        </div>
                                        <div class="form-group">
                                            <input   type="hidden" name="pid" id="pid"  value=' <c:out value="${consulta.getCodPid()}"/>' >
                                        </div> <div class="form-group">
                                            <input type="hidden" name="dt_chamado_aberto" id="dt_chamado_aberto"   >
                                        </div>
                                        <div class="form-group">
                                            <input type="hidden" name="ibge1" id="ibge1"  value='<c:out value="${consulta.getIbge()}"/>' >
                                        </div>
                                        <div class="form-group">
                                            <input type="hidden" name="descricao" id="descricao"  value='<%out.print(request.getParameter("descricao"));%>' >
                                        </div>
                                        <div class="form-group">
                                            <input type="hidden" name="duracao" id="duracao"   >
                                        </div>





                                    </c:forEach>     <table class="table">

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
                                            <c:forEach items="${sq.getContatos(param.idchamado)}" var="contato">





                                                <tr id="tr<c:out value='${contato.getId()}'/>">

                                                    <td>
                                                        <c:out value="${contato.getNome()}"/> 
                                                        <input type="hidden" name="contato" id="contato"  value='<c:out value="${contato.getNome()}"/>' >
                                                    </td>  <td>
                                                        <c:out value="${contato.getEmail()}"/> 

                                                    </td>

                                                    <td>  
                                                        <input type="hidden" name="situacao" id="situacao<c:out value='${contato.getId()}'/>"  value='<c:out value="${contato.getSituacao()}"/>' >
                                                        <c:out value="${contato.getDdd()}"/>
                                                    </td>

                                                    <td>    <c:out value="${contato.getTelefone()}"/>  
                                                    </td>   
                                                    <td>
                                                        <input id="invalido" name="invalido" type="checkbox" value='<c:out value="${contato.getId()}"/>'>   
                                                    </td> 
                                                </tr>


                                            <script>
                                                $(document).ready(function () {
                                                    var situacao = $('#situacao<c:out value="${contato.getId()}"/>').val();
                                                    if (situacao == 0) {
                                                        $('#tr<c:out value="${contato.getId()}"/>').hide();
                                                    }
                                                });
                                            </script>


                                        </c:forEach>

                                        </tbody>
                                    </table>
                                    <input style="margin-left: 10px;" id="botaoadd" type="button" class="ui-btn-inline btn btn-primary" value="Adicionar contato">

                                    <div id="fields">
                                        <div>

                                        </div>
                                    </div>
                                    <button hidden="true" style="margin-left: 40px;margin-top:-10px; display: none" type="button"id="addcontato" class="btn btn-primary">Inserir contato</button>
                                    <center><div class="alert alert-success" id="sucess2" hidden="true" role="alert"></center>



                                    <div class="form-group" style="margin-left: 10px; margin-right: 10px;">
                                        <br><label for="nome">Observação: </label><br>
                                        <textarea class="form-control" required="required" name="observacao" id="observacao" placeholder="Observação"></textarea> 
                                    </div> 

                                    <c:forEach items="${sq.getPerguntas(param.idchamado)}" var="Perguntas">
                                        <div class="form-group" style="margin-left: 10px; margin-right: 10px;">
                                            <label for="nome"><c:out value="${Perguntas.getCell(1).getValue()}"/></label>
                                            <input class="form-control" type="text" name="resposta" id="resposta"  placeholder="Resposta">
                                        </div>

                                    </c:forEach>


                                    <div style="margin-left: 10px; margin-right: 10px;" id="divdate" hidden="true">Data:
                                        <input   hidden="true" type="date" id="datepicker" name="datepicker">
                                    </div>
                                    <div style="margin-left: 10px; margin-right: 10px;" id="divtime" hidden="true">Hora:
                                        <input  hidden="true" type="time"  id="timepicker"name="timepicker">
                                    </div>


                                    <div class="form-group" style="margin-left: 10px; margin-right: 10px;">
                                        <br><br><label for="nome">Atendimento concluído com sucesso?</label><br>
                                        <input required="true" type="radio" name="realizado" id="realizado" value="1">Sim &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="radio" name="realizado" id="realizado2" value="0">Não

                                    </div>

                                    <!-- conteudo -->



                                    <button type="submit" style="margin-left: 10px;" class="ui-btn-inline btn btn-primary">Finalizar chamado</button>
                                </form>

                            </div>
                            <div style="margin-left: 10px; margin-right: 10px;" hidden="true" id="editarendereco" class="ui-content" style="min-width:250px;">
                                <form id="form">
                                    <div class="form-group">
                                        <input type="hidden" name="pid1" id="pid1"  value=' <c:out value="${consulta.getCodPid()}"/>' >
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" name="ibge1" id="ibge1"  value='<c:out value="${consulta.getIbge()}"/>' >
                                    </div>
                                    <div class="form-group">
                                        <label for="nome">Endereço</label>
                                        <input type="text" name="descricao_end" id="descricao_end" class="form-control" placeholder="Descrição do endereço">
                                    </div>

                                    <div class="form-group">
                                        <label for="nome">Número</label>
                                        <input type="text" name="numero" id="numero" class="form-control" placeholder="Número do endereço">
                                    </div>

                                    <div class="form-group">
                                        <label for="nome">Bairro</label>
                                        <input type="text" name="bairro" id="bairro" class="form-control" placeholder="Digite o bairro">
                                    </div>

                                    <div class="form-group">
                                        <label for="nome">CEP</label>
                                        <input type="text" name="cep" id="cep" class="form-control" placeholder="Digite o CEP">
                                    </div>

                                    <div class="form-group">
                                        <label for="nome">Complemento</label>
                                        <input type="text" name="complemento" id="complemento" class="form-control" placeholder="Digite o complemento">
                                    </div>





                                    <div class="row text-left">
                                        <div>
                                            <button style="margin-left: 20px;" type="button"id="submit" class="btn btn-primary">Aplicar</button>
                                        </div>
                                    </div>	
                                    <script src="lib/jquery/jquery.maskedinput.js"></script>
                                    <script type="text/javascript">
                                                $(document).ready(function () {
                                                    $("#cep").mask("99.999-999");
                                                });
                                    </script>

                                </form>



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
