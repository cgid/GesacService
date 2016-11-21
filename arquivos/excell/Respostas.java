package br.com.minicom.scr.entity;


import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import  br.com.minicom.scr.persistence.Entity;

public class Respostas implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "respostas";
	private final String[] COLUMNNAMES = {"id_Respostas", "Resposta", "chamado_cod_chamado"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public Respostas() {
		values[0] = new Cell(true, true,Type.NUM, 0, true);
		values[1] = new Cell(Type.STR, null, false);
		values[2] = new Cell(Type.NUM, 0, true);
	}

	public void setIdRespostas(int idRespostas) {
		this.values[0].setValue(idRespostas);
	}
	public void setResposta(String Resposta) {
		this.values[1].setValue(Resposta);
	}
	public void setCodChamado(int codChamado) {
		this.values[2].setValue(codChamado);
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