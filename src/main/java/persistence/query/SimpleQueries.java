/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.query;

import entities.exceptions.NotIsUpgradeableEntityException;
import entities.exceptions.NotIsInsertableEntityException;
import entities.exceptions.NotIsSelectableEntityException;
import entities.exceptions.NotIsDeletableEntityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cell.Type;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.ConnectionFactory;
import persistence.Entity;
import persistence.querygen.QueryGenerator;
import persistence.querygen.SimpleQueryGenerator;

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
            int col = e.getNumOfColumns();
            if (e.getCell(0).isIterable()) {
                col = col - 1;
            }
                
            for (int i = 1; i <= col; i++) {
                if (e.getCell(0).isIterable()) {
                    if (e.getCell(i).getType().equals(Type.NUM)) 
                        stmt.setInt(i, e.getCell(i - 1).getValue() == null ? 0 : (Integer) e.getCell(i - 1).getValue());
                    else 
                        stmt.setString(i, String.valueOf(e.getCell(i).getValue()));
                }
                else {
                    if (e.getCell(i - 1).getType().equals(Type.NUM)) 
                        stmt.setInt(i, e.getCell(i - 1).getValue() == null ? 0 : (Integer) e.getCell(i - 1).getValue());
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

    public void select(int id, Entity...e) throws NotIsSelectableEntityException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void select(Entity e) throws NotIsSelectableEntityException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int especificallySelect(Entity e) throws NotIsSelectableEntityException {
        Connection conn = ConnectionFactory.getConnection();
        int next = 0;
        try {

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * e.getTableName() order by desc limit 1");
            while (resultSet.next()) {
                next = resultSet.getInt(1);

            }

            return next;
        } catch (SQLException ex) {
            Logger.getLogger(SimpleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
