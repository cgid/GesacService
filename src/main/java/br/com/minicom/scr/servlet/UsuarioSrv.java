/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.entity.Usuario;
import br.com.minicom.scr.persistence.query.Queries;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.IOException;
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
       
        System.out.println("teste!");
  
            System.out.println("it is!");
            FileItemFactory factory = new DiskFileItemFactory();
            Servico servico = new Servico();
            ServletFileUpload upload = new ServletFileUpload(factory);

            HttpServletRequest reqt = (HttpServletRequest) request;

            Usuario usuario= new Usuario();
            HttpSession ses = reqt.getSession(false);
            Queries equery = new SimpleQueries();
            
            
            System.out.println("usuario ID: " + usuario);

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
                    if (item.getFieldName().equals("id")) {
                        usuario.setIdUsuario(Integer.parseInt(item.getString()));

                    }
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
              
                    equery.insert(usuario);

              
         
                
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

        doGet(request, response);
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

}
