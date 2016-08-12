/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author murilo
 */
public class EntityDemo {
    public static void main(String[] args) {
        Object t = null;
        try {
            t = Class.forName("entities.Chamado").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println(e);
        }
        
        System.out.println("Nome da classse: " + t.getClass().toString() + "\n");
        System.out.println("Metodos: ");
        
        for (Method m : t.getClass().getMethods())  
            System.out.println(m.getName() + ", ");
        
        System.out.println("\n");
        
        for (Field  f : t.getClass().getDeclaredFields()) 
            System.out.println(f.getName());
        
    }
}
