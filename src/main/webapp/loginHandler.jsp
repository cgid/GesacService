<%-- 
    Document   : login
    Created on : 30/05/2016, 15:55:36
    Author     : VictorHugo
--%>

<%@page import="br.com.minicom.scr.persistence.query.Queries"%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%@page import="br.com.minicom.scr.entity.Usuario"%>
<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("login");
    String pwd = request.getParameter("senha");
    SimpleQueries simpleQueries = new SimpleQueries();
    Usuario usuario = new Usuario();
    int id = simpleQueries.select(userid, pwd, usuario);
    System.out.print("id: " + id);

    if (usuario.autenticar(userid, pwd)) {

        if (usuario.autenticarPerfil(userid, pwd).equals("atendente")) {
           
            session.setAttribute("userid", userid);
            session.setAttribute("senha", pwd);
            session.setAttribute("id", id);
        
           
        }
        else if (usuario.autenticarPerfil(userid, pwd).equals("gerente")) {
          
            session.setAttribute("userid", userid);
            session.setAttribute("senha", pwd);
            session.setAttribute("id", id);
           
        }
       else if (usuario.autenticarPerfil(userid, pwd).equals("administrador")) {
    
            session.setAttribute("userid", userid);
            session.setAttribute("senha", pwd);
            session.setAttribute("id", id);
        
        }    response.sendRedirect("index.jsp");
    } else {

        out.println("Senha e/ou Usuário incorreto! <a href='login.jsp'>Tente Novamente</a>");
    }
%>
