/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.consultas.ChamadoEnderecos;
import br.com.minicom.scr.consultas.ChamadoeRespostas;
import br.com.minicom.scr.consultas.ChamadosAtendidos;
import br.com.minicom.scr.consultas.ConsultaContatos;
import br.com.minicom.scr.entity.Perguntas;
import br.com.minicom.scr.entity.Servico;
import br.com.minicom.scr.entity.Solicitacoes;
import br.com.minicom.scr.entity.exceptions.NotIsDeletableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsInsertableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsSelectableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsUpgradeableEntityException;
import br.com.minicom.scr.persistence.ConnectionFactory;
import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.querygen.QueryGenerator;
import br.com.minicom.scr.persistence.querygen.SimpleQueryGenerator;
import br.com.minicom.scr.relatorios.RelatorioPid;
import br.com.minicom.scr.relatorios.RelatorioUsuario;
import br.com.minicom.scr.relatorios.RelatorioPorPid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author murilo
 */
public class SimpleQueries implements Queries<Entity> {

    Connection conn;
    Statement stmt = null;
    Statement stmt2 = null;

    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement pstmt = null;

    public SimpleQueries() {
        conn = ConnectionFactory.getConnection();

    }

    /**
     *
     * @param e entidade a ser inserida
     * @throws java.sql.SQLException
     * @throws NotIsInsertableEntityException
     */
    @Override
    public void insert(Entity e) throws NotIsInsertableEntityException, SQLException {

        for (int i = e.getCell(0).isIterable() ? 1 : 0; i < e.getNumOfColumns(); i++) {
            if (e.getCell(i).isNotNull() && e.getCell(i).getValue().equals(null)) {
                throw new NotIsInsertableEntityException();
            }
        }

        QueryGenerator qg = new SimpleQueryGenerator();

        if (validaInsert(e)) {

            pstmt = conn.prepareStatement(qg.insertGenerator(e));
            int col = e.getCell(0).isIterable() ? e.getNumOfColumns() - 1 : e.getNumOfColumns();

            for (int i = 1; i <= col; i++) {

                if (e.getCell(0).isIterable()) {
                    if (e.getCell(i).getType().equals(Type.NUM)) {
                        pstmt.setInt(i, e.getCell(i).getValue() == null ? 0 : (int) e.getCell(i).getValue());
                    } else {
                        pstmt.setString(i, String.valueOf(e.getCell(i).getValue()));
                    }
                } else if (e.getCell(i - 1).getType().equals(Type.NUM)) {
                    pstmt.setInt(i, e.getCell(i - 1).getValue() == null ? 0 : (int) e.getCell(i - 1).getValue());
                } else {
                    pstmt.setString(i, String.valueOf(e.getCell(i - 1).getValue()));
                }
            }

            pstmt.executeUpdate();
            System.out.println("O " + e.getTableName() + ":" + e.getCell(1).getValue() + " inserido com sucesso!");

            pstmt.close();
        } else {
            System.out.println(e.getTableName() + " valor: " + e.getCell(0).getValue() + " ja inserido");
        }

    }

