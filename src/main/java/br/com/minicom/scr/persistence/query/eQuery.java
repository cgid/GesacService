/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.entity.exceptions.NotIsDeletableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsInsertableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsUpgradeableEntityException;
import br.com.minicom.scr.persistence.ConnectionFactory;
import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.querygen.QueryGenerator;
import br.com.minicom.scr.persistence.querygen.SimpleQueryGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author murilo
 */
public class eQuery {

    Connection conn;

    public eQuery() {
        conn = ConnectionFactory.getConnection();
    }

    public void close() throws SQLException {
        System.out.println("Fechou");
        conn.close();
    }

    Query<Integer> delete = (e, id) -> {
        if (e.getCell(0).getValue().equals(null)) {
            throw new NotIsDeletableEntityException();
        }

        Statement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(qg.deleteGenerator(e));
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    };

    public Query<Integer> insert = (e, id) -> {
        for (int k = e.getCell(0).isIterable() ? 1 : 0; k < e.getNumOfColumns(); k++) {
            if (e.getCell(k).isNotNull() && e.getCell(k).getValue().equals(null)) {
                throw new NotIsInsertableEntityException();
            }
        }

        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
            stmt = conn.prepareStatement(qg.insertGenerator(e));
            int col = e.getCell(0).isIterable() ? e.getNumOfColumns() - 1 : e.getNumOfColumns();

            for (int k = 1; k <= col; k++) {
                if (e.getCell(0).isIterable()) {
                    if (e.getCell(k).getType().equals(Type.NUM)) {
                        stmt.setInt(k, e.getCell(k).getValue() == null ? 0 : (int) e.getCell(k).getValue());
                    } else {
                        stmt.setString(k, String.valueOf(e.getCell(k).getValue()));
                    }
                } else if (e.getCell(k - 1).getType().equals(Type.NUM)) {
                    stmt.setInt(k, e.getCell(k - 1).getValue() == null ? 0 : (int) e.getCell(k - 1).getValue());
                } else {
                    stmt.setString(k, String.valueOf(e.getCell(k - 1).getValue()));
                }
            }
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    };

    public Query<Integer> update = (e, id) -> {
        if (!e.getCell(0).isIterable() && e.getCell(0).getValue().equals(null)) {
            throw new NotIsUpgradeableEntityException();
        }

        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
        } catch (ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    };

    public Query<Integer> selectID = (e, id) -> {
      
        Statement stmt = null;
        ResultSet rs = null;
        int next = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from " + e.getTableName() + " order by " + e.getColumnName(0) + " desc limit 1");
            while (rs.next()) {
                next = rs.getInt(1);
            }
            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return next;
    };

    public Query<List<Entity>> selectList = (e, id) -> {

        String sql = "SELECT * FROM " + e.getTableName();
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
                for (int k = 1; k <= aux.getNumOfColumns(); k++) {
                    aux.setCell(k - 1, rs.getString(k));
                }
                l.add(aux);
            }

            stmt.close();
            rs.close();

        } catch (SQLException | ArrayIndexOutOfBoundsException |
                ClassNotFoundException | InstantiationException |
                IllegalAccessException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    };

    public Query<Entity> selectEntity = (e, id) -> {

        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " + e.getTableName() + " WHERE " + e.getColumnName(0) + "= " + id[0];
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                for (int i = 1; i <= e.getNumOfColumns(); i++) {
                    e.setCell(i, rs.getString(i));
                }
            }
            stmt.close();
            rs.close();

            return e;
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    };
}
