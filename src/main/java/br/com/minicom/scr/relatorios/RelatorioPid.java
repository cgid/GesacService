/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.relatorios;

import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edilson Jr
 */
public class RelatorioPid implements Entity {

    private final String DB = "SisCentralRel";

    List<String> columNames = new ArrayList();

    Cell[] values = new Cell[this.columNames.size()];

    public RelatorioPid(List<String> columNames) {
        this.columNames = columNames;

        values = new Cell[this.columNames.size()];
        for (int i = 0; i < columNames.size(); i++) {

            values[i] = new Cell(Type.STR, null, false);

        }
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
        return columNames.size();
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cell getCell(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= this.columNames.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        return values[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getNumOfColumns(); i++) {
            sb.append(" <td>");
            sb.append(this.values[i].getValue()).append("</td>");
        }
        return sb.toString();
    }

    @Override
    public void setCell(int index, Object v) throws ArrayIndexOutOfBoundsException {
//        System.out.println("CEll length " + values.length);
//        System.out.println("INDEX " + index);
//        System.out.println("VALOR " + v.toString());
//        System.out.println("TAMANHO DO COLUM " + this.columNames.size());
        if (index >= this.columNames.size() || index < 0) {
//            System.out.println("INDEX " + index);
//            System.out.println("TAMANHO DO COLUM " + this.columNames.size());
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        this.values[index].setValue(v);
    }
}
