<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<form method="POST" action="servicoSrv" enctype="multipart/form-data">
    <div class="form-group">
        <label for="id">Descrição</label>
        <input type="text" name="id" id="id" class="form-control" placeholder="${servico.getCell(0).getValue()}  Digite a descrição do serviço">
    </div>
    <div class="form-group">
        <label for="nome">Descrição</label>
        <input type="text" name="descricao" id="descricao" class="form-control" placeholder="${servico.getCell(0).getValue()}  Digite a descrição do serviço">
    </div>

    <div class="form-group">
        <label for="nome">Intervalo de ligaçoes</label>
        <input type="text" name="intervaloligacoes" id="intervaloligacoes" class="form-control" placeholder="Digite o intervalo das ligações">
    </div>

    <div class="form-group">
        <label for="nome">Pergunta</label>
        <input type="text" name="pergunta" id="pergunta" class="form-control" placeholder="Digite a pergunta">
    </div>

    <div class="form-group">
        <label for="nome">Pergunta</label>
        <input type="text" name="pergunta" id="pergunta" class="form-control" placeholder="Digite a pergunta">
    </div>


    <label for="nome">Mais Perguntas</label>
    <div id="fields"></div>

    <div class="row text-left">
        <div>          
            <button id="btn-add-input-text" type="button" class="btn btn-primary besquerda">Adicionar pergunta</button>
        </div>
    </div>	
    <br>
    <label for="nome">Adicionar lista de PIDS</label>
    <input type="file" name="planilha" accept=".xlsx"><br>
    </li>


    <div class="row text-right">

        <td><button type="submit" class="btn btn-primary text-center">Adicionar serviços</button></td>

    </div>
</form>