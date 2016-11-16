<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:useBean id="conn" class="br.com.minicom.scr.persistence.ConnectionFactory"/>
    <body><sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://localhost/SisCentralRel?autoReconnect=true&useSSL=false"
                       user="root"
                       password="root"/>
        <sql:update var="result" dataSource="${dbsource}">
            INSERT INTO endereco_novo( descricao, numero, bairro, cep, complemento, Municipio_cod_IBGE, PID_cod_pid, valido) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9])


            WHERE id_usuario = '${param.id}'

            <sql:param value="${param.nome}"/>
            <sql:param value="${param.login}"/>
            <sql:param value="${param.senha}"/>
            <sql:param value="${param.perfil}"/>
            <sql:param value="${param.ativo}"/>
        </sql:update  >


        <c:if test="${result>=1}">

            <font size="5" color='green'> Congratulations ! Data updated
            successfully.</font>
            <a href="index.jsp">Voltar </a>          
        </c:if>
    </body>
</html>