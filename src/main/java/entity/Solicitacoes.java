/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Edilson Jr
 */
public class Solicitacoes implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "Solicitacoes";
    private final boolean HAVEID = true;
    private final int NUMCOMLUMNS = 6;
    private final String[] COLUMNNAMES = {"id_solicitacoes",
        "Qtde_tentativas",
        "descricao",
        "Dt_ult_tentativa",
        "status",
        "contato_ok", "Servico_cod_servico",
        "Gesac_cod_gesac"};

    private Object[] values = new Object[this.NUMCOMLUMNS];

    /**
     * @Construtor vazio
     *
     * @param
     */
    public Solicitacoes() {
        values[0] = null;
        values[1] = null;
    }

    /**
     *
     *
     * @Construtor usado para deletar ou selecionar um registro no Banco de
     * dados.
     * @param
     * @param ID
     */
    public Solicitacoes(int ID) {
        values[0] = ID;
    }

    /**
     * @Contrutor usado para update
     * @param ID
     * @param Qtde_tentativas
     * @param descricao
     * @param Dt_ult_tentativa
     * @param status
     * @param contato_ok
     * @param Servico_cod_servico
     * @param gesac_cod_gesac
     */
    public Solicitacoes(int ID,String Qtde_tentativas, String descricao, String Dt_ult_tentativa, boolean status, boolean contato_ok, int Servico_cod_servico, int gesac_cod_gesac) {
        values[0] = ID;
        values[1] = Qtde_tentativas;
        values[2] = descricao;
        values[3] = Dt_ult_tentativa;
        values[4] = status;
        values[5] = contato_ok;
        values[6] = Servico_cod_servico;
        values[7] = gesac_cod_gesac;
    }

    /**
     * @Construtor usado para inserir registros.
     * @param Qtde_tentativas
     * @param descricao
     * @param Dt_ult_tentativa
     * @param status
     * @param contato_ok
     * @param Servico_cod_servico
     * @param gesac_cod_gesac
     */
    public Solicitacoes(String Qtde_tentativas,String descricao,String Dt_ult_tentativa,boolean status,boolean contato_ok,int Servico_cod_servico,int gesac_cod_gesac) {
        values[1] = Qtde_tentativas;
        values[2] = descricao;
        values[3] = Dt_ult_tentativa;
        values[4] = status;
        values[5] = contato_ok;
        values[6] = Servico_cod_servico;
        values[7] = gesac_cod_gesac;

    }

    @Override
    public Object getValue(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return values[index];
    }

    public void setValue(Object o, int index) {
        this.values[index] = o;
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
    public boolean haveAutoIncrementID() {
        return this.HAVEID;
    }

    @Override
    public int getNumOfColumns() {
        return this.NUMCOMLUMNS;
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return this.COLUMNNAMES[index];

    }


}
