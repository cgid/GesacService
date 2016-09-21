/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.persistence;

import br.com.minicom.scr.cell.Cell;

/**
 *
 * @author murilo
 */
public interface Entity {
    
    public String getDB();

    public String getTableName();

    public int getNumOfColumns();

    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException;
    
    public Cell getCell(int index) throws ArrayIndexOutOfBoundsException;
    
    public void setCell(int index, Object v) throws ArrayIndexOutOfBoundsException;
}
