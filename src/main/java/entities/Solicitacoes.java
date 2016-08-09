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
public class Solicitacoes implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "Solicitacoes";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final int NUMCOMLUMNS = 7;
    private final String[] COLUMNNAMES = {  "id_solicitacao", 
                                            "Qtde_tentativas", 
                                            "Dt_ult_tentativa", 
                                            "em_chamado", 
                                            "contato_ok",
                                            "Servico_cod_servico",
                                            "PID_cod_pid"};

    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Solicitacoes() {
        values[0] = new Cell(HAVEID, IID, Type.NUM, null, true);
        values[1] = new Cell(Type.NUM, null, false);
        values[2] = new Cell(Type.DATE, null, true);
        values[3] = new Cell(Type.NUM, null, false);
        values[4] = new Cell(Type.NUM, null, false);
        values[5] = new Cell(Type.NUM, null, true);
        values[6] = new Cell(Type.NUM, null, true);
    }
    
    public void setIdSolicitacao(int idSolicitacao) {
        this.values[0].setValue(idSolicitacao);
    }
    
    public void setQtdTentativas(int qtdTentativas) {
        this.values[1].setValue(qtdTentativas);
    }
    
    public void setDtUltimaTentativa(String dtUltimaTentativa) {
        this.values[2].setValue(dtUltimaTentativa);
    }
    
    public void setEmChamado(int emChamado) {
        this.values[3].setValue(emChamado);
    }
    
    public void setContatoOK(int contatoOK) {
        this.values[4].setValue(contatoOK);
    }
    
    public void setCodServico(int codServico) {
        this.values[5].setValue(codServico);
    }
    
    public void setCodPID(int codPID) {
        this.values[6].setValue(codPID);
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
