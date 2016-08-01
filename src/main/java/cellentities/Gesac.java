/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellentities;

import cell.Cell;
import cell.Type;
import entity.Entity;

/**
 *
 * @author murilo
 */
public class Gesac implements Entity {
    private final String    DB          = "SisCentralRel";
    private final String    TABLE       = "gesac";
    private final boolean   HAVEID      = true;
    private final boolean   IID         = true;
    private final int       NUMCOMLUMNS = 2;
    private final String[]  COLUMNNAMES = {"cod_gesac", "nome_estabelecimento"};

    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Gesac(String nomeEstabelecimento) {
        values[0] = new Cell(HAVEID, IID, Type.NUM, null);
        values[1] = new Cell(Type.STR, nomeEstabelecimento);
    }
    
    public Gesac(int ID, String nomeEstabelecimento) {
        values[0] = new Cell(HAVEID, IID, Type.NUM, ID);
        values[1] = new Cell(Type.STR, nomeEstabelecimento);
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
        return this.IID;
    }

    @Override
    public int getNumOfColumns() {
        return this.NUMCOMLUMNS;
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        if (index > this.NUMCOMLUMNS || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return this.COLUMNNAMES[index];
    }

    @Override
    public Object getValue(int index) throws ArrayIndexOutOfBoundsException {
        if (index > this.NUMCOMLUMNS || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return values[index].value;
    }

    @Override
    public Integer getID() {
        
        return (Integer) values[0].value;
    }
}
