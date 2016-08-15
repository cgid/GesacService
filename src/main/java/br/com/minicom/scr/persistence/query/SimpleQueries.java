/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.cell.Type;
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


/**
 *
 * @author murilo
 */
public class SimpleQueries implements Queries<Entity> {
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

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
            stmt = conn.prepareStatement(qg.insertGenerator(e));
            int col = e.getCell(0).isIterable() ? e.getNumOfColumns() - 1 : e.getNumOfColumns();

            for (int i = 1; i <= col; i++) {
                if(e.getCell(0).isIterable()) {
                    if (e.getCell(i).getType().equals(Type.NUM)) 
                        stmt.setInt(i, e.getCell(i).getValue() == null ? 0 : (int) e.getCell(i).getValue());
                    else 
                        stmt.setString(i, String.valueOf(e.getCell(i).getValue()));
                }
                else {
                    if (e.getCell(i - 1).getType().equals(Type.NUM)) 
                        stmt.setInt(i, e.getCell(i - 1).getValue() == null ? 0 : (int) e.getCell(i - 1).getValue());
                    else 
                        stmt.setString(i, String.valueOf(e.getCell(i - 1).getValue()));
                }
            }
            stmt.executeUpdate();
            stmt.close();
            conn.close();
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

        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(qg.deleteGenerator(e));
            stmt.close();
            conn.close();
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

        Connection conn = ConnectionFactory.getConnection();
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
        } catch (SQLException | ArrayIndexOutOfBoundsException er) {
            System.out.println(er);
        }
    }

    @Override
    public int select(Entity e) throws NotIsSelectableEntityException {
        Connection conn = ConnectionFactory.getConnection();
        int next = 0;
        try {

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from " + e.getTableName() + " order by "+ e.getColumnName(0)+" limit 1");
            while (resultSet.next()) 
                next = resultSet.getInt(1);
            return next;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
}
