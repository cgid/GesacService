package br.com.minicom.scr.autoclass;


import br.com.minicom.scr.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author murilo
 */
public class Dados {
        public static String[] getTables() {
        String[] tabelas;
        String showTable = "show tables;";
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = ConnectionFactory.getConnection();
        int i;
        
        try {
            stmt = conn.createStatement();
            tabelas = new String[stmt.executeUpdate(showTable)];
            rs = stmt.executeQuery(showTable);
            
            i  = 0;
            
            while(rs.next()){
                tabelas[i] = rs.getString(1);
                i++;
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            return tabelas;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static String[][] getTableData(String table) {
        String sql = "SHOW FULL COLUMNS from " + table + " ;";
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = ConnectionFactory.getConnection();
        int[] colunas = {1,2,3,4,5,7};
        String[][] dados;
        
        try {
            
            stmt = conn.createStatement();
            dados = new String[stmt.executeUpdate(sql)][colunas.length];
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
                for (int i = 0; i < colunas.length; i++)
                    dados[rs.getRow() - 1][i] = rs.getString(colunas[i]);
            
            return dados;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
