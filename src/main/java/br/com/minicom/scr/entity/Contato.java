package br.com.minicom.scr.entity;

import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.persistence.Entity;

public class Contato implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLENAME = "contato";
    private final String[] COLUMNNAMES = {"id_contato", "nome", "PID_cod_pid"};
    private Cell[] values = new Cell[this.COLUMNNAMES.length];

    public Contato() {
        values[0] = new Cell(true, true, Type.NUM, 0, true);
        values[1] = new Cell(Type.STR, null, false);
        values[2] = new Cell(Type.NUM, 0, true);
    }

    public void setIdContato(int idContato) {
        this.values[0].setValue(idContato);
    }

    public void setNome(String nome) {
        this.values[1].setValue(nome);
    }

    public void setCodPid(int codPid) {
        this.values[2].setValue(codPid);
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

    @Override
    public void setCell(int index, Object v) throws ArrayIndexOutOfBoundsException {
        if (index >= this.COLUMNNAMES.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        this.values[index].setValue(v);
    }
}
