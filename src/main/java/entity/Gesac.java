/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import cell.Cell;

/**
 *
 * @author murilo
 */
public class Gesac implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "gesac";
    private final boolean AUTOINCREMENTID = false;
    private final int NUMCOMLUMNS = 2;
    private final String[] COLUMNNAMES = {"cod_gesac", "nome_estabelecimento"};

    private Object[] values = new Object[this.NUMCOMLUMNS];

    public Gesac() {
        for (int i = 0; i < values.length; i++) 
            values[i] = null;
    }

    public Gesac(int ID) {
        values[0] = ID;
        values[1] = null;
    }

    public Gesac(int ID, String nomeComplemento) {
        values[0] = ID;
        values[1] = nomeComplemento;
    }

    public Gesac(String nomeComplemento) {
        values[0] = null;
        values[1] = nomeComplemento;
    }

    public void setValue(Object o, int index) {
        this.values[index] = o;
    }

    @Override
    public String getDB() {
        return this.DB;
    }

    @Override
    public String getTableName() {
        return this.TABLE;
    }

    @Override
    public boolean haveAutoIncrementID() {
        return this.AUTOINCREMENTID;
    }

    @Override
    public int getNumOfColumns() {
        return this.NUMCOMLUMNS;
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return this.COLUMNNAMES[index];
    }

    @Override
    public Object getValue(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return values[index];
    }
}
