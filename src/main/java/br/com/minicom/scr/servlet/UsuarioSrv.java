/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Perguntas;
import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.entity.Usuario;
import br.com.minicom.scr.excell.ExcellHandler;
import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.query.Queries;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import javax.websocket.Session;
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
@WebServlet(name = "UsuarioSrv", urlPatterns = {"/UsuarioSrv"})
public class UsuarioSrv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession ses = request.getSession(false);
        boolean isMultiPart = FileUpload.isMultipartContent(request);
        if (isMultiPart) {
            adicionaUsuario(request, response);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("usuarios.jsp");
            dispatcher.forward(request, response);
        } else {
            ses.setAttribute("idnovo", request.getParameter("id"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("addusuarios.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void adicionaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("it is!");
        
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        HttpServletRequest reqt = (HttpServletRequest) request;
        Usuario usuario = new Usuario();
        HttpSession ses = reqt.getSession(false);
        System.out.println("ID DA SESSAO: "+ses.getAttribute("idnovo"));
        String id = (String) ses.getAttribute("idnovo");
        if (id!=null) {
        usuario.setIdUsuario((int) ses.getAttribute("id"));
            System.out.println("Entrou no if :"+usuario.getCell(0).getValue() );
        }
        Queries equery = new SimpleQueries();

        try {
            int i = 0;

            List items = upload.parseRequest(request);

            Iterator iter = items.iterator();
            if (items.isEmpty()) {
                System.out.println("Vazio");
            }

            while (iter.hasNext()) {
                i++;
                System.out.println("iterador: " + i);
                FileItem item = (FileItem) iter.next();

                if (item.getFieldName().equals("nome")) {
                    usuario.setNome(item.getString());
                }

                if (item.getFieldName().equals("login")) {
                    usuario.setLogin(item.getString());
                }

                if (item.getFieldName().equals("senha")) {
                    usuario.setSenha(item.getString());
                }
                if (item.getFieldName().contains("perfil")) {
                    usuario.setCodPerfil(Integer.parseInt(item.getString()));
                }

            }
            System.err.println(usuario.toString());
            id = (int) usuario.getCell(0).getValue();
            if (usuario.getCell(0).getValue()) {

                equery.insert(usuario);
            } else {
                equery.update(usuario);
            }

            equery.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

            dispatcher.forward(request, response);
            return;
        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println("Erro na Solicitação. Tente de novo ou entre em contato do Administrador do Banco de Dados");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
        dispatcher.forward(request, response);

    }

}