    /**
     *
     * @param e
     * @throws NotIsDeletableEntityException
     */
    @Override
    public void delete(Entity e) throws NotIsDeletableEntityException {
        if (e.getCell(0).getValue().equals(null)) {
            throw new NotIsDeletableEntityException();
        }

        Statement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(qg.deleteGenerator(e));

            stmt.close();

        } catch (SQLException er) {
            System.out.println(er);
        }

    }

    /**
     *
     * @param e
     * @throws NotIsUpgradeableEntityException
     */
    @Override
    public void update(Entity e) throws NotIsUpgradeableEntityException {

        if (!e.getCell(0).isIterable() && e.getCell(0).getValue().equals(null)) {
            throw new NotIsUpgradeableEntityException();
        }

        pstmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
            pstmt = conn.prepareStatement(qg.updateGenerator(e));

            int col = e.getCell(0).isIterable() ? e.getNumOfColumns() - 1 : e.getNumOfColumns();

            for (int i = 1; i <= col; i++) {
                if (e.getCell(0).isIterable()) {
                    if (e.getCell(i).getType().equals(Type.NUM)) {
                        pstmt.setInt(i, e.getCell(i).getValue() == null ? 0 : Integer.parseInt(String.valueOf(e.getCell(i).getValue())));

                    } else {
                        pstmt.setString(i, String.valueOf(e.getCell(i).getValue()));
                    }
                } else if (e.getCell(i - 1).getType().equals(Type.NUM)) {
                    pstmt.setInt(i, e.getCell(i - 1).getValue() == null ? 0 : (int) e.getCell(i - 1).getValue());
                } else {
                    pstmt.setString(i, String.valueOf(e.getCell(i - 1).getValue()));
                }
            }

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException | ArrayIndexOutOfBoundsException er) {
            System.out.println(er);
        }
    }

    /**
     *
     * @param e entidade que sera selecionado o id
     * @return o id do ultimo registro da entidade selecionada, quando for
     * contato vai usar pid e e nome na construcao da query, ja quando for
     * chamado e servico usa o usuario em consideração
     * @throws NotIsSelectableEntityException
     */
    @Override
    public int select(Entity e) throws NotIsSelectableEntityException {
        String sql = "SELECT * from " + e.getTableName() + " order by " + e.getColumnName(0) + " desc limit 1";
        int next = 0;
        try {
            if (e.getTableName().equals("Contato")) {
                sql = "select * from " + e.getTableName() + " where  " + e.getColumnName(3) + "= '" + String.valueOf(e.getCell(3).getValue()) + "' and " + e.getColumnName(1) + "= '" + String.valueOf(e.getCell(1).getValue()) + "';";

            }
            switch (e.getTableName()) {
                case "Chamado":
                    sql = "SELECT * from " + e.getTableName() + " order by " + e.getColumnName(0) + " desc limit 1";

                    break;

                case "Servico":
                    sql = "SELECT * from " + e.getTableName() + " where  usuario_id_usuario =" + e.getCell(5).getValue() + " order by " + e.getColumnName(0) + " desc limit 1";

                    break;
            }

            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                next = resultSet.getInt(1);
            }
            resultSet.close();
            stmt.close();

            resultSet.close();

            return next;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    /**
     * Usado para selecionar uma entidade do banco atraves do seu tipo e id
     *
     * @param e tipo da entidade que será selecionada
     * @param id
     * @return a entidade selecionada pelo id
     * @throws SQLException
     */
    public Entity select(Entity e, int id) throws SQLException {

        //SELECT * FROM ENTITY WHERE ID = *;
        stmt = null;
        rs = null;
        String sql = "SELECT * FROM " + e.getTableName() + " WHERE " + e.getColumnName(0) + "= " + id;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                for (int i = 0; i <= e.getNumOfColumns() - 1; i++) {

                    e.setCell(i, rs.getString(i + 1));
                }
            }
            rs.close();
            stmt.close();

            return e;
        } catch (Exception f) {
            System.out.println(f);
        }
        return null;
    }

    /**
     *
     * @param login
     * @param senha
     * @param e
     * @return id
     * @throws SQLException
     */
    public int select(String login, String senha, Entity e) throws SQLException {

        stmt = null;
        rs = null;
        int id = 0;
        String sql = "SELECT * FROM " + e.getTableName() + " WHERE " + e.getColumnName(2) + "= '" + login + "' and " + e.getColumnName(3) + "= '" + senha + "'";
        try {
            stmt = conn.createStatement();
            System.out.println(sql);;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(e.getColumnName(0));

            }
            rs.close();
            stmt.close();

            return id;
        } catch (Exception f) {
            System.out.println(f);
        }
        return id;
    }

    /**
     *
     * @param e entidade que será listada
     * @return lista de entidades(e)
     * @throws SQLException
     */
    public List<Entity> selectList(Entity e) throws SQLException {

        try {
            String sql = "SELECT * FROM  " + e.getTableName() + " order by " + e.getColumnName(0) + " desc";

            String clazz = "br.com.minicom.scr.entity." + e.getTableName();
            stmt = null;
            rs = null;
            List<Entity> l = new ArrayList<>();
            Entity aux;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                aux = (Entity) Class.forName(clazz).newInstance();
                for (int i = 1; i <= aux.getNumOfColumns(); i++) {
                    aux.setCell(i - 1, rs.getString(i));
                }
                l.add(aux);
            }
            rs.close();
            stmt.close();

            return l;
        } catch (SQLException | ArrayIndexOutOfBoundsException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<RelatorioPorPid> selectChamadoPorPid(int pid) throws SQLException {

        String sql = "  select\n"
                + "servico.id_servico,\n"
                + "usuario.nome,\n"
                + "chamado.dt_chamado_aberto,\n"
                + "chamado.observacao,\n"
                + "chamado.id_chamado\n"
                + "from chamado\n"
                + "INNER JOIN solicitacoes ON(chamado.Solicitacoes_id_solicitacao=solicitacoes.id_solicitacao)\n"
                + "INNER JOIN  pid on (solicitacoes.PID_cod_pid=pid.cod_pid)\n"
                + "INNER JOIN servico on (servico.id_servico=solicitacoes.servico_id_servico)\n"
                + "INNER JOIN usuario on (chamado.Usuario_cod_usuario=usuario.id_usuario)\n"
                + "where pid.cod_pid=" + pid;

        stmt = null;
        rs = null;
        List<RelatorioPorPid> l = new ArrayList<>();
        String duracao = "indefinido";
        stmt = conn.createStatement();
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            stmt2 = conn.createStatement();
            String sql2 = "SELECT log_chamado.duracao FROM `log_chamado` WHERE `chamado_id_chamado`=" + rs.getInt(5);
            rs2 = stmt2.executeQuery(sql2);
            while (rs2.next()) {
                duracao = rs2.getString(1);
            }

            RelatorioPorPid rpp = new RelatorioPorPid(
                    rs.getInt(1),
                    rs.getString(2), duracao,
                    rs.getString(3),
                    rs.getString(4));
            System.out.println(rpp.toString());
            l.add(rpp);
        }
        rs.close();
        stmt.close();

        return l;

    }

    @Override
    public void close() {
        try {
            System.out.println("conexao fechada");
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Seleciona contatos que serão usados no chamado
     *
     * @param servico selecionado
     * @return lista de contato referente a solicitação selecionada para o
     * chamado(ConsultaContatos)
     */
    public List getContatos(String servico) {

        List<ConsultaContatos> consultas = new ArrayList<>();
        String sql = "SELECT DISTINCT\n"
                + "telefone.id_telefone,\n"
                + "contato.nome,\n"
                + "contato.email,\n"
                + "telefone.Situacao,\n"
                + "telefone.ddd,\n"
                + "telefone.telefone\n"
                + "    \n"
                + "    \n"
                + "FROM \n"
                + "	pid \n"
                + "    INNER JOIN contato  on 	(pid.cod_pid = contato.PID_cod_pid)\n"
                + "    INNER JOIN endereco on 	(pid.cod_pid = endereco.PID_cod_pid)\n"
                + "    INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato)\n"
                + "    INNER JOIN municipio on 	(endereco.Municipio_cod_IBGE = municipio.cod_IBGE)\n"
                + "    INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
                + "    INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"
                + "   \n"
                + "    \n"
                + " WHERE solicitacoes.em_chamado= 3 and servico.id_servico=" + servico + ";";

        stmt = null;
        rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                consultas.add(new ConsultaContatos(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));

            }

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;
    }

    public List getContatosRelatorio(String pid) {

        List<ConsultaContatos> consultas = new ArrayList<>();
        String sql = "SELECT DISTINCT\n"
                + "telefone.id_telefone,\n" + "contato.nome,\n"
                + "contato.email,\n"
                + "telefone.Situacao,\n"
                + "telefone.ddd,\n"
                + "telefone.telefone\n"
                + "    \n"
                + "    \n"
                + "FROM \n"
                + "	pid \n"
                + "    INNER JOIN contato  on 	(pid.cod_pid = contato.PID_cod_pid)\n"
                + "    INNER JOIN endereco on 	(pid.cod_pid = endereco.PID_cod_pid)\n"
                + "    INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato)\n"
                + "    INNER JOIN municipio on 	(endereco.Municipio_cod_IBGE = municipio.cod_IBGE)\n"
                + "    INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
                + "    INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"
                + "   \n"
                + "    \n"
                + " WHERE pid.cod_pid=" + pid + ";";

        stmt = null;
        rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                consultas.add(new ConsultaContatos(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));

            }

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;
    }

    /**
     * Seleciona o endereco e pid para o chamado a ser realizado, mudando o
     * status dessa solicitacao ate que o chamado seja finalizado.
     *
     * @param servico
     * @return ChamadoEnderecos
     */
    public List getChamado(String servico) {
        Solicitacoes e = chamadoHandler(servico);

        List<ChamadoEnderecos> consultas = new ArrayList<>();
        String sql = "SELECT DISTINCT\n"
                + "	pid.cod_pid,\n"
                + "    pid.nome_estabelecimento,\n"
                + "    endereco.descricao,\n"
                + "    endereco.numero,\n"
                + "    endereco.bairro,\n"
                + "    endereco.complemento,\n"
                + "    municipio.nome_municipio,\n"
                + "    municipio.uf,\n"
                + "    municipio.cod_IBGE,\n"
                + "    solicitacoes.id_solicitacao\n"
                + "    \n"
                + "    \n"
                + "FROM \n"
                + "	pid \n"
                + "    INNER JOIN contato  on 	(pid.cod_pid = contato.PID_cod_pid)\n"
                + "    INNER JOIN endereco on 	(pid.cod_pid = endereco.PID_cod_pid)\n"
                + "    INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato)\n"
                + "    INNER JOIN municipio on 	(endereco.Municipio_cod_IBGE = municipio.cod_IBGE)\n"
                + "    INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
                + "    INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"
                + "   \n"
                + "    \n"
                + " WHERE solicitacoes.em_chamado= 3 and servico.id_servico=" + servico + ";";

        stmt = null;
        rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.err.println(rs.getString(2));
                consultas.add(new ChamadoEnderecos(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));

            }

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;

    }

    /**
     *
     * @param Pid
     * @return
     */
    public List getRelatorioPid(String Pid) {

        List<ChamadoEnderecos> consultas = new ArrayList<>();
        String sql = "SELECT DISTINCT\n"
                + "	pid.cod_pid,\n"
                + "    pid.nome_estabelecimento,\n"
                + "    endereco.descricao,\n"
                + "    endereco.numero,\n"
                + "    endereco.bairro,\n"
                + "    endereco.complemento,\n"
                + "    municipio.nome_municipio,\n"
                + "    municipio.uf,\n"
                + "    municipio.cod_IBGE,\n"
                + "    solicitacoes.id_solicitacao\n"
                + "    \n"
                + "    \n"
                + "FROM \n"
                + "	pid \n"
                + "    INNER JOIN contato  on 	(pid.cod_pid = contato.PID_cod_pid)\n"
                + "    INNER JOIN endereco on 	(pid.cod_pid = endereco.PID_cod_pid)\n"
                + "    INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato)\n"
                + "    INNER JOIN municipio on 	(endereco.Municipio_cod_IBGE = municipio.cod_IBGE)\n"
                + "    INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
                + "    INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"
                + "   \n"
                + "    \n"
                + " WHERE pid.cod_pid=" + Pid + ";";

        stmt = null;
        rs = null;
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                consultas.add(new ChamadoEnderecos(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));

            }

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;
    }

    public List getRelatorioEnderecoNovo(String Pid) {

        List<ChamadoEnderecos> consultas = new ArrayList<>();
        String sql = "SELECT DISTINCT\n"
                + "	pid.cod_pid,\n"
                + "    pid.nome_estabelecimento,\n"
                + "    endereco_novo.descricao,\n"
                + "    endereco_novo.numero,\n"
                + "    endereco_novo.bairro,\n"
                + "    endereco_novo.complemento,\n"
                + "    municipio.nome_municipio,\n"
                + "    municipio.uf,\n"
                + "    municipio.cod_IBGE,\n"
                + "    solicitacoes.id_solicitacao\n"
                + "    \n"
                + "    \n"
                + "FROM \n"
                + "	pid \n"
                + "    INNER JOIN contato  on 	(pid.cod_pid = contato.PID_cod_pid)\n"
                + "    INNER JOIN endereco_novo on 	(pid.cod_pid = endereco_novo.PID_cod_pid)\n"
                + "    INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato)\n"
                + "    INNER JOIN municipio on 	(endereco_novo.Municipio_cod_IBGE = municipio.cod_IBGE)\n"
                + "    INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
                + "    INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"
                + "   \n"
                + "    \n"
                + " WHERE pid.cod_pid=" + Pid + ";";

        stmt = null;
        rs = null;
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                consultas.add(new ChamadoEnderecos(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));

            }

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;
    }

    /**
     * Seleciona as perguntas referentes ao servico selecionado.
     *
     * @param servico
     * @return lista de perguntas
     */
    public List<Perguntas> getPerguntas(String servico) {
        String idSolicitacao = "";
        List<Perguntas> perguntas = new ArrayList<>();

        String sql = "Select\n"
                + "    perguntas.Id_Perguntas,\n"
                + "    perguntas.pergunta,\n"
                + "    perguntas.Servico_id_servico,\n"
                + "   solicitacoes.id_solicitacao\n"
                + "FROM \n"
                + "	pid \n"
                + "\n"
                + "INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
                + "    INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"
                + "    INNER JOIN perguntas on 	(servico.id_servico=perguntas.Servico_id_servico)\n"
                + " WHERE solicitacoes.em_chamado = 3 and servico.id_servico = " + servico + ";";

        stmt = null;
        rs = null;

        try {
            System.out.println(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Perguntas p = new Perguntas();

                p.setIdPerguntas(rs.getInt(1));
                p.setIdServico(rs.getInt(3));
                p.setPergunta(rs.getString(2));
                idSolicitacao = rs.getString(4);
                perguntas.add(p);
            }
            mudaStatus(idSolicitacao);

            return perguntas;

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return perguntas;
    }

    /**
     *
     * @param servico
     * @return lista de perguntas
     */
    public List<Perguntas> Perguntas(String servico) {

        List<Perguntas> perguntas = new ArrayList<>();

        String sql = "Select *"
                + "FROM \n"
                + "	Perguntas \n"
                + "\n"
                + " WHERE servico_id_servico = " + servico + ";";

        stmt = null;
        rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Perguntas p = new Perguntas();

                p.setIdPerguntas(rs.getInt(1));
                p.setPergunta(rs.getString(2));
                p.setIdServico(rs.getInt(3));
                perguntas.add(p);
            }

            return perguntas;

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return perguntas;
    }

    /**
     * Verifica se a entidade ja existe no banco de dados
     *
     * @param e entidade seleciona
     * @return boolean,
     * @throws SQLException
     */
    private boolean validaInsert(Entity e) throws SQLException {
        stmt = conn.createStatement();
        String sql = "select * from " + e.getTableName() + " where  " + e.getColumnName(0) + "= " + e.getCell(0).getValue();
        if (e.getTableName().equals("Contato")) {
            sql = "select * from " + e.getTableName() + " where  " + e.getColumnName(3) + "= '" + String.valueOf(e.getCell(3).getValue()) + "' and " + e.getColumnName(1) + "= '" + String.valueOf(e.getCell(1).getValue()) + "';";

        }
        if (e.getTableName().equals("Telefone")) {
            sql = "select * from " + e.getTableName() + " where  " + e.getColumnName(2) + "= '" + String.valueOf(e.getCell(2).getValue()) + "' and " + e.getColumnName(1) + "= '" + String.valueOf(e.getCell(1).getValue()) + "' and " + e.getColumnName(3) + "= '" + String.valueOf(e.getCell(3).getValue()) + "';";

        }
        if (e.getTableName().equals("Solicitacoes")) {
            sql = "select * from " + e.getTableName() + " where  " + e.getColumnName(6) + "= '" + String.valueOf(e.getCell(6).getValue()) + "' and " + e.getColumnName(7) + "= '" + String.valueOf(e.getCell(7).getValue()) + "';";

        }

        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            stmt.close();
            rs.close();
            return false;
        }
        stmt.close();
        rs.close();
        return true;

    }

    /**
     * Muda o status da solicitacao selecionada para a construcao do chamado
     *
     * @param servico
     * @return solicitacao
     */
    public Solicitacoes chamadoHandler(String servico) {
        Solicitacoes solicitacao = new Solicitacoes();

        String sql = "SELECT * FROM " + solicitacao.getTableName() + " WHERE " + solicitacao.getColumnName(7) + "= " + servico + " and " + solicitacao.getColumnName(4) + "=0 order by RAND()   limit 1";
        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            if (rs.next()) {

                for (int i = 1; i <= solicitacao.getNumOfColumns(); i++) {

                    solicitacao.setCell(i - 1, rs.getString(i));

                }

                sql = "UPDATE solicitacoes SET em_chamado=3 WHERE  " + solicitacao.getColumnName(0) + "= " + solicitacao.getCell(0).getValue();

                stmt.executeUpdate(sql);
                rs.close();
                stmt.close();
                return solicitacao;
            } else {

                sql = "SELECT * FROM " + solicitacao.getTableName() + " WHERE " + solicitacao.getColumnName(7) + "= " + servico + " and " + solicitacao.getColumnName(4) + "=1 order by RAND()   limit 1";
                stmt = conn.createStatement();

                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    for (int i = 1; i <= solicitacao.getNumOfColumns(); i++) {

                        solicitacao.setCell(i - 1, rs.getString(i));

                    }
                }
                sql = "UPDATE solicitacoes SET em_chamado=3 WHERE  " + solicitacao.getColumnName(0) + "= " + solicitacao.getCell(0).getValue();

                stmt.executeUpdate(sql);
                rs.close();
                stmt.close();
                return solicitacao;
            }
        } catch (Exception f) {
            System.out.println(f);
        }
        return solicitacao;
    }

    /**
     * muda status da solicitacao apos a selecao das informacoes para o chamado
     * ser realizado para que nao seja selecionado por outro usuario
     *
     * @param e
     * @throws SQLException
     */
    public void mudaStatus(String e) throws SQLException {
        String sql = "UPDATE solicitacoes SET em_chamado=2 WHERE  id_solicitacao= " + e;
        System.err.println("MUDASTATUS: " + sql);
        stmt.executeUpdate(sql);
        stmt.close();
        rs.close();
    }

    /**
     * autentica usuario pelo login e senha caso ele esteja registrado no banco
     * de dados
     *
     * @param userid
     * @param pwd
     * @return boolean
     * @throws SQLException
     */
    public boolean autenticar(String userid, String pwd) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("select * from usuario where login=? and senha=? and ativo=1");
        ps.setString(1, userid);
        ps.setString(2, pwd);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;
        } else {
            System.out.println("Senha e/ou Usuário incorreto! <a href='index.jsp'>Tente Novamente</a>");
        }
        return false;
    }

    /**
     *
     * @param userid
     * @param pwd
     * @return autentica e retorna o perfil para o controle de permissao(a barra
     * com as paginas disponiveis)
     * @throws SQLException
     */
    public String autenticarPerfil(String userid, String pwd) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("select * from usuario where login=? and senha=?");
        PreparedStatement ps2;

        ps.setString(1, userid);
        ps.setString(2, pwd);

        ResultSet rs = ps.executeQuery();
        ResultSet rs2;

        if (rs.next()) {
            int perfil = rs.getInt("Perfil_cod_perfil");
            rs.close();

            ps2 = conn.prepareStatement("select * from perfil where id_perfil=?");
            ps2.setInt(1, perfil);

            rs2 = ps2.executeQuery();

            if (rs2.next()) {

                String descricao = rs2.getString(2);

                System.out.println(descricao);

                return descricao;

            }
        } else {
            System.out.println("Senha e/ou Usuário incorreto! Tente Novamente</a>");
        }
        return null;
    }

    /**
     * encerra o servico e muda o status de todas as solicitacoes
     *
     * @param servico
     * @throws SQLException
     */
    public void encerraServico(Servico servico) throws SQLException {

        servico.setStatus(0);
        servico.setDtEncerramento(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        update(servico);
        String sql = "UPDATE solicitacoes SET em_chamado=4 WHERE  servico_id_servico= " + servico.getCell(0).getValue();

        stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();

    }

    /**
     * Lista chamados realizados, concluidos e o total
     *
     * @param servico
     * @return ChamadoAtendidos
     * @throws SQLException
     */
    public List<ChamadosAtendidos> contaChamadosAtendidos(int servico) throws SQLException {

        List<ChamadosAtendidos> CAList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `solicitacoes` WHERE servico_id_servico =? ");

        ps.setInt(1, servico);
        ChamadosAtendidos ch = new ChamadosAtendidos();
        int contador1 = 0, contador2 = 0, contador3 = 0;

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contador1++;
        }
        PreparedStatement ps2 = conn.prepareStatement("SELECT COUNT(`id_solicitacao`)  FROM `solicitacoes` WHERE servico_id_servico =? and em_chamado= 4 ");
        ps2.setInt(1, servico);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            System.out.println(rs2.getInt(1));
            contador2 = rs2.getInt(1);
        }
        PreparedStatement ps3 = conn.prepareStatement("SELECT  SUM(Qtde_tentativas) FROM `solicitacoes` WHERE servico_id_servico=?");
        ps3.setInt(1, servico);
        ResultSet rs3 = ps3.executeQuery();
        while (rs3.next()) {
            System.out.println(rs3.getInt(1));
            contador3 = rs3.getInt(1);
        }

        ch.setTotalDeChamados(contador1);
        ch.setChamadosConcluidos(contador2);
        ch.setChamadosRealizados(contador3 + contador2);
        CAList.add(ch);
        return CAList;
    }

    public List<ChamadoeRespostas> ChamadosERespostas(int servico) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(perguntas.id_perguntas) from perguntas WHERE perguntas.servico_id_servico=?");
        ps.setInt(1, servico);
        int qtdPerguntas = 0;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            qtdPerguntas = rs.getInt(1);
        }
        List<ChamadoeRespostas> CAList = new ArrayList<>();
        PreparedStatement ps2 = conn.prepareStatement("SELECT DISTINCT(chamado.id_chamado),\n"
                + "pid.cod_pid,\n"
                + "pid.nome_estabelecimento,\n"
                + " chamado.observacao,\n"
                + " respostas.id_Respostas,\n"
                + " respostas.Resposta FROM pid\n"
                + " INNER JOIN solicitacoes  on (pid.cod_pid = solicitacoes.PID_cod_pid)\n"
                + " INNER JOIN servico  on	(solicitacoes.Servico_id_servico=servico.id_servico)\n"
                + " INNER JOIN   chamado  on (solicitacoes.id_solicitacao=chamado.Solicitacoes_id_solicitacao)\n"
                + " INNER JOIN  respostas  on (chamado.id_chamado= respostas.chamado_cod_chamado)\n"
                + " INNER JOIN  contato  on (pid.cod_pid=contato.PID_cod_pid)\n"
                + " \n"
                + " WHERE   servico.id_servico=?");

        System.out.println(ps2.toString());
        ps2.setInt(1, servico);
        int i = 0;
        int ii = 0;
        ResultSet rs2 = ps2.executeQuery();
        List<String> list = new ArrayList<>();
        while (rs2.next()) {
            ChamadoeRespostas cr = new ChamadoeRespostas();
            cr.setPid(rs2.getString("cod_pid"));
            cr.setEstabelecimento(rs2.getString(3));
            cr.setObs(rs2.getString("observacao"));

            list.add(rs2.getString(6));
            i++;
            if (i % qtdPerguntas == 0) {
                ii++;
                System.out.println(i);

                cr.setRepostas(list);

                System.out.println(cr.toString());
                System.out.println("LISTA: " + list.toString());
                System.out.println("TAMANHO DA LISTA " + list.size());

                CAList.add(cr);
                list = new ArrayList<>();
            }

        }
        System.out.println("tamanho do list" + ii);
        System.out.println("tamanho do list" + CAList.size());
        System.out.println("TO STRING " + CAList.toString());
        return CAList;
    }

    public List<String> getTitulosList(int servico) throws SQLException {
        List<String> titulos = new ArrayList<String>();
        PreparedStatement ps = conn.prepareStatement("SELECT pergunta from perguntas WHERE perguntas.servico_id_servico=?");
        ps.setInt(1, servico);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("pergunta").length() > 30) {
                titulos.add(rs.getString("pergunta").substring(0, 20)+"...");
            } else {
                titulos.add(rs.getString("pergunta"));
            }
        }

        return titulos;
    }

    public List<RelatorioUsuario> RelatorioUsuario(String nome) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<RelatorioUsuario> rus = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("Select servico.id_servico,  \n"
                + "pid.cod_pid, \n"
                + "        chamado.id_chamado, \n"
                + "        chamado.dt_chamado_aberto, log_chamado.duracao, \n"
                + "        chamado.observacao \n"
                + "        from usuario \n"
                + "        INNER JOIN chamado on(usuario.id_usuario = chamado.Usuario_cod_usuario)\n"
                + "        INNER JOIN solicitacoes on (chamado.Solicitacoes_id_solicitacao = solicitacoes.id_solicitacao        )\n"
                + "   INNER JOIN servico on (servico.id_servico = solicitacoes.servico_id_servico        )\n"
                + "  INNER JOIN pid on (solicitacoes.PID_cod_pid = pid.cod_pid       )\n"
                + "   INNER JOIN log_chamado on (chamado.id_chamado = log_chamado.chamado_id_chamado)where usuario.nome=?");
        ps.setString(1, nome);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            RelatorioUsuario ru = new RelatorioUsuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            rus.add(ru);
        }
        return rus;
    }

}
