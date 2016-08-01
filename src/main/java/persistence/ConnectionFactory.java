/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author murilo
 */
public class ConnectionFactory {
    static final String USER = "root";
    static final String PASS = "!@#123Asd";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/SisCentralRel?autoReconnect=true&useSSL=false";

    public static Connection getConnection() {
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
