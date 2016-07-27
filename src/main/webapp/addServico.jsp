<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form id="formImagem" name="formImagem" method="post" action="ServicoSrv" enctype="multipart/form-data">
            <input type="hidden" id="tipoForm" name="tipoForm" value="imagem">

            <fieldset>
                <legend>Serviço</legend>       
                <br/>
                <label>Descrição de Serviço</label>
                <br/>
                <label>1.1 - Defina a descrição do servico</label>
                <br/>
                <textarea id="servico" name="descricao_servico" rows="10" cols="155" required="true" style="background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>
                <label>Defina a primeria pergunta</label>
                <br/>
                <textarea id="1" name="pergunta1" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>
                <label>Defina a segunda pergunta</label>
                <br/>
                <textarea id="2" name="pergunta2" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/> <label>Defina a terceira pergunta</label>
                <br/>
                <textarea id="3" name="pergunta3" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/> <label>Defina a quarta pergunta</label>
                <br/>
                <textarea id="4" name="pergunta4" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>
                <label>Defina a ultima pergunta</label>
                <br/>
                <textarea id="5" name="pergunta5" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>
                   <fieldset>
                       <legend>Escolha o intervalo entre as ligaçoes, em horas </legend>
                       <input type="number" id="Intevalo_ligacoes" name="intervalo"> 
                   </fieldset>
                <fieldset>
                    <legend>Por Favor, anexe a planilha com os pontos Gesac que serão atendidas nesse servico</legend>
                    <input name="planilha" type="file" accept="image/jpeg; image/gif; image/bmp; image/png" id="imagem1" class="dados" maxlength="60" tabindex="1" value="c:/" position:absolute top:23px left:12px  width:500px/>
                    <br/>
                </fieldset>

                <br/>
                <br/>

                <input type="submit" value="Iniciar Servico" name="addServico" />
        </form>
    </body>
</html>
<a href='logout.jsp'><button>Sair</button></a>