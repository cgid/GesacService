/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.entity.Solicitacoes;
import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import br.com.minicom.scr.persistence.query.eQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Chamado", urlPatterns = {"/Chamado"})
public class Chamado extends HttpServlet {

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

        Solicitacoes s = new Solicitacoes();

//        List<Entity> solicitacoeses = eQuery.selectList.execute(s);
        boolean isMultiPart = FileUpload.isMultipartContent(request);
        if (isMultiPart) {

            FileItemFactory factory = new DiskFileItemFactory();
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            HttpSession ses = reqt.getSession(false);
            String usuario = (String) ses.getAttribute("userid");
            ServletFileUpload upload = new ServletFileUpload(factory);
            SimpleQueries queries = new SimpleQueries();
            String formulario = "";
            int id = Integer.parseInt(usuario);

            try {

                List items = upload.parseRequest(request);

                Iterator iter = items.iterator();

                while (iter.hasNext()) {

                    FileItem item = (FileItem) iter.next();

                    if (item.getFieldName().equals("contato")) {

//                        formulario = item.setString();

                    }

                    if (!item.isFormField()) {

                        if (item.getName().length() > 0) {

                            if (item.getFieldName().equals("imagem")) {/* Verifica se item é igual a imagem   se for pega a imagem e o usuario logado.                    
                                 */


//                                dao.atualizarFoto(usuario, item);
                            }

                        }

                    }/* Verifica se item é igual a manifestacao de interesse ou termo                     
                     */

                    if (item.getFieldName().contains("manifestacao") || item.getFieldName().contains("termo")) {
//                        manifestacoes = item.getString();
//                        HttpServletRequest reqt = (HttpServletRequest) request;
//
//                        HttpSession ses = reqt.getSession(false);
//                        String usuario = (String) ses.getAttribute("userid");
//                        String campo = item.getFieldName();
//
//                        dao.atualizaManifestacao(usuario, manifestacoes, campo);
                    }

                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrado.jsp");

                dispatcher.forward(request, response);
                return;
            } catch (FileUploadException ex) {

                System.out.println("Não foi possível fazer o Upload do arquivo! Tente Novamente");

            } catch (Exception ex) {

                ex.printStackTrace();
                System.out.println("Erro na Solicitação. Tente de novo ou entre em contato do Administrador do Banco de Dados");
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/errorpage.html");
        dispatcher.forward(request, response);
    }

}
