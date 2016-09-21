/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.entity;

import br.com.minicom.scr.persistence.query.Queries;
import br.com.minicom.scr.persistence.query.SimpleQueries;

/**
 *
 * @author murilo
 */
public class EntityDemo {

    public static void main(String[] args) {
        Solicitacoes s = new Solicitacoes();
        s.setCodPid(0);
        s.setCodServico(0);
        System.out.println(s.toString());
    }
}
