
<%@page import="br.com.minicom.scr.persistence.query.SimpleQueries"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="br.com.minicom.scr.entity.Chamado"%>
<%  Chamado c = new Chamado();
    c.setIdSolicitacao(Integer.parseInt(String.valueOf(request.getParameter("iSolicitacao"))));
    c.setCodUsuario(Integer.parseInt(String.valueOf(session.getAttribute("usuarioid"))));
    c.setChamadoAberto(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    SimpleQueries simpleQueries = new SimpleQueries();
    simpleQueries.insert(c);
    response.getWriter().write("endereço novo não inserido!!");
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

%>