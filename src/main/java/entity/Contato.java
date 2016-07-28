/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author murilo
 */
public class Contato implements Entity {
    private final String DB = "SisCentralRel";
    private final String TABLE = "chamado";
    private final boolean AUTOINCREMENTID = true;
    private final int NUMCOMLUMNS = 3;
    private final String[] COLUMNNAMES = {"id_contato", "nome", "gesac_cod_gesac"};

    private Object[] values = new Object[this.NUMCOMLUMNS];
    
    /**
     * @contrutor nulo.
     */
    public Contato() {
        for (int i = 0; i < values.length; i++) {
            values[i] = null;
        }
    }
        
    /**
     * @contrutor de consulta.
     * @param ID 
     */
    public Contato(int ID) {
        values[0] = ID;
    }
    
    /**
     * @construtor usado inserir um registo no banco de dados.
     * @param nome
     * @param codGesac 
     */
    public Contato(String nome, int codGesac) {
        values[1] = nome;
        values[2] = codGesac;
    }
    
    /**
     * @contrutor usado para atualizar um registro no banco de dados.
     * @param ID
     * @param nome
     * @param codGesac 
     */
    public Contato(int ID, String nome, int codGesac) {
        values[0] = ID;
        values[1] = nome;
        values[2] = codGesac;
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
        return this.AUTOINCREMENTID;
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
