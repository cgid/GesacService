/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence.query;

import br.com.minicom.scr.entity.exceptions.NotIsSelectableEntityException;
import br.com.minicom.scr.persistence.Entity;
import java.util.List;

/**
 *
 * @author murilo
 */
public interface Select {

    /**
     *
     * @param e
     * @return
     */
    public List<Entity> selectList(Entity e);

    /**
     *
     * @param e
     * @param id
     * @return
     */
    public Entity select(Entity e, int id);

    /**
     *
     * @param e
     * @return
     * @throws NotIsSelectableEntityException
     */
    public int select(Entity e) throws NotIsSelectableEntityException;
}
