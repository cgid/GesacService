/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import tabelas.Line;

/**
 *
 * @author murilo
 * @param <L>
 */
public interface Queries<L extends Line>{
    /**
     * Executa uma query para inserir um numa determinada tabela.
     * @param l 
     */
    public void insert(L l);
    /**
     * 
     * @param l 
     */
    public void delete(L l);
    
    /**
     * <p>
     * Executa uma query para atualizar um numa determinada tabela.
     * </p>
     * @param l 
     */
    public void update(L l);
    public void select(L l);
    public void select(L l, int id);
}
