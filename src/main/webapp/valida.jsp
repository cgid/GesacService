
<%

    if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
%>
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
Você não está logado no sistema<br/>
<a href="login.jsp">Por Favor, Entre com o seu Login clicando aqui!</a>
<%} else {
    String login = String.valueOf(session.getAttribute("login"));
    String pwd = String.valueOf(session.getAttribute("senha"));
    SimpleQueries sq1 = new SimpleQueries();

    String perfil = sq1.autenticarPerfil(login, pwd);
    
%>