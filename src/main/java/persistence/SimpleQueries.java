/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.NotIsUpgradeableEntityException;
import entity.NotIsInsertableEntityException;
import entity.NotIsSelectableEntityException;
import entity.NotIsDeletableEntityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import persistencia.ConnectionFactory;
import java.sql.SQLException;
import entity.Entity;
import java.sql.Statement;

/**
 *
 * @author murilo
 */
public class SimpleQueries implements Queries<Entity> {
    @Override
    public void insert(Entity e) throws NotIsInsertableEntityException{
        if(true)//temporariamente
            throw new NotIsInsertableEntityException();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();
        try {
            stmt = conn.prepareStatement(qg.insertGenerator(e));
            for (int i = 1; i <= e.getNumOfColumns(); i++) {
                if(e.haveAutoIncrementID()) {
                    if (e.getValue(i).getClass().equals(Integer.class)) 
                        stmt.setInt(i, (Integer) e.getValue(i));
                    else 
                        stmt.setString(i, (String) e.getValue(i));
                }
                else {
                     if (e.getValue(i - 1).getClass().equals(Integer.class)) 
                        stmt.setInt(i, (Integer) e.getValue(i - 1));
                    else 
                        stmt.setString(i, (String) e.getValue(i - 1));
                }
            }
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException | ArrayIndexOutOfBoundsException er) {
            System.out.println(er);
        }
    }

    @Override
    public void delete(Entity e) throws NotIsDeletableEntityException {
        if(true)//temporariamente
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
    public void update(Entity e) throws NotIsUpgradeableEntityException {
        if(true)//temporariamente
            throw new NotIsUpgradeableEntityException();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        QueryGenerator qg = new SimpleQueryGenerator();
        try {
            stmt = conn.prepareStatement(qg.updateGenerator(e));
            for (int i = 1; i <= e.getNumOfColumns(); i++) {
                if(e.haveAutoIncrementID()) {
                    if (e.getValue(i).getClass().equals(Integer.class)) 
                        stmt.setInt(i, (Integer) e.getValue(i));
                    else 
                        stmt.setString(i, (String) e.getValue(i));
                }
                else {
                     if (e.getValue(i - 1).getClass().equals(Integer.class)) 
                        stmt.setInt(i, (Integer) e.getValue(i - 1));
                    else 
                        stmt.setString(i, (String) e.getValue(i - 1));
                }
            }
            stmt.executeUpdate();
        } catch (Exception er) {
            System.out.println(er);
        }
    }

    @Override
    public void select(Entity e) throws NotIsSelectableEntityException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void especificallySelect(Entity l) throws NotIsSelectableEntityException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
