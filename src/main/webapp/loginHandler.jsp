

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

    if (simpleQueries.autenticar(login, pwd)) {

        session.setAttribute("login", login);
        session.setAttribute("senha", pwd);
        session.setAttribute("usuarioid", usuarioid);

        session.setMaxInactiveInterval(7200);
        response.sendRedirect("index.jsp");
    } else {
        out.println("Senha e/ou Usuário incorreto! <a href='login.jsp'>Tente Novamente</a>");

    }
%>
