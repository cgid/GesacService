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
public class Respostas implements Entity{
    private final String DB = "SisCentralRel";
    private final String TABLE = "Respostas";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final int NUMCOMLUMNS = 3;
    private final String[] COLUMNNAMES = {"id_Respostas",
                                            "Resposta",
                                            "chamado_cod_chamado"};
    
    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Respostas() {
        this.values[0] = new Cell(HAVEID, IID, Type.NUM, null, true);
        this.values[1] = new Cell(Type.STR, null, false);
        this.values[2] = new Cell(Type.NUM, null, true);
    }
    
    public void setIdRespostas( int idRespostas) {
        this.values[0].setValue(idRespostas);
    }
    
    public void setRespostas(String respostas) {
        this.values[1].setValue(respostas);
    }
    
    public void setCodChamado(int codChamado) {
        this.values[2].setValue(codChamado);
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
