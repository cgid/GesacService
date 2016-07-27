/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas;

/**
 *
 * @author murilo
 */
public class ChamadoTab implements Line {

    private final String DB = "SisCentralRel";
    private final String TABLE = "chamado";
    private final boolean HAVEID = true;
    private final int NUMCOMLUMNS = 5;
    private final String[] COLUMNNAMES = {"cod_chamado", "dt_chamado", "observacao", "Usuario_cod_usuario", "Solicitacoes_id_solicitacao"};

    private Object[] values = new Object[this.NUMCOMLUMNS];

    /**
     * @contrutor nulo.
     */
    public ChamadoTab() {
        for (int i = 0; i < values.length; i++) {
            values[i] = null;
        }
    }

    /**
     * @Construtor Usado para deletar e selecionar um registro no banco de
     * dados.
     * @param ID
     */
    public ChamadoTab(int ID) {
        values[0] = ID;
    }

    /**
     * @construtor usado para inserir um registro no banco de dados.
     * @param date
     * @param obs
     * @param codUsuario
     * @param solicitacoes
     */
    public ChamadoTab(String date, String obs, int codUsuario, int solicitacoes) {
        values[1] = date;
        values[2] = obs;
        values[3] = codUsuario;
        values[4] = solicitacoes;
    }

    /**
     * @construtor usado para atualizar um registro no banco de dados.
     * @param ID
     * @param date
     * @param obs
     * @param codUsuario
     * @param solicitacoes
     */
    public ChamadoTab(int ID, String date, String obs, int codUsuario, int solicitacoes) {
        values[0] = ID;
        values[1] = date;
        values[2] = obs;
        values[3] = codUsuario;
        values[4] = solicitacoes;
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
    public boolean haveID() {
        return this.HAVEID;
    }

    @Override
    public int getNumOfColumns() {
        return this.NUMCOMLUMNS;
    }

    @Override
    public String getColumnName(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        }
        return this.COLUMNNAMES[index];
    }

    @Override
    public Object getValue(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        }
        return values[index];
    }

}
