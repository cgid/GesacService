package entities;


import cell.Cell;
import cell.Type;
import persistence.Entity;

public class Chamado implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "chamado";
	private final String[] COLUMNNAMES = {"id_chamado", "dt_chamado", "observacao", "Usuario_cod_usuario", "Solicitacoes_id_solicitacao"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public Chamado() {
		values[0] = new Cell(true, true,Type.NUM, 0, true);
		values[1] = new Cell(Type.DATE, null, true);
		values[2] = new Cell(Type.STR, null, false);
		values[3] = new Cell(Type.NUM, 0, true);
		values[4] = new Cell(Type.NUM, 0, true);
	}

	public void setIdChamado(int idChamado) {
		this.values[0].setValue(idChamado);
	}
	public void setDtChamado(String dtChamado) {
		this.values[1].setValue(dtChamado);
	}
	public void setObservacao(String observacao) {
		this.values[2].setValue(observacao);
	}
	public void setCodUsuario(int codUsuario) {
		this.values[3].setValue(codUsuario);
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.values[4].setValue(idSolicitacao);
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
}