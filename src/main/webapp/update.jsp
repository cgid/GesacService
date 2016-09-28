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
            UPDATE Usuario
            SET nome  = ?, login = ?, senha =?, Perfil_cod_perfil = ?   

            WHERE id_usuario = '${param.id}'
            <sql:param value="${param.nome}"/>
            <sql:param value="${param.login}"/>
            <sql:param value="${param.senha}"/>
            <sql:param value="${param.perfil}"/>
        </sql:update  >
            

        <c:if test="${result>=1}">
            
            <font size="5" color='green'> Congratulations ! Data updated
            successfully.</font>
            <a href="usuarios.jsp">Voltar </a>          
        </c:if>
    </body>
</html>