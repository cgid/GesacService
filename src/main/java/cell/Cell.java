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
    public boolean id;
    public boolean iterable;
    public Type type;
    public Object value;

    public Cell(boolean id, boolean iterable, Type type, Object value) {
        this.id = id;
        this.iterable = iterable;
        this.type = type;
        if(type.equals(type.NUM))
            this.value = (Integer) value;
        else
            this.value = (String) value;
    }

    public Cell(Type type, Object value) {
        this.id = false;
        this.iterable = false;
        this.type = type;
        if(type.equals(type.NUM))
            this.value = (Integer) value;
        else
            this.value = (String) value;
    }
}
