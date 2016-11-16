package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Contato;
import br.com.minicom.scr.entity.Endereco_novo;
import br.com.minicom.scr.entity.Telefone;
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
public class ContatoActionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Contato contato;

    public ContatoActionServlet() {
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

        try {
            String[] contatonovo = request.getParameterValues("contatonovo");

            String[] telefonenovo = request.getParameterValues("telefonenovo");
            String[] emailnovo = request.getParameterValues("emailnovo");

            if (null != telefonenovo) {
                for (int i = 0; i < telefonenovo.length; i++) {

                    if (10 <= telefonenovo[i].length()) {
                        contato = new Contato();
                      
                        
                        contato.setCodPid(Integer.parseInt(request.getParameter("pid2").trim()));
                        contato.setNome(contatonovo[i]);
                        if (null != emailnovo[i]) {
                            contato.setEmail(emailnovo[i]);
                        }

                        contato.setSituacao(1);
                        System.out.println("CONTATO NOVO: " + contato);
                        contato.setIdUsuario((int) ses.getAttribute("usuarioid"));
                        equery.insert(contato);
                        System.out.println(contato.toString());
                        Telefone telefone = new Telefone();
                        String tel[] = telefonenovo[i].replace(")", ",").split(",");
                        int ddd = Integer.parseInt(tel[0].replace("(", "").trim());
                        int t = Integer.parseInt(tel[1].replace("-", "").trim());

                        telefone.setDdd(ddd);

//                       
                        telefone.setTelefone(t);
                        telefone.setIdContato(equery.select(contato));
                        telefone.setSituacao(1);
                        System.out.println(telefone.toString());
                        equery.insert(telefone);
                        equery.close();
                         response.getWriter().write("contato(s) novo(s)  inserido!!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
                    }
                }
            }
            equery.close();
        } catch (NotIsInsertableEntityException ex) {
            equery.close();
            Logger.getLogger(ContatoActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("contato(s) novo(s) não inserido!!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

        } catch (SQLException ex) {
            equery.close();
            Logger.getLogger(ContatoActionServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("contato(s) novo(s) não inserido!!");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
