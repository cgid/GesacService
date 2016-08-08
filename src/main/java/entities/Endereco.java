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
public class Endereco implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "PID";
    private final boolean HAVEID = true;
    private final boolean IID = true;
    private final int NUMCOMLUMNS = 10;
    private final String[] COLUMNNAMES = {"id_endereco", "descricao", "numero", "bairro", "cep", "complemento", "Municipio_cod_municipio", "PID_cod_pid", "valido", "dt_atualizacao"};

    private Cell[] values = new Cell[this.NUMCOMLUMNS];

    public Endereco() {
    }

    public Endereco(int id_endereco, String descricao, String numero, String bairro, String cep, String complemento, int municipio, int PID_cod_pid, int valido, String dt_atualizacao) {
        values[0] = new Cell(HAVEID, IID, Type.NUM, id_endereco, true);
        values[1] = new Cell(Type.STR, descricao, false);
        values[2] = new Cell(Type.STR, numero, false);
        values[3] = new Cell(Type.STR, bairro, false);
        values[4] = new Cell(Type.STR, cep, false);
        values[5] = new Cell(Type.STR, complemento, false);
        values[6] = new Cell(Type.NUM, municipio, false);
        values[7] = new Cell(Type.NUM, PID_cod_pid, false);
        values[8] = new Cell(Type.NUM, valido, false);
        values[9] = new Cell(Type.STR, dt_atualizacao, false);
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

    public void setCodPID(int PID) {
        this.values[7].setValue(PID);
    }

    public void setDescricao(String descricao) {
        this.values[1].setValue(descricao);
    }

    public void setNumero(String numero) {
        this.values[2].setValue(numero);
    }

    public void setBairro(String contents) {
        this.values[3].setValue(contents);
    }

    public void setCep(String cep) {
        this.values[4].setValue(cep);
    }

    public void setComplemento(String complemento) {
        this.values[5].setValue(complemento);
    }

    public void setMunicipio(int Mun) {
        this.values[6].setValue(Mun);
    }

    public void setPID(int PID) {
        this.values[7].setValue(PID);
    }

    public void setValido(int valido) {
        this.values[8].setValue(valido);
    }

    public void setDataAtualizacao(String atualizacao) {
        this.values[9].setValue(atualizacao);
    }

}
