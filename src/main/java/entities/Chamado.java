/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import cell.Cell;
import cell.Type;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import persistence.Entity;

/**
 *
 * @author murilo
 */
public class Chamado implements Entity {
    private final String DB = "SisCentralRel";
    private final String TABLE = "chamado";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final int NUMCOMLUMNS = 5;
    private final String[] COLUMNNAMES = {"id_chamado", "dt_chamado", "observacao", "Usuario_cod_usuario", "Solicitacoes_id_solicitacao"};

    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Chamado() {
        values[0] = new Cell(HAVEID, IID, Type.NUM, null, true);
        values[1] = new Cell(Type.DATE, null, true);
        values[2] = new Cell(Type.STR, null, false);
        values[3] = new Cell(Type.NUM, null, true);
        values[4] = new Cell(Type.NUM, null, true);
    }

    public void setIdChamado(int val) {
        this.values[0].setValue(val);
    }

    public void setDtChamado(String date) {
        this.values[1].setValue(date);
    }

    public void setObservacao(String obs) {
        this.values[2].setValue(obs);
    }

    public void setCodusuario(int codUsuario) {
        this.values[3].setValue(codUsuario);
    }

    public void setIdSolicitacoes(int idSolicitacoes) {
        this.values[4].setValue(idSolicitacoes);
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
