/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.consultas.ChamadoEnderecos;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
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
    ResultSet rs = null;
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
            System.out.println(e.getCell(0).getValue() + " inserido com sucesso!");

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
                        System.err.println("GENERATOR: " + e.getCell(i).getValue());
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
            System.err.println(qg.updateGenerator(e));
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

            System.out.println(sql);
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

        String sql = "SELECT * FROM  " + e.getTableName() + " order by " + e.getColumnName(0) + " desc";

        String clazz = "br.com.minicom.scr.entity." + e.getTableName();
        Statement stmt = null;
        ResultSet rs = null;
        List<Entity> l = new ArrayList<>();
        Entity aux;
        try {
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

    public List<Entity> selectList(int teste, String entidade) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String clazz = "br.com.minicom.scr.entity." + entidade;
        Entity e = (Entity) Class.forName(clazz).newInstance();
        String sql = "SELECT * FROM  " + entidade + " order by " + e.getColumnName(0) + " desc";

        Statement stmt = null;
        ResultSet rs = null;
        List<Entity> l = new ArrayList<>();
        Entity aux;
        try {
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

    @Override
    public void close() {
        try {
            System.out.println("conexao fechada");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
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

        System.out.println("GETCONTATOS: sql 2" + sql.substring(sql.length() - 63));
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
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("GETCHAMADO: " + sql.substring(sql.length() - 63));
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
        System.out.println("GETPERGUNTAS:  WHERE servico_id_servico = " + servico + ";");
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
        System.out.println(sql);
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

        System.out.println(servico.toString());
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
        PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM `solicitacoes` WHERE servico_id_servico =? and em_chamado= 4 ");
        ps2.setInt(1, servico);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            contador2++;
        }
        PreparedStatement ps3 = conn.prepareStatement("SELECT  Qtde_tentativas FROM `solicitacoes` WHERE servico_id_servico=?");
        ps3.setInt(1, servico);
        ResultSet rs3 = ps3.executeQuery();
        while (rs3.next()) {

            contador3=contador3+rs3.getInt(1);
        }
        PreparedStatement ps4 = conn.prepareStatement("SELECT * FROM `solicitacoes` WHERE servico_id_servico =? and em_chamado= 2 ");
        ps4.setInt(1, servico);

        ResultSet rs4 = ps4.executeQuery();
        while (rs4.next()) {

            contador3++;
        }
        ch.setTotalDeChamados(contador1);
        ch.setChamadosConcluidos(contador2);
        ch.setChamadosRealizados(contador3 + contador2);
        CAList.add(ch);
        return CAList;
    }

    public List<String> getTitulosList(List<String> atributos, List<String> Joins, List<String> contadores, String where) throws SQLException {
        List<String> titulos = new ArrayList();

        for (Iterator<String> iterator = atributos.iterator(); iterator.hasNext();) {
            String next = iterator.next();

            if (next.contains(".")) {
                next = next.substring(next.indexOf(".") + 1, next.length());

            }
            if (next.contains("_")) {
                next = next.replaceAll("_", " de ");
            }

            titulos.add(next);
        }
        if (contadores != null) {
            for (Iterator<String> iterator = contadores.iterator(); iterator.hasNext();) {
                String next = iterator.next();
                titulos.add(next);

            }
        }
        return titulos;
    }

    public List<RelatorioPid> Relatorios(List<String> atributos, List<String> Joins, List<String> contadores, String where, String pesquisa) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<RelatorioPid> relatorioPids = new ArrayList<>();
        List<String> columNames = new ArrayList();
        String sql = "Select DISTINCT ";
        int size = 0;
        stmt = null;
        rs = null;

        for (int i = 0; i < atributos.size() - 1; i++) {
            sql = sql.concat("\n" + atributos.get(i) + ",\n");
            size++;
            columNames.add(atributos.get(i + 1));
        }
//        for (int i = 0; i < contadores.size() - 1; i++) {
//            sql = sql.concat("\n" + atributos.get(i) + ",\n");
//            size++;
//            columNames.add(atributos.get(i + 1));
//        }
//        if (contadores != null) {
//            for (Iterator<String> iterator = contadores.iterator(); iterator.hasNext();) {
//
//                String sqlc = "Select * from ";
//                sqlc = sqlc.concat(iterator.next());
//                Entity aux = (Entity) Class.forName(iterator.next()).newInstance();
//                if (pesquisa.equals("usuario")) {
//                    if (contadores.contains("invalidos")) {
//                        sqlc = sqlc.concat(" INNER JOIN contato  on 	(usuario.id_usuario = contato.usuario_id_usuario)/n ");
//                        sqlc = sqlc.concat(" INNER JOIN telefone  on 	(contato.id_contato = telefone.Contato_id_contato)/n ");
//
//                    }
//                }
//                sql = sql.concat(where);
//
//            }
//        }

        sql = sql.concat("\n" + atributos.get(atributos.size() - 1) + "\n");
        sql = sql.concat("from " + pesquisa);
        columNames.add(atributos.get(atributos.size() - 1));
        for (Iterator<String> iterator = Joins.iterator(); iterator.hasNext();) {
            sql = sql.concat("\n" + iterator.next() + "\n");

        }

        sql = sql.concat(where);
        System.out.println(sql);
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        size = size + 1;
        while (rs.next()) {

//            
            RelatorioPid rp = new RelatorioPid(columNames);
            for (int o = 0; o < size; o++) {

                rp.setCell(o, rs.getString(o + 1));

            }
            relatorioPids.add(rp);

        }

        return relatorioPids;
    }

}
