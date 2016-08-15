package br.com.minicom.scr.entity;

import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.persistence.Entity;

public class Servico implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLENAME = "Servico";
    private final String[] COLUMNNAMES = {"id_servico", "dt_criacao_servico", "descricao", "Dt_encerramento", "Intervalo_ligacoes", "Usuario_cod_usuario"};
    private Cell[] values = new Cell[this.COLUMNNAMES.length];

    public Servico() {
        values[0] = new Cell(true, true, Type.NUM, 0, true);
        values[1] = new Cell(Type.DATE, null, true);
        values[2] = new Cell(Type.STR, null, true);
        values[3] = new Cell(Type.DATE, null, false);
        values[4] = new Cell(Type.NUM, 0, false);
        values[5] = new Cell(Type.NUM, 0, true);
    }

    public void setIdServico(int idServico) {
        this.values[0].setValue(idServico);
    }

    public void setCriacaoServico(String criacaoServico) {
        this.values[1].setValue(criacaoServico);
    }

    public void setDescricao(String descricao) {
        this.values[2].setValue(descricao);
    }

    public void setDtEncerramento(String DtEncerramento) {
        this.values[3].setValue(DtEncerramento);
    }

    public void setIntervaloLigacoes(int IntervaloLigacoes) {
        this.values[4].setValue(IntervaloLigacoes);
    }

    public void setCodUsuario(int codUsuario) {
        this.values[5].setValue(codUsuario);
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
