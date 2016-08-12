/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;


import br.com.minicom.scr.entity.Contato;
import br.com.minicom.scr.entity.PID;
import br.com.minicom.scr.persistence.querygen.QueryGenerator;
import br.com.minicom.scr.persistence.querygen.SimpleQueryGenerator;


/**
 *
 * @author murilo
 */
public class QueryDemo {
    public static void main(String[] args) {
        Queries q = new SimpleQueries();
        QueryGenerator qg = new SimpleQueryGenerator();
        PID pid = new PID();
        Contato c = new Contato();
        
        c.setCodPid(4001);
        c.setNome("Murilo");
        pid.setCodPid(4001);
        pid.setNomeEstabelecimento("Estabelecimento");
        
        System.out.println(c.toString());
        System.out.println(pid.toString());
        
        System.out.println(qg.insertGenerator(pid));
        //System.out.println(qg.insertGenerator(c));
        
        //q.insert(pid);
        //q.insert(c);
    }
}
