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
public class Telefone implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "PID";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final int NUMCOMLUMNS = 4;
    private final String[] COLUMNNAMES = {"id_telefone",
        "ddd", "telefone",
        "Contato_id_contato"};

    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Telefone() {
    }

    public Telefone(int id_telefone, int ddd, int telefone, int Contato_id_contato) {
        values[0] = new Cell(HAVEID, IID, Type.NUM, id_telefone, true);
        values[1] = new Cell(Type.NUM, ddd, false);
        values[2] = new Cell(Type.NUM, telefone, false);
        values[3] = new Cell(Type.NUM, Contato_id_contato, false);
    }

    public void setDDD(int contents) {
        this.values[1].setValue(contents);
    }

    public void setTelefone(int contents) {
        this.values[2].setValue(contents);
    }

    public void setContato(int contents) {
        this.values[3].setValue(contents);
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
