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
public class PerfilTab implements Line {
    private final String DB = "SisCentralRel";
    private final String TABLE = "Perfil";
    private final boolean HAVEID = true;
    private final int NUMCOMLUMNS = 2;
    private final String[] COLUMNNAMES = {"cod_perfil", "descricao_perfil"};

    private Object[] values = new Object[this.NUMCOMLUMNS];
    
    /**
     * @construtor nulo.
     */
    public PerfilTab() {
        for (int i = 0; i < values.length; i++) 
            values[i] = null;
    }
        
    /**
     * @construtor usado para deletar e selecionar um registro no banco de dados.
     * @param ID 
     */
    public PerfilTab(int ID) {
        values[0] = ID;
    }
    
    /**
     * @construtor usado para inserir um registro no banco de dados.
     * @param descricao 
     */
    public PerfilTab(String descricao) {
        values[1] = descricao;
    }
    
    /**
     * @contrutor usado para atualizar um registro no banco de dados.
     * @param ID
     * @param descricao 
     */
    public PerfilTab(int ID, String descricao) {
        values[0] = ID;
        values[1] = descricao;
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
