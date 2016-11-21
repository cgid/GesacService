package br.com.minicom.scr.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.minicom.scr.entity.Log_servico;
import br.com.minicom.scr.entity.Perguntas;
import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.excell.ExcellHandler;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/servicoSrv"}, name = "servicoSrv")
public class ServicoSrv extends HttpServlet {

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
        String op = "";
        FileItemFactory factory = new DiskFileItemFactory();
        Servico servico = new Servico();
        ServletFileUpload upload = new ServletFileUpload(factory);
        HttpServletRequest reqt = (HttpServletRequest) request;
        int usuario, idpergunta = 0;
        HttpSession ses = reqt.getSession(false);
        SimpleQueries equery = new SimpleQueries();
        usuario = Integer.parseInt(String.valueOf(ses.getAttribute("usuarioid")));
        Log_servico log = new Log_servico();

        System.out.println("usuario ID: " + usuario);

        int i = 0;

        List items;
        try {
            items = upload.parseRequest(request);

            Iterator iter = items.iterator();
            if (items.isEmpty()) {
                System.out.println("Vazio");
            }

            while (iter.hasNext()) {
                i++;
                System.err.println("iterator:" + i);
                FileItem item = (FileItem) iter.next();
                if (item.getFieldName().equals("id")) {
                    if (!item.getString().equals("0") || !item.getString().equals("null")) {
                        System.out.println("setou no id");

                        servico.setIdServico(Integer.parseInt(item.getString()));

                    }

                }
                if (item.getFieldName().equals("descricao")) {
                    servico.setDescricao(item.getString());

                    servico.setIdUsuario(usuario);
                    servico.setStatus(1);

                    servico.setCriacaoServico(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    servico.setDtEncerramento(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    System.out.println(String.valueOf(servico.getCell(3).getValue()));
                    System.out.println(servico.toString());
                    if (String.valueOf(servico.getCell(0).getValue()).equals("0")) {

                        System.out.println("entrou no insert");
                        System.out.println(servico.toString());
                        equery.insert(servico);
                        op = "adicionou servico";
                        System.out.println(op);
                        log.setIdServico(Integer.parseInt(String.valueOf(equery.select(servico))));
                    } else {
                        op = "editou servico";
                        System.out.println(op);
                        log.setIdServico(Integer.parseInt(String.valueOf(servico.getCell(0).getValue())));
                        System.out.println("entrou no update");
                        System.out.println(servico.toString());
                        equery.update(servico);
                    }
                }

                if (item.getFieldName().contains("idp")) {
                    System.out.println("IDP: " + Integer.parseInt(item.getString()));
                    idpergunta = Integer.parseInt(item.getString());
                    System.out.println("IDP2: " + idpergunta);
                }
                if (item.getFieldName().contains("pergunta")) {

                    if (!item.getString().isEmpty()) {
                        Perguntas perguntas = new Perguntas();
                        if (idpergunta == 0) {
                            if (String.valueOf(servico.getCell(0).getValue()).equals("0")) {
                                perguntas.setIdServico(equery.select(servico));
                                System.out.println("INSERT novo servico");

                            } else {
                                perguntas.setIdServico(Integer.parseInt(String.valueOf(servico.getCell(0).getValue())));
                            }

                            perguntas.setPergunta(item.getString());
                            System.out.println("INSERT nova pergunta em servico ");
                            System.out.println("INSERT" + perguntas);
                            equery.insert(perguntas);
                        } else {
                            perguntas.setIdPerguntas(idpergunta);
                            perguntas.setPergunta(item.getString());
                            perguntas.setIdServico(Integer.parseInt(String.valueOf(servico.getCell(0).getValue())));
                            System.out.println("UPDATE" + perguntas);
                            equery.update(perguntas);
                            idpergunta = 0;
                        }
                    }
                }
                if (!item.isFormField()) {

                    if (item.getName().length() > 0) {
                        if (item.getFieldName().equals("planilha")) {/* Verifica se item é igual a imagem   se for pega a imagem e o usuario logado.                    
                             */


                            String planilha = ExcellHandler.readSheet(usuario,servico, item);
                            System.err.println(planilha);
                            if (!planilha.equals("lista inserida")) {
                                System.err.println("ERRO DE PLANILHA:" + planilha);
                                ses.setAttribute("erro", planilha);
                                RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
                                servico.setIdServico(equery.select(servico));
                                equery.encerraServico(servico);
                                dispatcher.forward(request, response);
                            }
//                        

                        }

                    }
                }
            }

            System.out.println("LOG:" + op);
            log.setOperacao(op);
            System.out.println("LOG:" + log.toString());
            equery.insert(log);
            equery.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/servico.jsp");

            dispatcher.forward(request, response);

        } catch (FileUploadException | FileNotFoundException | SQLException ex) {
            Logger.getLogger(ServicoSrv.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        }

    }
}
