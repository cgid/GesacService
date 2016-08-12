/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excell;


import java.sql.Connection;
import persistence.ConnectionFactory;

/**
 *
 * @author murilo
 */
public class EHandlerDemo {

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
        if (connection!= null) {
            System.out.println("deu bom");
        }
        ExcellHandler eh = new ExcellHandler();
        eh.readSheet();
    }
}
