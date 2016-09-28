/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Chamado;
import br.com.minicom.scr.entity.Log;
import br.com.minicom.scr.entity.Respostas;
import br.com.minicom.scr.entity.Solicitacoes;
import br.com.minicom.scr.persistence.ConnectionFactory;
import br.com.minicom.scr.persistence.query.SimpleQueries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Edilson Jr
 */
@WebServlet(name = "ChamadoSrv", urlPatterns = {"/ChamadoSrv"})
public class ChamadoSrv extends HttpServlet {

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
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        HttpServletRequest reqt = (HttpServletRequest) request;
        HttpSession ses = reqt.getSession(false);
        Chamado c = new Chamado();
        c.setCodUsuario(Integer.parseInt(String.valueOf(ses.getAttribute("usuarioid"))));
        Solicitacoes s = new Solicitacoes();
        c.setDtChamado(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Log log = new Log();
        SimpleQueries equery = new SimpleQueries();

        try {
            int i = 0;

            List items = upload.parseRequest(reqt);

            Iterator iter = items.iterator();
            if (items.isEmpty()) {
                System.out.println("Vazio");
            }
            System.out.println(c.toString());
            while (iter.hasNext()) {
                i++;
                System.out.println(i);
                FileItem item = (FileItem) iter.next();
                if (item.getFieldName().equals("idSolicitacao")) {
                    c.setIdSolicitacao(Integer.parseInt(item.getString()));
                    System.out.println(c.toString());

                    s = (Solicitacoes) equery.select(s, Integer.parseInt(item.getString()));
                    int QtdeTentativas = (1 + Integer.parseInt(String.valueOf(s.getCell(1).getValue())));
                    s.setEmChamado(0);
                    String sql = "UPDATE solicitacoes SET em_chamado=1,Qtde_tentativas=" + QtdeTentativas + " WHERE  " + s.getColumnName(0) + "= " + s.getCell(0).getValue();
                    System.err.println(sql);
                    Connection conn = ConnectionFactory.getConnection();
                    Statement stmt;
                    stmt = conn.createStatement();

                    stmt.executeUpdate(sql);

                    stmt.close();

                }
                if (item.getFieldName().contains("observacao")) {
                    c.setObservacao(item.getString());
                    System.out.println(c.toString());
                    equery.insert(c);
                }
                if (item.getFieldName().contains("ok")) {
                    s.setContatoOk(Integer.parseInt(item.getString()));
                    String sql = "UPDATE solicitacoes SET  contato_ok=" + s.getCell(4).getValue() + " WHERE  " + s.getColumnName(0) + "= " + s.getCell(0).getValue();
                    System.err.println(sql);
                    Connection conn = ConnectionFactory.getConnection();
                    Statement stmt;
                    stmt = conn.createStatement();

                    stmt.executeUpdate(sql);

                    stmt.close();
                }

                if (item.getFieldName().contains("sucesso")) {
                    s.setContatoOk(Integer.parseInt(item.getString()));
                    if (item.getString().contains("0")) {

                    }
                }

                if (item.getFieldName().contains("resposta")) {

                    Respostas respostas = new Respostas();
                    respostas.setCodChamado(equery.select(c));
                    log.setIdChamado(equery.select(c));

                    respostas.setResposta(item.getString());
                    System.out.println(respostas.toString());
                    equery.insert(respostas);
                }

            }
            equery.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/chamados.jsp?idchamado=" + ses.getAttribute("idchamado")
            );

            dispatcher.forward(request, response);
            return;
        } catch (FileUploadException ex) {

            System.out.println("Não foi possível fazer o Upload do arquivo! Tente Novamente " + ex);
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println("Erro na Solicitação. Tente de novo ou entre em contato do Administrador do Banco de Dados");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);

        }

    }

}
