/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.entity.exceptions.NotIsDeletableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsInsertableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsSelectableEntityException;
import br.com.minicom.scr.entity.exceptions.NotIsUpgradeableEntityException;
import br.com.minicom.scr.persistence.Entity;

/**
 *
 * @author murilo
 * @param <E>
 */
public interface Queries<E extends Entity> {

    /**
     * @param l
     */
    public void insert(E l) throws NotIsInsertableEntityException;

    /**
     *
     * @param l
     */
    public void delete(E l) throws NotIsDeletableEntityException;

    /**
     * @param l
     */
    public void update(E l) throws NotIsUpgradeableEntityException;

    /**
     * @param e
     * @return 
     */
    public int select(Entity e) throws NotIsSelectableEntityException;

}
