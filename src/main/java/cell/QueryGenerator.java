/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cell;

/**
 *
 * @author murilo
 */
public interface QueryGenerator<Entity> {
    public String insertGenerator(Entity e);
    public String deleteGenerator(Entity e);
    public String updateGenerator(Entity e);
    public String selectGenerator(Entity e);
}
