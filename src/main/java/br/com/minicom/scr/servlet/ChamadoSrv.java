/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.servlet;

import br.com.minicom.scr.entity.Chamado;
import br.com.minicom.scr.entity.Contato;
import br.com.minicom.scr.entity.Log_chamado;
import br.com.minicom.scr.entity.Log_contato;

import br.com.minicom.scr.entity.Respostas;
import br.com.minicom.scr.entity.Solicitacoes;
import br.com.minicom.scr.entity.Telefone;
import br.com.minicom.scr.persistence.ConnectionFactory;
import br.com.minicom.scr.persistence.query.SimpleQueries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
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

    /**
     * Usada na pagina de chamado
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest reqt = (HttpServletRequest) request;
        HttpSession ses = reqt.getSession(false);
        Chamado c = new Chamado();
        Contato contato = new Contato();
        c.setCodUsuario(Integer.parseInt(String.valueOf(ses.getAttribute("usuarioid"))));
        Solicitacoes s = new Solicitacoes();
        
        c.setChamadoAberto(request.getParameter("dt_chamado_aberto"));
        Log_chamado log = new Log_chamado();
        SimpleQueries equery = new SimpleQueries();
        String operacaoLog = null;
        try {
            
            c.setIdSolicitacao(Integer.parseInt(request.getParameter("idSolicitacao")));
            
            s = (Solicitacoes) equery.select(s, Integer.parseInt(request.getParameter("idSolicitacao")));
            int QtdeTentativas = (1 + Integer.parseInt(String.valueOf(s.getCell(1).getValue())));
            s.setUltTentativa(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println(s.toString());
            s.setQtdeTentativas(QtdeTentativas);
            equery.update(s);
            
            c.setObservacao(request.getParameter("observacao"));
            
            equery.insert(c);
            
            String[] invalidos = request.getParameterValues("invalido");
            
            String[] contatonovo = request.getParameterValues("contatonovo");
            
            String[] contatos = request.getParameterValues("contato");
            
            String[] telefonenovo = request.getParameterValues("telefonenovo");
            String[] emailnovo = request.getParameterValues("emailnovo");
            
            String[] respostas = request.getParameterValues("resposta");
            
            List contatosList = new ArrayList();
            int contador = 0;
            
            if (null != invalidos) { //invalída contatos que foram selecionados como invalidos
                for (int i = 0; i < invalidos.length; i++) {
                    Log_contato lc = new Log_contato();
                    lc.setIdUsuario(Integer.parseInt(String.valueOf(ses.getAttribute("usuarioid"))));
                    Telefone telefone = new Telefone();
                    telefone = (Telefone) equery.select(telefone, Integer.parseInt(invalidos[i]));
                    telefone.setSituacao(0);
                    lc.setIdContato(Integer.parseInt(String.valueOf(telefone.getCell(0).getValue())));
                    lc.setOperacao("invalidou");
                    System.out.println("TELEFONE INVALIDADO");
                    equery.update(telefone);
                    equery.insert(lc);
                    contador++;
                }
            }
            if (null != contatonovo) {
                for (int i = 0; i < contatonovo.length; i++) {
                    contatosList.add(contatonovo[i]);
                }
            }
            if (contatosList.isEmpty()) {
                System.out.println("LISTA VAZIA");
            }
            
            if (request.getParameter("realizado").equals("1")) {
                operacaoLog = "realizado com sucesso";
            }
            if (contador == contatos.length && contatosList.isEmpty()) {
                operacaoLog = " concluido, mas sem sucesso";
            }
            //analiza se o contato foi realizado ou
            //se todos os contatos foram invalidados e nenhum contato foi adiciondo e tira solicitacao da lista de ser ligado.
            if (request.getParameter("realizado").equals("1") || contador == contatos.length && contatosList.isEmpty()) {
                // insere as repostas caso tenha sido contatado 
                if (respostas != null) {
                    
                    if (0 < respostas.length) {
                        
                        for (int i = 0; i < respostas.length; i++) {
                            
                            if (respostas[i].trim().length() > 0) {
                                
                                Respostas resposta = new Respostas();
                                resposta.setCodChamado(equery.select(c));
                                
                                resposta.setResposta(respostas[i]);
                                System.out.println(resposta.toString());
                                equery.insert(resposta);
                                
                            }
                        }
                    }
                }
                String sql = "UPDATE solicitacoes SET em_chamado=4 WHERE  " + s.getColumnName(0) + "= " + s.getCell(0).getValue();
                operacaoLog = "realizado com sucesso";
                
                System.err.println(sql);
                Connection conn = ConnectionFactory.getConnection();
                Statement stmt;
                stmt = conn.createStatement();
                
                stmt.executeUpdate(sql);
            }

            //se o contado nao foi realizado tras a solicitacao de volta 
            //a lista a ser  realizado os chamados
            if (request.getParameter("realizado").equals("0")) {
                String sql = "UPDATE solicitacoes SET em_chamado=2 WHERE  " + s.getColumnName(0) + "= " + s.getCell(0).getValue();
                operacaoLog = "realizado sem sucesso";
                System.err.println("Entrou no if de set=4");
                System.err.println(sql);
                Connection conn = ConnectionFactory.getConnection();
                Statement stmt;
                stmt = conn.createStatement();
                
                stmt.executeUpdate(sql);
                
                s.setDtAgenda(request.getParameter("datepicker") + " " + request.getParameter("timepicker"));
                
                equery.update(s);
            }

            //adiciona telefone e contatos novos caso tenha sido inserido 
            if (null != telefonenovo) {
                for (int i = 0; i < telefonenovo.length; i++) {
                    
                    if (10 <= telefonenovo[i].length()) {
                        contato.setCodPid(Integer.parseInt(request.getParameter("pid").trim()));
                        contato.setNome(contatonovo[i]);
                        if (null != emailnovo[i]) {
                            contato.setEmail(emailnovo[i]);
                        }
                        
                        contato.setSituacao(1);
                        System.out.println("CONTATO NOVO: " + contato);
                        contato.setIdUsuario((int) ses.getAttribute("usuarioid"));
                        equery.insert(contato);
                        System.out.println(contato.toString());
                        Telefone telefone = new Telefone();
                        String tel[] = telefonenovo[i].replace(")", ",").split(",");
                        int ddd = Integer.parseInt(tel[0].replace("(", "").trim());
                        int t = Integer.parseInt(tel[1].replace("-", "").trim());
                        
                        telefone.setDdd(ddd);

//                       
                        telefone.setTelefone(t);
                        telefone.setIdContato(equery.select(contato));
                        telefone.setSituacao(1);
                        System.out.println(telefone.toString());
                        equery.insert(telefone);
                        
                    }
                }
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/chamados.jsp?idchamado=" + request.getParameter("idchamado")
            );
            
            log.setIdChamado(equery.select(c));
            
            log.setOperacao(operacaoLog);
            log.setDuracao(request.getParameter("duracao"));
            equery.insert(log);
            equery.close();
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
            System.out.println("Erro na Solicitação. Tente de novo ou entre em contato do Administrador do Banco de Dados");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
            
        }
        
    }

    /**
     * adiciona a tentatva a solicitacao selecionada
     *
     * @param QtdeTentativas
     * @param s
     * @throws SQLException
     */
}
