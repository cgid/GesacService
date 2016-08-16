/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.entity.exceptions.NotIsUpgradeableEntityException;
import br.com.minicom.scr.persistence.Entity;

/**
 *
 * @author murilo
 */
@FunctionalInterface
public interface Update {
    /**
     * 
     * @param e
     * @throws NotIsUpgradeableEntityException 
     */
    public void update(Entity e) throws NotIsUpgradeableEntityException;
}
