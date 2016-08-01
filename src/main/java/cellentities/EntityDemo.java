/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellentities;

import entity.Entity;
import persistence.Queries;
import persistence.SimpleQueries;

/**
 *
 * @author murilo
 */
public class EntityDemo {
    public static void main(String[] args) {
        Entity e = new Gesac(333, "klkasdasdas");
        System.out.println(e.toString());
        Queries q = new SimpleQueries();
        q.insert(e);
    }
}
