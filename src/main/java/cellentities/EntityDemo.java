/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellentities;

import cell.Entity;
import persistence.Queries;
import persistence.QueryGenerator;
import persistence.SimpleQueries;
import persistence.SimpleQueryGenerator;



/**
 *
 * @author murilo
 */
public class EntityDemo {
    public static void main(String[] args) {
        Entity e = new Gesac(666, "beaba");
        QueryGenerator qg = new SimpleQueryGenerator();
        System.out.println(qg.insertGenerator(e));
        System.out.println(e.getCell(0).toString());
    }
}
