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
public class Cell {
    private DataType t;
    private Object value;

    public Cell(DataType t, Object value) {
        this.t = t;
        this.value = value;
    }
    //Getters...
    public DataType getT() {return t;}
    public Object getValue() {return value;}
    
    //Setters...
    public void setT(DataType t) {this.t = t;}
    public void setValue(Object value) {this.value = value;}
}
