package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Telefone;
import br.com.minicom.scr.entity.Log_contato;
import br.com.minicom.scr.entity.exceptions.NotIsInsertableEntityException;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ActionServlet
 */
public class InvalidaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public InvalidaServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Usada na pagina de chamados para adicionar novo endereço.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession();

        SimpleQueries equery = new SimpleQueries();

        Log_contato log = new Log_contato();
        String idContato = invalidaTelefone(Integer.parseInt(request.getParameter("id")));
        if (!idContato.equals("nao inserido")) {
            log.setIdContato(Integer.parseInt(idContato));
            log.setIdUsuario(Integer.parseInt(String.valueOf(ses.getAttribute("usuarioid"))));
            log.setOperacao("invalidou");
            try {
                equery.insert(log);
            } catch (NotIsInsertableEntityException ex) {
                response.getWriter().write(" não inserido!!");
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                Logger.getLogger(InvalidaServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                response.getWriter().write(" não inserido!!");
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                Logger.getLogger(InvalidaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.getWriter().write("inserido");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
        } else {
            response.getWriter().write(" não inserido!!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

    public String invalidaTelefone(int id) {

        Telefone telefone = new Telefone();

        SimpleQueries equery = new SimpleQueries();
        try {
            Telefone c = (Telefone) equery.select(telefone, id);

            c.setSituacao(0);
            equery.update(c);
            return (String) c.getCell(3).getValue();
        } catch (SQLException ex) {
            Logger.getLogger(InvalidaServlet.class.getName()).log(Level.SEVERE, null, ex);
            return " nao inserido";
        }

    }

}
