package br.com.minicom.scr.entity;


import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import  br.com.minicom.scr.persistence.Entity;

public class Perfil implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "perfil";
	private final String[] COLUMNNAMES = {"id_perfil", "descricao_perfil", "realizar_chamado", "gerenciar_usuarios", "gerenciar_servicos"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public Perfil() {
		values[0] = new Cell(true, true,Type.NUM, 0, true);
		values[1] = new Cell(Type.STR, null, true);
		values[2] = new Cell(Type.NUM, 0, true);
		values[3] = new Cell(Type.NUM, 0, true);
		values[4] = new Cell(Type.NUM, 0, true);
	}

	public void setIdPerfil(int idPerfil) {
		this.values[0].setValue(idPerfil);
	}
	public void setDescricaoPerfil(String descricaoPerfil) {
		this.values[1].setValue(descricaoPerfil);
	}
	public void setRealizarChamado(int realizarChamado) {
		this.values[2].setValue(realizarChamado);
	}
	public void setGerenciarUsuarios(int gerenciarUsuarios) {
		this.values[3].setValue(gerenciarUsuarios);
	}
	public void setGerenciarServicos(int gerenciarServicos) {
		this.values[4].setValue(gerenciarServicos);
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