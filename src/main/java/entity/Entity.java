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
public interface Entity {
    public String getDB();

    public String getTableName();

    public boolean haveAutoIncrementID();

    public int getNumOfColumns();

    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException;
    
    public Object getValue(int index) throws ArrayIndexOutOfBoundsException;
    
    //public Integer getID();
}
