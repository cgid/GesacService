package entity;

/**
 *
 * @author murilo
 */
public class Chamado implements EntityModifiable {

    private final String DB = "SisCentralRel";
    private final String TABLE = "chamado";
    private final boolean AUTOINCREMENTID = true;
    private final int NUMCOMLUMNS = 5;
    private final String[] COLUMNNAMES = {"cod_chamado", "dt_chamado", "observacao", "Usuario_cod_usuario", "Solicitacoes_id_solicitacao"};

    private Object[] values = new Object[this.NUMCOMLUMNS];
    
    private final boolean INSERTABLE;
    private final boolean DELETABLE;
    private final boolean SELECTABLE;
    private final boolean UPGRADEABLE;

    /**
     * @contructor = null.
     */
    public Chamado() {
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = null;
        }
        this.INSERTABLE = false;
        this.DELETABLE = false;
        this.SELECTABLE = false;
        this.UPGRADEABLE = false;
    }

    /**
     * @constructor used to delete and select an specific entity in the
     * database.
     * @param ID
     */
    public Chamado(int ID) {
        this.values[0] = ID;
        this.values[1] = null;
        this.INSERTABLE = false;
        this.DELETABLE = true;
        this.SELECTABLE = true;
        this.UPGRADEABLE = false;

    }

    /**
     * @constructor used to insert an entity in the database.
     * @param date
     * @param obs
     * @param codUsuario
     * @param solicitacoes
     */
    public Chamado(String date, String obs, int codUsuario, int solicitacoes) {
        this.values[0] = null;
        this.values[1] = date;
        this.values[2] = obs;
        this.values[3] = codUsuario;
        this.values[4] = solicitacoes;
        this.INSERTABLE = haveAutoIncrementID();
        this.DELETABLE = false;
        this.SELECTABLE = false;
        this.UPGRADEABLE = false;

    }

    /**
     * @constructor used to update an entity in the database and delete an
     * entity if the id of this table is not auto incremented.
     * The same applies to insert.
     * @param ID
     * @param date
     * @param obs
     * @param codUsuario
     * @param solicitacoes
     */
    public Chamado(int ID, String date, String obs, int codUsuario, int solicitacoes) {
        values[0] = ID;
        values[1] = date;
        values[2] = obs;
        values[3] = codUsuario;
        values[4] = solicitacoes;
        this.INSERTABLE = !haveAutoIncrementID();
        this.DELETABLE = false;
        this.SELECTABLE = false;
        this.UPGRADEABLE = !haveAutoIncrementID();
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
        if (index > 1 || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return this.COLUMNNAMES[index];
    }

    @Override
    public Object getValue(int index) throws ArrayIndexOutOfBoundsException {
        if (index > 1 || index < 0) 
            throw new ArrayIndexOutOfBoundsException("Valor inserido esta fora do intervalo.");
        return values[index];
    }

    @Override
    public boolean isInsertable() {
        return this.INSERTABLE;
    }

    @Override
    public boolean isDeletable() {
        return this.DELETABLE;
    }

    @Override
    public boolean isSelectable() {
        return this.SELECTABLE;
    }

    @Override
    public boolean isUpgradeable() {
        return this.UPGRADEABLE;
    }
}
