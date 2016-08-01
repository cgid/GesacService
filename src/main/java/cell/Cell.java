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
    private final boolean id;
    private final boolean notNull;
    private final boolean iterable;
    private final Type type;
    private final Object value;

    public Cell(boolean id, boolean iterable, Type type, Object value, boolean notNull) {
        this.id = id;
        this.iterable = iterable;
        this.type = type;
        this.notNull = notNull;
        this.value = value;
    }
    
    //para cricao de celula que nao sao ID
    public Cell(Type type, Object value, boolean notNull) {
        this.id = false;
        this.iterable = false;
        this.type = type;
        this.notNull = notNull;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID            : ").append(this.id ? "SIM" : "NAO").append('\n');
        sb.append("NOT NULL      : ").append(this.notNull ? "SIM" : "NAO").append('\n');
        sb.append("AUTO INCREMENT: ").append(this.iterable ? "SIM" : "NAO").append('\n');
        sb.append("TIPO          : ").append(this.type).append('\n');
        sb.append("VALOR         : ").append(this.value).append('\n');
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isId() {return this.id;}

    public boolean isNotNull() {return this.notNull;}

    public boolean isIterable() {return this.iterable;}

    public Type getType() {return this.type;}

    public Object getValue() {return this.value;}
}
