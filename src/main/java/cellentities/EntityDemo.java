/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellentities;

import cell.Entity;
import cellentities.persistence.Queries;
import cellentities.persistence.QueryGenerator;
import cellentities.persistence.SimpleQueries;
import cellentities.persistence.SimpleQueryGenerator;



/**
 *
 * @author murilo
 */
public class EntityDemo {
    public static void main(String[] args) {
        Entity e = new Gesac(666, "beaba");
        QueryGenerator qg = new SimpleQueryGenerator();
        System.out.println(qg.insertGenerator(e));
        System.out.println(e.toString());
        Queries q = new SimpleQueries();
        q.insert(e);
    }
}
