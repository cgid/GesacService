/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.persistence.Entity;

/**
 *
 * @author murilo
 * @param <E>
 */
public interface Query<E> {
    /**
     * 
     * @param e
     * @param id
     * @return 
     */
    public E execute(Entity e, int...id);
}
