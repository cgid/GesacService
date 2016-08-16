package br.com.minicom.scr.entity;

import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.persistence.Entity;

public class Telefone implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLENAME = "Telefone";
    private final String[] COLUMNNAMES = {"id_telefone", "ddd", "telefone", "Contato_id_contato"};
    private Cell[] values = new Cell[this.COLUMNNAMES.length];

    public Telefone() {
        values[0] = new Cell(true, true, Type.NUM, 0, true);
        values[1] = new Cell(Type.NUM, 0, true);
        values[2] = new Cell(Type.NUM, 0, true);
        values[3] = new Cell(Type.NUM, 0, true);
    }

    public void setIdTelefone(int idTelefone) {
        this.values[0].setValue(idTelefone);
    }

    public void setDdd(int ddd) {
        this.values[1].setValue(ddd);
    }

    public void setTelefone(int telefone) {
        this.values[2].setValue(telefone);
    }

    public void setIdContato(int idContato) {
        this.values[3].setValue(idContato);
    }

    @Override
    public String getDB() {
        return this.DB;
    }

    @Override
    public String getTableName() {
        return this.TABLENAME;
    }

    @Override
    public int getNumOfColumns() {
        return this.COLUMNNAMES.length;
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= this.COLUMNNAMES.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        return this.COLUMNNAMES[index];
    }

    @Override
    public Cell getCell(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= this.COLUMNNAMES.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        return values[index];
    }
    
    @Override
    public void setCell(int index, Object v) throws ArrayIndexOutOfBoundsException {
        if (index >= this.COLUMNNAMES.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        this.values[index].setValue(v);
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
