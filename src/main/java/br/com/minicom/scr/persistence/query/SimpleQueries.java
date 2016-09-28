/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.consultas.ChamadoEnderecos;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

    public SimpleQueries() {
        conn = ConnectionFactory.getConnection();

    }

    /**
     *
     * @param e
     * @throws NotIsInsertableEntityException
     */
    @Override
    public void insert(Entity e) throws NotIsInsertableEntityException {

        for (int i = e.getCell(0).isIterable() ? 1 : 0; i < e.getNumOfColumns(); i++) {
            if (e.getCell(i).isNotNull() && e.getCell(i).getValue().equals(null)) {
                throw new NotIsInsertableEntityException();
            }
        }

        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {

            if (validaInsert(e, (int) e.getCell(0).getValue())) {

                stmt = conn.prepareStatement(qg.insertGenerator(e));
                int col = e.getCell(0).isIterable() ? e.getNumOfColumns() - 1 : e.getNumOfColumns();

                for (int i = 1; i <= col; i++) {
                    if (e.getCell(0).isIterable()) {
                        if (e.getCell(i).getType().equals(Type.NUM)) {
                            stmt.setInt(i, e.getCell(i).getValue() == null ? 0 : (int) e.getCell(i).getValue());
                        } else {
                            stmt.setString(i, String.valueOf(e.getCell(i).getValue()));
                        }
                    } else if (e.getCell(i - 1).getType().equals(Type.NUM)) {
                        stmt.setInt(i, e.getCell(i - 1).getValue() == null ? 0 : (int) e.getCell(i - 1).getValue());
                    } else {
                        stmt.setString(i, String.valueOf(e.getCell(i - 1).getValue()));
                    }
                }
                stmt.executeUpdate();
                System.out.println(e.getCell(0).getValue() + " inserido com sucesso!");
                stmt.close();
            } else {
                System.out.println(e.getTableName() + " valor: " + e.getCell(0).getValue() + " ja inserido");
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException er) {
            System.out.println(er);
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

        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
            stmt = conn.prepareStatement(qg.updateGenerator(e));
            for (int i = 1; i <= e.getNumOfColumns(); i++) {
                if (e.getCell(0).isIterable()) {
                    if (e.getCell(i).getType().equals(Type.NUM)) {
                        stmt.setInt(i, (Integer) e.getCell(i).getValue());
                    } else {
                        stmt.setString(i, String.valueOf(e.getCell(i).getValue()));
                    }
                } else if (e.getCell(i - 1).getType().equals(Type.NUM)) {
                    stmt.setInt(i, (Integer) e.getCell(i - 1).getValue());
                } else {
                    stmt.setString(i, String.valueOf(e.getCell(i - 1).getValue()));
                }
            }
            stmt.executeUpdate();
            System.err.println(qg.updateGenerator(e));
            stmt.close();

        } catch (SQLException | ArrayIndexOutOfBoundsException er) {
            System.out.println(er);
        }
    }

    @Override
    public int select(Entity e) throws NotIsSelectableEntityException {

        int next = 0;
        try {

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from " + e.getTableName() + " order by " + e.getColumnName(0) + " desc limit 1");
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

    public Entity select(Entity e, int id) throws SQLException {

        //SELECT * FROM ENTITY WHERE ID = *;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + e.getTableName() + " WHERE " + e.getColumnName(0) + "= " + id;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                for (int i = 1; i <= e.getNumOfColumns(); i++) {
                    e.setCell(i - 1, rs.getString(i));
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

    public int select(String login, String senha, Entity e) throws SQLException {

        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "SELECT * FROM " + e.getTableName() + " WHERE " + e.getColumnName(2) + "= '" + login + "' and " + e.getColumnName(3) + "= '" + senha + "'";
        try {
            stmt = conn.createStatement();
            System.out.println(sql.toString());;
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

    public List<Entity> selectList() throws SQLException {
        Servico s = new Servico();
        String sql = "SELECT * FROM servico ";
        String clazz = "br.com.minicom.scr.entity." + s.getTableName();
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

    public List<Entity> selectList(Entity e) throws SQLException {

        String sql = "SELECT * FROM  " + e.getTableName();
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

    @Override
    public void close() {
        try {
            System.out.println("conexao fechada");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List getContatos(String servico) {
        Solicitacoes e = chamadoHandler(servico);
        List<ConsultaContatos> consultas = new ArrayList<>();
        String sql = "SELECT DISTINCT\n"
                + "contato.nome,\n"
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
                + "    INNER JOIN servico  on	(solicitacoes.Servico_cod_servico=servico.id_servico)\n"
                + "   \n"
                + "    \n"
                + " WHERE solicitacoes.em_chamado= 3 and servico.id_servico=" + servico + ";";

        stmt = null;
        rs = null;
        System.out.println("GETCONTATOS: " + sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                consultas.add(new ConsultaContatos(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }

            mudaStatus(e);

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;
    }

    /**
     *
     * @param servico
     * @return
     */
    public List getChamado(String servico) {
        Solicitacoes e = chamadoHandler(servico);
        System.err.println(e.toString());
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
                + "    INNER JOIN servico  on	(solicitacoes.Servico_cod_servico=servico.id_servico)\n"
                + "   \n"
                + "    \n"
                + " WHERE solicitacoes.em_chamado= 3 and servico.id_servico=" + servico + ";";

        stmt = null;
        rs = null;
        System.out.println("GETCHAMADO: " + sql);
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
                        rs.getString(9)
                ));

            }

            mudaStatus(e);
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;
    }

    public List<Perguntas> getPerguntas(String servico) {
        Solicitacoes e = chamadoHandler(servico);
        List<Perguntas> perguntas = new ArrayList<>();

        String sql = "Select\n"
                + "    perguntas.Id_Perguntas,\n"
                + "    perguntas.pergunta,\n"
                + "    perguntas.Servico_cod_servico\n"
                + "FROM \n"
                + "	pid \n"
                + "\n"
                + "INNER JOIN solicitacoes on  (pid.cod_pid= solicitacoes.PID_cod_pid)\n"
                + "    INNER JOIN servico  on	(solicitacoes.Servico_cod_servico=servico.id_servico)\n"
                + "    INNER JOIN perguntas on 	(servico.id_servico=perguntas.Servico_cod_servico)\n"
                + " WHERE solicitacoes.em_chamado = 3 and servico.id_servico = " + servico + ";";

        stmt = null;
        rs = null;
        System.out.println("GETPERGUNTAS: " + sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Perguntas p = new Perguntas();

                p.setIdPerguntas(rs.getInt(1));
                p.setCodServico(rs.getInt(3));
                p.setPergunta(rs.getString(2));
                perguntas.add(p);
            }
            mudaStatus(e);

            return perguntas;

        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return perguntas;
    }

    private static boolean validaInsert(Entity e, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "select * from " + e.getTableName() + " where  " + e.getColumnName(0) + "= " + id;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            conn.close();
            return false;
        }
        conn.close();
        return true;

    }

    public Solicitacoes chamadoHandler(String servico) {
        Solicitacoes e = new Solicitacoes();

        String sql = "SELECT * FROM " + e.getTableName() + " WHERE " + e.getColumnName(5) + "= " + servico + " and " + e.getColumnName(3) + "=0 order by " + e.getColumnName(0) + "  limit 1";
        try {
            stmt = conn.createStatement();
            System.err.println("CHAMADOHANDLER: " + sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
               

                for (int i = 1; i <= e.getNumOfColumns(); i++) {

                    e.setCell(i - 1, rs.getString(i));
                   
                }

                sql = "UPDATE solicitacoes SET em_chamado=3 WHERE  " + e.getColumnName(0) + "= " + e.getCell(0).getValue();
                System.err.println("CHAMADOHANDLER: " + sql);
                stmt.executeUpdate(sql);
                rs.close();
                stmt.close();
                return e;
            } else {
             
                sql = "SELECT * FROM " + e.getTableName() + " WHERE " + e.getColumnName(5) + "= " + servico + " and " + e.getColumnName(3) + "=1 order by " + e.getColumnName(0) + "  limit 1";
                stmt = conn.createStatement();
                System.err.println("CHAMADOHANDLER: " + sql);
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    for (int i = 1; i <= e.getNumOfColumns(); i++) {

                        e.setCell(i - 1, rs.getString(i));

                    }
                }
                sql = "UPDATE solicitacoes SET em_chamado=3 WHERE  " + e.getColumnName(0) + "= " + e.getCell(0).getValue();
                System.err.println("CHAMADOHANDLER: " + sql);
                stmt.executeUpdate(sql);
                rs.close();
                stmt.close();
                return e;
            }
        } catch (Exception f) {
            System.out.println(f);
        }
        return e;
    }

    public void mudaStatus(Solicitacoes e) throws SQLException {
        String sql = "UPDATE solicitacoes SET em_chamado=1 WHERE  " + e.getColumnName(0) + "= " + e.getCell(0).getValue();
        System.err.println("MUDASTATUS: " + sql);
        stmt.executeUpdate(sql);
        stmt.close();
        rs.close();
    }
}
