/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edilson Jr
 */
@WebServlet(name = "GerenciadorServico", urlPatterns = {"/GerenciadorServico"})
public class GerenciadorServico extends HttpServlet {

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest reqt = (HttpServletRequest) request;
        String op = reqt.getParameter("editarservico");
        SimpleQueries equery = new SimpleQueries();
        HttpSession ses = reqt.getSession(false);
        System.out.println("Operacao:" + op);
        int usuario, idServico;
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        Servico servico = null;
        if (request.getParameter("id") != null) {
            idServico = Integer.parseInt(request.getParameter("id"));
        } else {
            idServico = 0;
        }
        if (op == null) {
            System.out.println(op + 2);
            ses.setAttribute("idserv", "0");
            response.sendRedirect("addservico.jsp");
            
        } else {
            if (op.contains("editar")) {
                try {
                    servico = (Servico) equery.select(new Servico(), Integer.parseInt(reqt.getParameter("idservico")));
                } catch (SQLException ex) {
             
                    Logger.getLogger(GerenciadorServico.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(op + 1);
                equery.close();
                response.sendRedirect("addservico.jsp?idservico=" + reqt.getParameter("idservico") + "&intervalo=" + servico.getCell(4).getValue() + "&descricao=" + servico.getCell(2).getValue());
                
            }
            
            usuario = Integer.parseInt(String.valueOf(ses.getAttribute("usuarioid")));
            
            if (op.contains("encerrar")) {
                System.out.println(op + 13);
                try {
                    servico.setIdServico(idServico);
                    equery.encerraServico(servico);
                    equery.close();
                } catch (SQLException ex) {
                    equery.close();
                    Logger.getLogger(GerenciadorServico.class.getName()).log(Level.SEVERE, null, ex);
                    op = "nao foi encerrado tente novamente ";
                }
                
                op = "encerrado com sucesso!!";
                response.getWriter().write(op);;
            }
            
            if (op == null) {
                op = "nao foi possivel realizar essa operação!!";
            }
            equery.close();
            response.getWriter().write(op);;
            
        }
        
    }
    
}
