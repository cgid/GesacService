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
public class DDD implements Entity {

    private final String DB = "SisCentralRel";
    private final String TABLE = "DDD";
    private final boolean AUTOINCREMENTID = true;
    private final int NUMCOMLUMNS = 2;
    private final String[] COLUMNNAMES = {"id_DDD", "DDD"};

    private Object[] values = new Object[this.NUMCOMLUMNS];

    /**
     * @contrutor nulo.
     */
    public DDD() {
        for (int i = 0; i < values.length; i++) {
            values[i] = null;
        }
    }

    /**
     * @contrutor usado para deletar e consultar um registro no banco de dados.
     * @param ID
     */
    public DDD(int ID) {
        values[0] = ID;
    }

    /**
     * @contrutor usado inserir um resgitro no banco de dados.
     * @param DDD
     */
    public DDD(String DDD) {
        values[1] = DDD;
    }

    /**
     * @contrutor usado para atualizar um registro no banco de dados.
     * @param ID
     * @param DDD
     */
    public DDD(int ID, String DDD) {
        values[0] = ID;
        values[1] = DDD;
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
