package br.com.minicom.scr.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.minicom.scr.entity.PID;
import br.com.minicom.scr.entity.Perguntas;
import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.entity.Usuario;
import br.com.minicom.scr.excell.ExcellHandler;
import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import br.com.minicom.scr.persistence.query.eQuery;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Edilson Jr
 */
@WebServlet(urlPatterns = {"/servicoSrv"}, name = "servicoSrv")
public class ServicoSrv_1 extends HttpServlet {

    //Initialize global variables
    public void init() throws ServletException {

    }

    //Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

//Process the HTTP Get request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isMultiPart = FileUpload.isMultipartContent(request);

        if (isMultiPart) {
            System.out.println("it is!");
            FileItemFactory factory = new DiskFileItemFactory();
            Servico servico = new Servico();
            ServletFileUpload upload = new ServletFileUpload(factory);
            Entity e = null;
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            Usuario u = new Usuario();
            String usuario;
            HttpSession ses = reqt.getSession(false);


                usuario = String.valueOf(ses.getAttribute("userid"));
            e = eQuery.select(u, usuario);
            e.toString();
            int id = Integer.parseInt(String.valueOf(e.getCell(0).getValue()));

             try {
                int i = 0;

                List items = upload.parseRequest(request);

                Iterator iter = items.iterator();
                if (items.isEmpty()) {
                    System.out.println("TA VAZII");
                }

                while (iter.hasNext()) {
                    i++;
                    System.out.println(i);
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {

                        if (item.getName().length() > 0) {
                            if (item.getFieldName().equals("planilha")) {/* Verifica se item é igual a imagem   se for pega a imagem e o usuario logado.                    
                                 */


                                ExcellHandler.readSheet(servico, item);
                            }
                        }
                    }

                    if (item.getFieldName().equals("descricao")) {
                        servico.setDescricao(item.getString());
                    }
                    if (item.getFieldName().equals("intervalo")) {
                        servico.setIntervaloLigacoes(Integer.parseInt(item.getString()));
                        servico.setCodUsuario(id);

                        servico.setCriacaoServico(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        servico.setDtEncerramento(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        System.out.println(String.valueOf(servico.getCell(3).getValue()));
                        System.out.println(servico.toString());
                        eQuery.insert.execute(servico);
                    }

                    if (item.getFieldName().contains("pergunta")) {
                        Perguntas perguntas = new Perguntas();
                        perguntas.setCodServico(eQuery.selectID.execute(servico));
                        perguntas.setPergunta(item.getString());
                        System.out.println(perguntas);
                        eQuery.insert.execute(perguntas);

                    }

                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login1.jsp");

                dispatcher.forward(request, response);
                return;
            } catch (FileUploadException ex) {

                System.out.println("Não foi possível fazer o Upload do arquivo! Tente Novamente");

            } catch (Exception ex) {

                ex.printStackTrace();
                System.out.println("Erro na Solicitação. Tente de novo ou entre em contato do Administrador do Banco de Dados");
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/atendente_index.jsp");
        dispatcher.forward(request, response);
    }

}
