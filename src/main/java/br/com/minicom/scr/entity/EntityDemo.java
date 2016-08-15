/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.entity;

import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.query.Queries;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import sun.java2d.pipe.SpanShapeRenderer;
import sun.text.normalizer.NormalizerImpl;

/**
 *
 * @author murilo
 */
public class EntityDemo {

    public static void main(String[] args) {
//        Object t = null;
//        try {
//            t = Class.forName("entities.Chamado").newInstance();
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            System.err.println(e);
//        }
//        
//        System.out.println("Nome da classse: " + t.getClass().toString() + "\n");
//        System.out.println("Metodos: ");
//        
//        for (Method m : t.getClass().getMethods())  
//            System.out.println(m.getName() + ", ");
//        
//        System.out.println("\n");
//        
//        for (Field  f : t.getClass().getDeclaredFields()) 
//            System.out.println(f.getName());
        Queries q = new SimpleQueries();
        Perfil p = new Perfil();
        p.setDescricaoPerfil("novo");
        q.insert(p);
        Usuario u = new Usuario();

        u.setLogin("teste");
        u.setCodPerfil(q.select(p));
        u.setSenha("teste");
        u.setNome("teste");
        q.insert(u);
    }
}
