package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Endereco_novo;
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
public class ActionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ActionServlet() {
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

       
        Endereco_novo en = new Endereco_novo();
        en.setCodPid(Integer.parseInt(request.getParameter("pid").trim()));
        en.setDescricao(request.getParameter("descricao"));
        en.setNumero(request.getParameter("numero"));
        en.setComplemento(request.getParameter("complemento"));
        en.setCep(request.getParameter("cep"));
        en.setBairro(request.getParameter("bairro"));
        en.setCodIBGE(Integer.parseInt(request.getParameter("ibge")));
        en.setIdUsuario((int) ses.getAttribute("usuarioid"));

        try {
            equery.insert(en);
            equery.close();
            response.getWriter().write("endereço novo inserido com sucesso!!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            
        } catch (NotIsInsertableEntityException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("endereço novo não inserido!!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            
        } catch (SQLException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("endereço novo não inserido!!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
