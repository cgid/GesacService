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
            System.out.println(request.getParameter("telefone_usu"));
            System.out.println(request.getParameter("end_novo_usu"));
            if (request.getParameter("telefone_usu") != null) {
                System.out.println("PESQUISA POR USUARIO telefone");
              

                atributosList.add("telefone.ddd");
                atributosList.add("telefone.telefone");

                atributosList.add("contato.nome");
                atributosList.add("contato.usuario_id_usuario");
                JoinsList.add(" INNER JOIN contato  on 	(usuario.id_usuario = contato.usuario_id_usuario) ");
                JoinsList.add(" INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato) ");

            }
            if (request.getParameter("end_novo_usu") != null) {
                JoinsList.add(" INNER JOIN endereco_novo on (usuario.id_usuario=endereco_novo.usuario_id_usuario)");
                System.out.println("PESQUISA POR USUARIO endereco");
                atributosList.add("endereco_novo.id_endereco");
                if (request.getParameter("Descricao_usu") != null) {
                    atributosList.add("endereco_novo.descricao");

                }
                if (request.getParameter("Numero_usu") != null) {
                    atributosList.add("endereco_novo.numero");

                }
                if (request.getParameter("Bairro_usu") != null) {
                    atributosList.add("endereco_novo.bairro");

                }
                if (request.getParameter("Cep_usu") != null) {
                    atributosList.add("endereco_novo.cep");

                }
                if (request.getParameter("Complemento_usu") != null) {
                    atributosList.add("endereco_novo.complemento");

                }
                if (request.getParameter("Municipio_usu") != null) {
                    atributosList.add("municipio.nome_municipio");
                    JoinsList.add(" INNER JOIN municipio on (endereco_novo.Municipio_cod_IBGE=municipio.cod_IBGE)");
                }
            }
            if (request.getParameter("Respostas_usu") != null) {
                contadoresList.add("Respostas");

            }
            if (request.getParameter("chamados_usu") != null) {
                 System.out.println("PESQUISA POR usuaro chamadoS");
                atributosList.add("servico.id_servico");
                atributosList.add("pid.cod_pid");
                atributosList.add("chamado.id_chamado");
                atributosList.add("chamado.observacao");
                atributosList.add("chamado.dt_chamado_aberto");
                JoinsList.add("INNER JOIN chamado on (usuario.id_usuario=chamado.Usuario_cod_usuario)");
                JoinsList.add("INNER JOIN solicitacoes on (chamado.Solicitacoes_id_solicitacao=solicitacoes.id_solicitacao)");
                JoinsList.add(" INNER JOIN servico on (servico.id_servico=solicitacoes.servico_id_servico)");
                
                
                JoinsList.add("INNER JOIN pid on (solicitacoes.PID_cod_pid=pid.cod_pid)");

                if (request.getParameter("duracao_usu") != null) {
                    atributosList.add("log_chamado.duracao");
                    JoinsList.add(" INNER JOIN log_chamado on (chamado.id_chamado=log_chamado.chamado_id_chamado)");
                }
                if (request.getParameter("qtd_atendidos_usu") != null) {
                    contadoresList.add("Atendidos");

                }
            }

        }
        if (request.getParameter("radio").equals("pid")) {
            System.out.println("PESQUISA POR pid");
            atributosList.add("pid.cod_pid");
            atributosList.add("pid.nome_estabelecimento");
            pesquisa = "Pid";
            where = "where pid.cod_pid='" + request.getParameter("where") + "'";
            if (request.getParameter("telefone_pid") != null) {
                System.out.println("PESQUISA POR USUARIO telefone");
                
                atributosList.add("telefone.ddd");
                atributosList.add("telefone.telefone");

                atributosList.add("contato.nome");
              
                JoinsList.add(" INNER JOIN contato  on 	(pid.cod_pid = contato.PID_cod_pid)  ");
                JoinsList.add(" INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato) ");

            }
//            if (request.getParameter("end_novo_pid") != null) {
//                JoinsList.add(" INNER JOIN endereco_novo on (pid.cod_pid=endereco_novo.PID_cod_pid)");
//            }
            if (request.getParameter("end_pid") != null) {
                JoinsList.add(" INNER JOIN endereco on (pid.cod_pid=endereco.PID_cod_pid)");

              
                if (request.getParameter("Descricao_pid") != null) {
                    atributosList.add("endereco.descricao");

                }
                if (request.getParameter("Numero_pid") != null) {
                    atributosList.add("endereco.numero");

                }
                if (request.getParameter("Bairro_pid") != null) {
                    atributosList.add("endereco.bairro");

                }
                if (request.getParameter("Cep_pid") != null) {
                    atributosList.add("endereco.cep");

                }
                if (request.getParameter("Complemento_id") != null) {
                    atributosList.add("endereco.complemento");

                }
                if (request.getParameter("Municipio_usu") != null) {
                    atributosList.add("municipio.nome_municipio");
                    JoinsList.add(" INNER JOIN municipio on (endereco.Municipio_cod_IBGE=municipio.cod_IBGE)");
                }
            }
            if (request.getParameter("end_novo_pid") != null) {
                JoinsList.add(" INNER JOIN endereco_novo on (pid.cod_pid=endereco_novo.PID_cod_pid)");

             
                if (request.getParameter("Descricao_pidn") != null) {
                    atributosList.add("endereco_novo.descricao");

                }
                if (request.getParameter("Numero_pidn") != null) {
                    atributosList.add("endereco_novo.numero");

                }
                if (request.getParameter("Bairro_pidn") != null) {
                    atributosList.add("endereco_novo.bairro");

                }
                if (request.getParameter("Cep_pidn") != null) {
                    atributosList.add("endereco_novo.cep");

                }
                if (request.getParameter("Complemento_idn") != null) {
                    atributosList.add("endereco_novo.complemento");

                }
                if (request.getParameter("Municipio_usun") != null && request.getParameter("Municipio_usu") == null) {
                    atributosList.add("municipio.nome_municipio");
                    JoinsList.add(" INNER JOIN municipio on (endereco_novo.Municipio_cod_IBGE=municipio.cod_IBGE)");
                }
            }
            if (request.getParameter("Respostas_usu") != null) {
                contadoresList.add("Respostas");

            }
            if (request.getParameter("chamados_usu") != null) {
                JoinsList.add(" INNER JOIN chamado on (usuario.id_usuario=chamado.Usuario_cod_usuario)");
                atributosList.add("chamado.id_chamado");
                if (request.getParameter("duracao_usu") != null) {
                    atributosList.add("log_chamado.duracao");
                    JoinsList.add(" INNER JOIN log_chamado on (chamado.id_chamado=log_chamado.chamado_id_chamado)");
                }
                if (request.getParameter("qtd_atendidos_usu") != null) {
                    contadoresList.add("Atendidos");

                }
            }

        }
        if (request.getParameter("rad_servico") != null) {
            System.out.println("PESQUISA POR SERVICO");
            atributosList.add("servico.id_servico");
            atributosList.add("servico.dt_criacao_servico");
            atributosList.add("servico.descricao");

        }
        if (request.getParameter("rad_chamado") != null) {
            System.out.println("PESQUISA POR CHAMADO");
            atributosList.add("chamado.id_chamado");
            atributosList.add("chamado.Usuario_cod_usuario");
            if (request.getParameter("Data_chdo") != null) {
                atributosList.add("chamado.dt_chamado_aberto");
            }
            if (request.getParameter("Observação_chdo") != null) {
                atributosList.add("chamado.observação");
            }

        }

        request.setAttribute("atributosList", atributosList);
        if (!contadoresList.isEmpty()) {
            request.setAttribute("contadores", contadoresList);
        }
        request.setAttribute("where", where);
        request.setAttribute("pesquisa", pesquisa);
        request.setAttribute("JoinList", JoinsList);
        request.setAttribute("JoinList", JoinsList);
        System.out.println("AINDA TA NESSA");
        RequestDispatcher dispatcher = request.getRequestDispatcher("teste_ajax.jsp");
        System.out.println("AINDA TA NESSA");
        dispatcher.forward(request, response);
    }
}
