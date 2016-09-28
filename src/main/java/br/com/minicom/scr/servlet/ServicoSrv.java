package br.com.minicom.scr.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.minicom.scr.entity.Perguntas;
import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.excell.ExcellHandler;
import br.com.minicom.scr.persistence.query.Queries;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.IOException;
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
        
        

            FileItemFactory factory = new DiskFileItemFactory();
            Servico servico = new Servico();
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            HttpServletRequest reqt = (HttpServletRequest) request;
            
            int usuario;
            HttpSession ses = reqt.getSession(false);
            Queries equery = new SimpleQueries();
            usuario = Integer.parseInt(String.valueOf(ses.getAttribute("usuarioid")));
            
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
                    System.out.println(i);
                    FileItem item = (FileItem) iter.next();
                    if (item.getFieldName().equals("id")) {
                        servico.setIdServico(Integer.parseInt(item.getString()));
                    }
                    if (item.getFieldName().equals("descricao")) {
                        servico.setDescricao(item.getString());
                    }
                    if (item.getFieldName().equals("intervaloligacoes")) {
                        servico.setIntervaloLigacoes(Integer.parseInt(item.getString()));
                        servico.setCodUsuario(usuario);
                        
                        servico.setCriacaoServico(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        servico.setDtEncerramento(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        System.out.println(String.valueOf(servico.getCell(3).getValue()));
                        System.out.println(servico.toString());
                        if ( servico.getCell(0).getValue().toString()!=null) {
            
                            equery.update(servico);
                            
                        }
                        equery.insert(servico);
                        
                    }
                    
                    if (item.getFieldName().contains("pergunta")) {
                        Perguntas perguntas = new Perguntas();
                        perguntas.setCodServico(equery.select(servico));
                        perguntas.setPergunta(item.getString());
                        System.out.println(perguntas);
                        equery.insert(perguntas);
                        
                    }
                    if (!item.isFormField()) {
                        
                        if (item.getName().length() > 0) {
                            if (item.getFieldName().equals("planilha")) {/* Verifica se item é igual a imagem   se for pega a imagem e o usuario logado.                    
                                 */
                                
                                equery.close();
                                ExcellHandler.readSheet(servico, item);
                                
                            }
                        }
                    }
                    
                }
                equery.close();
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                
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
