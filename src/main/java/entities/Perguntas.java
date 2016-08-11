package entities;

import cell.Cell;
import cell.Type;
import persistence.Entity;

public class Perguntas implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLENAME = "Perguntas";
    private final String[] COLUMNNAMES = {"id_Perguntas", "pergunta", "Servico_cod_servico"};
    private Cell[] values = new Cell[this.COLUMNNAMES.length];

    public Perguntas() {
        values[0] = new Cell(true, true, Type.NUM, null, true);
        values[1] = new Cell(Type.STR, null, false);
        values[2] = new Cell(Type.NUM, null, true);
    }

    public void setIdPerguntas(int idPerguntas) {
        this.values[0].setValue(idPerguntas);
    }

    public void setPergunta(String pergunta) {
        this.values[1].setValue(pergunta);
    }

    public void setCodServico(int codServico) {
        this.values[2].setValue(codServico);
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
}
