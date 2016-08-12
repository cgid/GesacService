/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author murilo
 */
public class ConnectionFactory {

    private static final String USER = "root";
    private static final String PASS = "!@#123Asd";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/SisCentralRel?autoReconnect=true&useSSL=false";

    public static Connection getConnection() {
        /**
        Properties props = null;
        FileInputStream in;
        String driver;
        
        try {
            props = new Properties();
            in = new FileInputStream("sid/cgid/gesacservice/db.properties");
            props.load(in);
        } catch (IOException e) {System.out.println(e);}
        
        driver = props.getProperty("jdbc.driver");
        
        try {Class.forName(driver);} 
        catch (ClassNotFoundException ex) {System.out.println(ex);}
        */
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            //Propriedades prop = Propriedades.getInstance();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Nao foi possivel conectar ao banco de dados.");
            System.out.println(e);
        }
        return null;
    }
}
