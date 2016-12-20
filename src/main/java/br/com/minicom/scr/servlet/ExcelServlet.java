/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.excell.ExcellHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edilson Jr
 */
public class ExcelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //To change body of generated methods, choose Tools | Templates.
        String[] listaStrings = req.getParameterValues("lista");
        String[] pidStrings = req.getParameterValues("pid");
        String[] chamadosStrings = req.getParameterValues("chamado");
        String[] dataStrings = req.getParameterValues("data");
        String[] duracaoStrings = req.getParameterValues("duracao");
        String[] obsStrings = req.getParameterValues("obs");

        String path = ExcellHandler.writeSheet((String) req.getParameter("usuario"), listaStrings, pidStrings, chamadosStrings, dataStrings, duracaoStrings, obsStrings);
        if (!path.contains("erro")) {

            File arquivo = new File(path);
            int tamanho = (int) arquivo.length();

            resp.setContentType("sheet/xls"); // tipo do conteúdo na resposta
            resp.setContentLength(tamanho); // opcional. ajuda na barra de progresso
            resp.setHeader("Content-Disposition", "attachment; filename=" + req.getParameter("usuario") + ".xls");

            OutputStream output = resp.getOutputStream();
            Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
        } else {

            resp.sendRedirect("erro.jsp");
        }
    }

}
