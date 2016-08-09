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
 * @author Edilson Jr
 */
public class Contato implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "Contato";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final boolean NOTNULL = true;
    private final int NUMCOMLUMNS = 3;
    private final String[] COLUMNNAMES = {"id_contato", "nome", "PID_cod_pid"};

    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Contato() {
        values[0] = new Cell(HAVEID, IID, Type.NUM, null, NOTNULL);
        values[1] = new Cell(Type.STR, null, NOTNULL);
        values[2] = new Cell(Type.NUM, null, NOTNULL);

    }

    public Contato(int id_contato, String nome, int PID_cod_pid) {
        values[0] = new Cell(HAVEID, IID, Type.NUM, id_contato, NOTNULL);
        values[1] = new Cell(Type.STR, nome, NOTNULL);
        values[2] = new Cell(Type.NUM, PID_cod_pid, NOTNULL);
    }
    
    public void setIdContato(int idContato) {
        this.values[0].setValue(idContato);
    }
    
    public void setNome(String contents) {
        this.values[1].setValue(contents);
    }

    public void setCodPID(int PID) {
        this.values[2].setValue(PID);
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
