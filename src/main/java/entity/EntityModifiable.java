/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author murilo
 */
public interface EntityModifiable extends Entity {
    public boolean isInsertable();
    public boolean isDeletable();
    public boolean isSelectable();
    public boolean isUpgradeable();
}
