/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;


import br.com.minicom.scr.entity.PID;
import br.com.minicom.scr.persistence.Entity;
import java.util.List;


/**
 *
 * @author murilo
 */
public class QueryDemo {
    public static void main(String[] args) {
        SimpleQueries q = new SimpleQueries();
        PID pid = new PID();
        
        List<Entity> l = q.selectList(new PID());
        
        l.forEach(list -> System.out.println(list));
        
    }
}
