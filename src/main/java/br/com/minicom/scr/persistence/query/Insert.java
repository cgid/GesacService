/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.entity.exceptions.NotIsInsertableEntityException;

/**
 *
 * @author murilo
 * @param <Entity>
 */
@FunctionalInterface
public interface Insert<Entity> {
    public void Insert(Entity e) throws NotIsInsertableEntityException;
}
