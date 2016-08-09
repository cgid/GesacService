/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import cell.Cell;
import cell.Type;
import persistence.Entity;

/**
 *
 * @author murilo
 */
public class Perfil implements Entity {
    private final String DB = "SisCentralRel";
    private final String TABLE = "Perfil";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final int NUMCOMLUMNS = 2;
    private final String[] COLUMNNAMES = {"id_perfil",
                                            "descricao_perfil"};
    
    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Perfil() {
        this.values[0] = new Cell(HAVEID, IID, Type.NUM, null, true);
        this.values[1] = new Cell(Type.STR, null, true);
    }
    
    public void setIdPerfil( int idPerfil) {
        this.values[0].setValue(idPerfil);
    }
    
    public void setDescPerfil(String descPerfil) {
        this.values[1].setValue(descPerfil);
    }
    
    /**
     * Methods of Entity interface.
     */
    @Override
    public String getDB() {
        return this.DB;
    }

    @Override
    public String getTableName() {
        return this.TABLE;
    }

    @Override
    public int getNumOfColumns() {
        return this.NUMCOMLUMNS;
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= this.NUMCOMLUMNS || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        return this.COLUMNNAMES[index];
    }

    @Override
    public Cell getCell(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= this.NUMCOMLUMNS || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        return values[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumOfColumns(); i++) {
            sb.append(getColumnName(i)).append('\t');
        }
        sb.append('\n');
        for (int i = 0; i < getNumOfColumns(); i++) {
            sb.append(this.values[i].getValue()).append('\t');
        }
        return sb.toString();
    }
}
