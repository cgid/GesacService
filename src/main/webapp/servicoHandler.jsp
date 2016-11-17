
<%@page import="br.com.minicom.scr.persistence.ConnectionFactory"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    Connection conn;
    Statement stmt = null;
    Statement stmt2 = null;
    conn = ConnectionFactory.getConnection();
    ResultSet rs = null;
    ResultSet rs2 = null;
    String sql;
    sql = "  SELECT  `observacao`,"
            + "`dt_chamado_aberto`"
            + "FROM `chamado` INNER JOIN solicitacoes on (chamado.Solicitacoes_id_solicitacao = solicitacoes.id_solicitacao) WHERE solicitacoes.id_solicitacao = " + request.getParameter("iSolicitacao")
            + " ORDER by chamado.dt_chamado_aberto ";
    stmt = conn.createStatement();
    rs = stmt.executeQuery(sql);
    System.err.print(sql);
    String obs = "";
    while (rs.next()) {
        if (!rs.getString(1).equals("null")) {
            System.err.print("gets 1  " + rs.getString(1));
            System.err.print("observacao" + obs);
            obs = rs.getString(1);
            System.err.print("observacao nova " + obs);

        }
    }
    response.getWriter().write(obs);
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    simpleQueries.close();
    conn.close();
    rs.close();
    stmt.close();

%>