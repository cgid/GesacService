package br.com.minicom.scr.entity;


import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import  br.com.minicom.scr.persistence.Entity;

public class Usuario implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "usuario";
	private final String[] COLUMNNAMES = {"id_usuario", "nome", "login", "senha", "Perfil_cod_perfil", "ativo"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public Usuario() {
		values[0] = new Cell(true, true,Type.NUM, 0, true);
		values[1] = new Cell(Type.STR, null, true);
		values[2] = new Cell(Type.STR, null, true);
		values[3] = new Cell(Type.STR, null, true);
		values[4] = new Cell(Type.NUM, 0, true);
		values[5] = new Cell(Type.NUM, 0, true);
	}

	public void setIdUsuario(int idUsuario) {
		this.values[0].setValue(idUsuario);
	}
	public void setNome(String nome) {
		this.values[1].setValue(nome);
	}
	public void setLogin(String login) {
		this.values[2].setValue(login);
	}
	public void setSenha(String senha) {
		this.values[3].setValue(senha);
	}
	public void setCodPerfil(int codPerfil) {
		this.values[4].setValue(codPerfil);
	}
	public void setAtivo(int ativo) {
		this.values[5].setValue(ativo);
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
@Override
    public void setCell(int index, Object v) throws ArrayIndexOutOfBoundsException {
        if (index >= this.COLUMNNAMES.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Indice inserido esta fora do intervalo.");
        }
        this.values[index].setValue(v);
    }}