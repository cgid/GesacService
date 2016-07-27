/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas;

import cell.Cell;

/**
 *
 * @author murilo
 */
public class GesacTab implements Line {

    private final String DB = "SisCentralRel";
    private final String TABLE = "gesac";
    private final boolean HAVEID = false;
    private final int NUMCOMLUMNS = 2;
    private final String[] COLUMNNAMES = {"cod_gesac", "nome_estabelecimento"};

    private Object[] values = new Object[this.NUMCOMLUMNS];

    public GesacTab() {
        for (int i = 0; i < values.length; i++) 
            values[i] = null;
    }

    public GesacTab(int ID) {
        values[0] = ID;
    }

    public GesacTab(int ID, String nomeComplemento) {
        values[0] = ID;
        values[1] = nomeComplemento;
    }

    public GesacTab(String nomeComplemento) {
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
    public boolean haveID() {
        return this.HAVEID;
    }

    @Override
    public int getNumOfColumns() {
        return this.NUMCOMLUMNS;
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        }
        return this.COLUMNNAMES[index];
    }

    @Override
    public Object getValue(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        }
        return values[index];
    }
}
