package br.com.minicom.scr.entity;


import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import br.com.minicom.scr.persistence.Entity;

public class Municipio implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "Municipio";
	private final String[] COLUMNNAMES = {"cod_IBGE", "nome_municipio", "UF"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public Municipio() {
		values[0] = new Cell(true, false,Type.NUM, 0, true);
		values[1] = new Cell(Type.STR, null, false);
		values[2] = new Cell(Type.STR, null, false);
	}

	public void setCodIBGE(int codIBGE) {
		this.values[0].setValue(codIBGE);
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.values[1].setValue(nomeMunicipio);
	}
	public void setUF(String UF) {
		this.values[2].setValue(UF);
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