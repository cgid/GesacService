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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.write.WriteException;

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

        int i = 0;
        int ii = 0;
        if (req.getParameter("relatorio").contains("servico")) {
            List<String[]> rowList = new ArrayList<String[]>();
            String lista = req.getParameter("lista");
            String[] estabStrings = req.getParameterValues("Estabelecimento");
            String[] obsStrings = req.getParameterValues("Obs");
            String[] pidStrings = req.getParameterValues("pid");
            rowList.add(req.getParameterValues("titulo"));
            String[] chamadoStrings = req.getParameterValues("chamado");
           
            System.out.print(" TAMANHO DA LISTA de OBS" + obsStrings.length);

            for (String chamadoString : chamadoStrings) {
                String[] rowStrings = new String[200];

                rowStrings[ii] = pidStrings[i];
                ii++;
                rowStrings[ii] = estabStrings[i];
                ii++;
                rowStrings[ii] = obsStrings[i];
                ii++;
                String[] respostaStrings = req.getParameterValues("respostas" + i);
                System.out.print("respostas" + i);
                for (String respostaString : respostaStrings) {

                    System.out.print(" ii no for" + ii);
                    System.out.print(" TAMANHO DA LISTA de respostas " + respostaStrings.length);
                    System.out.print(" respostas " + respostaString);
                    rowStrings[ii] = respostaString;
                    ii++;

                }
                ii = 0;
                System.out.println("II fora " + ii);
                System.out.println("LNHA: " + i);
                rowList.add(rowStrings);

                i++;
            }
            String path;
            try {
                path = ExcellHandler.relatorioServico(rowList, lista);

                if (!path.contains("erro")) {

                    File arquivo = new File(path);
                    int tamanho = (int) arquivo.length();

                    resp.setContentType("sheet/xls"); // tipo do conteúdo na resposta
                    resp.setContentLength(tamanho); // opcional. ajuda na barra de progresso
                    resp.setHeader("Content-Disposition", "attachment; filename=relatorio da lista " + lista + ".xls");

                    OutputStream output = resp.getOutputStream();
                    Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
                } else {

                    resp.sendRedirect("erro.jsp");
                }

                System.out.println("TAMANHO DA LISTA" + rowList.get(1));
                System.out.println("TAMANHO DA LISTA" + rowList.get(1).toString());
                System.out.println("TAMANHO DA LISTA" + rowList.size());
            } catch (WriteException ex) {
                Logger.getLogger(ExcelServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (req.getParameter("relatorio").contains("usuario")) {

            String[] listaStrings = req.getParameterValues("lista");
            String[] pidStrings = req.getParameterValues("pid");
            String[] chamadosStrings = req.getParameterValues("chamado");
            String[] dataStrings = req.getParameterValues("data");
            String[] duracaoStrings = req.getParameterValues("duracao");
            String[] obsStrings = req.getParameterValues("obs");

            String path = ExcellHandler.relatorioUsuario((String) req.getParameter("usuario"), listaStrings, pidStrings, chamadosStrings, dataStrings, duracaoStrings, obsStrings);
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

}
