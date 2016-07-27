<%-- 
    Document   : success
    Created on : 30/05/2016, 15:56:02
    Author     : VictorHugo
--%>

 
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form id="formImagem" name="formImagem" method="post" action="servletupload" enctype="multipart/form-data">
            <input type="hidden" id="tipoForm" name="tipoForm" value="imagem">

            <fieldset>
                <legend>Serviço</legend>       
                <br/>
                <label>Descrição de Serviço</label>
                <br/>
                <label>1.1 - Defina a descrição do servico</label>
                <br/>
                <textarea id="descricao_servico" name="descricao_servico" rows="10" cols="155" required="true" style="background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>
                <label>Defina a primeria pergunta</label>
                <br/>
                <textarea id="pergunta1" name="pergunta1" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>
                 <label>Defina a segunda pergunta</label>
                <br/>
                <textarea id="pergunta2" name="pergunta2" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/> <label>Defina a terceira pergunta</label>
                <br/>
                <textarea id="pergunta3" name="pergunta3" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/> <label>Defina a quarta pergunta</label>
                <br/>
                <textarea id="pergunta4" name="pergunta4" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>
                 <label>Defina a ultima pergunta</label>
                <br/>
                <textarea id="pergunta5" name="pergunta5" rows="10" cols="155" required="true" style=" background-color: #F0F8FF; resize: none"></textarea>
                <br/>
                <br/>

            <fieldset>
                <legend>Por Favor, anexe a planilha com os pontos Gesac que serão atendidas nesse servico</legend>
                <input name="imagem" type="file" accept="image/jpeg; image/gif; image/bmp; image/png" id="imagem1" class="dados" maxlength="60" tabindex="1" value="c:/" position:absolute top:23px left:12px  width:500px/>
                <br/>
            </fieldset>

            <br/>
            <br/>

      
        </form>
    </body>
</html>
<a href='logout.jsp'><button>Sair</button></a>

