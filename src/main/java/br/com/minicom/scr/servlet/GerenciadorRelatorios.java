/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edilson Jr
 */
@WebServlet(name = "GerenciadorRelatorios", urlPatterns = {"/GerenciadorRelatorios"})
public class GerenciadorRelatorios extends HttpServlet {

    private String pesquisa;
    private String where;

    //Initialize global variables
    public void init() throws ServletException {

    }

    //Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("radio").equals("servico")) {
            String nome = request.getParameter("where");
            request.setAttribute("servico", nome);

            RequestDispatcher dispatcher = request.getRequestDispatcher("chamados_respostas.jsp?servico="+nome);

            dispatcher.forward(request, response);
        }
        if (request.getParameter("radio").equals("usuario")) {
            String nome = request.getParameter("where");
            request.setAttribute("nome", nome);

            RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio_usuario.jsp?nome="+nome);

            dispatcher.forward(request, response);
        }

        if (request.getParameter("radio").equals("pid")) {
            where = request.getParameter("where");

            RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio_pid.jsp?pid=" + where);
            dispatcher.forward(request, response);
        }
    }
}
