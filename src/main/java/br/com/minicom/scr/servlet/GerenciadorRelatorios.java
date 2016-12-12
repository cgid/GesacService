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
//        +"    INNER JOIN contato  on 	(pid.cod_pid = contato.PID_cod_pid)\n"
//                + "    INNER JOIN endereco on 	(pid.cod_pid = endereco.PID_cod_pid)\n"
//                + "    INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato)\n"
//                + "    INNER JOIN municipio on 	(endereco.Municipio_cod_IBGE = municipio.cod_IBGE)\n"
//                + "    INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
//                + "    INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"

        List<String> atributosList = new ArrayList<>();
        List<String> contadoresList = new ArrayList<>();
        List<String> JoinsList = new ArrayList<>();

        System.out.println("Entrou na servlet");
        if (request.getParameter("radio").equals("usuario")) {
            System.out.println("PESQUISA POR USUARIO");

            pesquisa = "usuario";
            where = "where usuario.nome='" + request.getParameter("where") + "'";
            atributosList.add("servico.id_servico");
            atributosList.add("pid.cod_pid");
            atributosList.add("chamado.id_chamado");

            atributosList.add("chamado.dt_chamado_aberto");
            atributosList.add("log_chamado.duracao");
            atributosList.add("chamado.observacao");
            JoinsList.add("INNER JOIN chamado on (usuario.id_usuario=chamado.Usuario_cod_usuario)");
            JoinsList.add("INNER JOIN solicitacoes on (chamado.Solicitacoes_id_solicitacao=solicitacoes.id_solicitacao)");
            JoinsList.add(" INNER JOIN servico on (servico.id_servico=solicitacoes.servico_id_servico)");

            JoinsList.add("INNER JOIN pid on (solicitacoes.PID_cod_pid=pid.cod_pid)");

            JoinsList.add(" INNER JOIN log_chamado on (chamado.id_chamado=log_chamado.chamado_id_chamado)");

            request.setAttribute("where", where);
            request.setAttribute("pesquisa", pesquisa);
            request.setAttribute("JoinList", JoinsList);
            request.setAttribute("atributosList", atributosList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio_usuario.jsp");

            dispatcher.forward(request, response);
        }

        if (request.getParameter("radio").equals("pid")) {
            where = request.getParameter("where");

            RequestDispatcher dispatcher = request.getRequestDispatcher("relatorio_pid.jsp?pid="+where);
            dispatcher.forward(request, response);
        }
    }
}
