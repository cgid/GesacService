<%-- 
    Document   : Texte
    Created on : 22/08/2016, 09:30:28
    Author     : Edilson Jr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="servicoSrv"  enctype="multipart/form-data">

            <div class="form-group">
                <label for="nome">Adicionar lista de PIDS</label>
                <input name= "planilha" type="file" accept=".xls,.xlsx"><br>
                </li>


                <div class="row text-right">

                    <td><button type="submit" id="upload" name="upload"  class="btn btn-primary text-center">Adicionar serviÃ§o</button></td>

                </div>
        </form>


    </body>
</html>
