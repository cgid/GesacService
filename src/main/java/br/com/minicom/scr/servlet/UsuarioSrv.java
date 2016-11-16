/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Usuario;
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

/**
 *
 * @author Edilson Jr
 */
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpServletRequest reqt = (HttpServletRequest) request;
        String id = request.getParameter("id");
        String operacao = request.getParameter("operacao");
        if (request.getParameter("operacao") == null) {
            operacao = "";
        }

        System.err.println(operacao + request.getParameter("perfil") + request.getParameter("senha"));
        SimpleQueries sq = new SimpleQueries();
        if (id != null) {
            Usuario usuario;
            try {
                usuario = (Usuario) sq.select(new Usuario(), Integer.parseInt(id));
                System.out.println("DOGET1: " + usuario.toString());
                if (request.getParameter("perfil") != null) {
                    usuario.setCodPerfil(Integer.parseInt(request.getParameter("perfil")));

                }
                if (request.getParameter("ativo") != null) {
                    usuario.setAtivo(Integer.parseInt(request.getParameter("ativo")));
                }

                if (request.getParameter("senha") != null) {
                    usuario.setSenha(reqt.getParameter("senha"));

                }

                System.out.println("DOGET2: " + usuario.toString());
                sq.update(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioSrv.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            if (operacao.equals("desativar")) {
                sq.close();
                response.getWriter().write("Usuario desativado!");
            } else if (operacao.equals("ativar")) {

                response.getWriter().write("Usuario ativado!");
            } else {
                sq.close();
                response.getWriter().write("usuario alterado com sucesso!");
            }
        } else {
            Usuario usuario = new Usuario();

            usuario.setNome(reqt.getParameter("nome"));
            usuario.setLogin(reqt.getParameter("login"));
            usuario.setSenha(reqt.getParameter("senha"));
            usuario.setAtivo(1);
            System.out.println(usuario);
            usuario.setCodPerfil(Integer.parseInt(reqt.getParameter("perfil")));
            try {
                sq.insert(usuario);
                sq.close();
            } catch (NotIsInsertableEntityException ex) {
                Logger.getLogger(UsuarioSrv.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioSrv.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("inserido com sucesso!!");

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
