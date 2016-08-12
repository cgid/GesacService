/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.query;

import entity.exceptions.NotIsUpgradeableEntityException;
import entity.exceptions.NotIsInsertableEntityException;
import entity.exceptions.NotIsSelectableEntityException;
import entity.exceptions.NotIsDeletableEntityException;
import persistence.Entity;

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
     * @param l
     */
    public void select(E l) throws NotIsSelectableEntityException;

    /**
     * @param e
     * @return 
     */
    public int especificallySelect(Entity e) throws NotIsSelectableEntityException;

}
