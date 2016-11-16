

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

        if (simpleQueries.autenticarPerfil(login, pwd).equals("atendente")) {

            session.setAttribute("login", login);
            session.setAttribute("senha", pwd);
            session.setAttribute("usuarioid", usuarioid);

        } else if (simpleQueries.autenticarPerfil(login, pwd).equals("gerente")) {

            session.setAttribute("login", login);
            session.setAttribute("senha", pwd);
            session.setAttribute("usuarioid", usuarioid);

        } else if (simpleQueries.autenticarPerfil(login, pwd).equals("administrador")) {

            session.setAttribute("login", login);
            session.setAttribute("senha", pwd);
            session.setAttribute("usuarioid", usuarioid);

        }
        session.setMaxInactiveInterval(7200);
        response.sendRedirect("index.jsp");
    } else {
        String servicoid = request.getParameter("servicoid");
        session.setAttribute("servicoid", servicoid);
        response.sendRedirect("addservico.jsp");

    }
%>
