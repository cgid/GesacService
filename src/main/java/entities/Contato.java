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
class Contato implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "Contato";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final boolean NOTNULL = true;
    private final int NUMCOMLUMNS = 3;
    private final String[] COLUMNNAMES = {"id_contato", "nome", "PID_cod_pid"};
    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Contato() {
    }

    public Contato(int id_contato, String nome, int PID_cod_pid) {
        values[0] = new Cell(HAVEID, IID, Type.NUM, null, NOTNULL);
        values[1] = new Cell(Type.STR, nome, NOTNULL);
        values[2] = new Cell(Type.NUM, PID_cod_pid, NOTNULL);
    }

    @Override
    public String getDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumOfColumns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cell getCell(int index) throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
