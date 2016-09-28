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
    String login = request.getParameter("login");
    String pwd = request.getParameter("senha");
    SimpleQueries simpleQueries = new SimpleQueries();
    Usuario usuario = new Usuario();
    int usuarioid = simpleQueries.select(login, pwd, usuario);
    System.out.print("usuarioid: " + usuarioid);

    if (usuario.autenticar(login, pwd)) {

        if (usuario.autenticarPerfil(login, pwd).equals("atendente")) {
           
            session.setAttribute("login", login);
            session.setAttribute("senha", pwd);
            session.setAttribute("usuarioid", usuarioid);
        
           
        }
        else if (usuario.autenticarPerfil(login, pwd).equals("gerente")) {
          
            session.setAttribute("login", login);
            session.setAttribute("senha", pwd);
            session.setAttribute("usuarioid", usuarioid);
           
        }
       else if (usuario.autenticarPerfil(login, pwd).equals("administrador")) {
    
            session.setAttribute("login", login);
            session.setAttribute("senha", pwd);
            session.setAttribute("usuarioid", usuarioid);
        
        }    response.sendRedirect("index.jsp");
    } else {

        out.println("Senha e/ou Usuário incorreto! <a href='login.jsp'>Tente Novamente</a>");
    }
%>
