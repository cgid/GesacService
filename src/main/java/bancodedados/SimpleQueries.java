/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import persistencia.ConnectionFactory;
import tabelas.Line;

/**
 *
 * @author murilo
 */
public class SimpleQueries implements Queries<Line> {

    @Override
    public void insert(Line l) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(l.getTableName()).append('(');
        /**
         * @acao adiciona as colunas que receberao valores a query.
         */
        for (int i = l.haveID() ? 1 : 0; i < l.getNumOfColumns(); i++) {
            sql.append(l.getColumnName(i));
            if (i < l.getNumOfColumns() - 1) {
                sql.append(",");
            }
        }
        sql.append(") VALUES(");
        for (int i = l.haveID() ? 1 : 0; i < l.getNumOfColumns(); i++) {
            sql.append("?");
            if (i < l.getNumOfColumns() - 1) {
                sql.append(",");
            }
        }
        sql.append(");");
        System.out.println(sql);
        System.out.println(l.getValue(0));
        System.out.println(l.getValue(1));
        try {
            stmt = conn.prepareStatement(sql.toString());
            for (int i = l.haveID() ? 2 : 1; i <= l.getNumOfColumns(); i++) {
                boolean teste = l.getValue(i - 1).getClass().equals(Integer.class);
                System.out.println(teste);
                if (teste) {
                    stmt.setInt(i, (Integer) l.getValue(i - 1));
                } else {
                    stmt.setString(i, (String) l.getValue(i - 1));
                }
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("deu ruim: " + e);
        }
    }

    @Override
    public void delete(Line l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Line l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void select(Line l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void select(Line l, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
