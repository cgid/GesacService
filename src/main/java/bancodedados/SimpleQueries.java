/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import entity.NotIsUpgradeableEntityException;
import entity.NotIsInsertableEntityException;
import entity.NotIsSelectableEntityException;
import entity.NotIsDeletableEntityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import persistencia.ConnectionFactory;
import java.sql.SQLException;
import entity.Entity;
import entity.EntityModifiable;
import java.sql.Statement;

/**
 *
 * @author murilo
 */
public class SimpleQueries implements Queries<EntityModifiable> {
    @Override
    public void insert(EntityModifiable e) throws NotIsInsertableEntityException{
        if(!e.isInsertable())
            throw new NotIsInsertableEntityException();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();
        try {
            stmt = conn.prepareStatement(qg.insertGenerator(e));
            for (int i = e.haveAutoIncrementID() ? 2 : 1; i <= e.getNumOfColumns(); i++) {
                if (e.getValue(i - 1).getClass().equals(Integer.class)) 
                    stmt.setInt(i, (Integer) e.getValue(i - 1));
                else 
                    stmt.setString(i, (String) e.getValue(i - 1));
            }
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException | ArrayIndexOutOfBoundsException er) {
            System.out.println(er);
        }
    }

    @Override
    public void delete(EntityModifiable e) throws NotIsDeletableEntityException {
        if(!e.isDeletable())
            throw new NotIsDeletableEntityException();
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(qg.deleteGenerator(e));
            stmt.close();
            conn.close();
        } catch (Exception er) {
            System.out.println(er);
        }
    }

    @Override
    public void update(EntityModifiable e) throws NotIsUpgradeableEntityException {
        if(!e.isUpgradeable())
            throw new NotIsUpgradeableEntityException();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();
        try {
            stmt = conn.prepareStatement(qg.updateGenerator(e));
            int n = e.getNumOfColumns();
            boolean t = e.haveAutoIncrementID();
            for (int i = 1; i < (t ? n - 1 : n); i++) {
                if (e.getValue(i - 1).getClass().equals(Integer.class)) 
                    stmt.setInt(i, (Integer) e.getValue(i - 1));
                else 
                    stmt.setString(i, (String) e.getValue(i - 1));
            }
            stmt.executeUpdate();
        } catch (Exception er) {
        }
    }

    @Override
    public void select(EntityModifiable e) throws NotIsSelectableEntityException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void especificallySelect(Entity l) throws NotIsSelectableEntityException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
