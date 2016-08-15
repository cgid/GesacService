<%-- 
    Document   : login
    Created on : 30/05/2016, 15:55:36
    Author     : VictorHugo
--%>

<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("login");
    String pwd = request.getParameter("senha");

    Usuario usuario = new Usuario();
    if (usuario.autenticar(userid, pwd)) {
        session.setAttribute("userid", userid);
        response.sendRedirect("atendente_index.jsp");
    } else {

        out.println("Senha e/ou Usuário incorreto! <a href='login.html'>Tente Novamente</a>");
    }
%>
