package entity;


import cell.Cell;
import cell.Type;
import persistence.Entity;

public class PID implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "PID";
	private final String[] COLUMNNAMES = {"cod_pid", "cod_gesac", "cod_tc", "cod_cd", "nome_estabelecimento"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public PID() {
		values[0] = new Cell(true, false,Type.NUM, 0, true);
		values[1] = new Cell(Type.NUM, 0, false);
		values[2] = new Cell(Type.NUM, 0, false);
		values[3] = new Cell(Type.NUM, 0, false);
		values[4] = new Cell(Type.STR, null, false);
	}

	public void setCodPid(int codPid) {
		this.values[0].setValue(codPid);
	}
	public void setCodGesac(int codGesac) {
		this.values[1].setValue(codGesac);
	}
	public void setCodTc(int codTc) {
		this.values[2].setValue(codTc);
	}
	public void setCodCd(int codCd) {
		this.values[3].setValue(codCd);
	}
	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.values[4].setValue(nomeEstabelecimento);
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