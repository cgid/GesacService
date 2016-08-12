package entity;


import cell.Cell;
import cell.Type;
import persistence.Entity;

public class Endereco_novo implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "Endereco_novo";
	private final String[] COLUMNNAMES = {"id_endereco", "descricao", "numero", "bairro", "cep", "complemento", "Municipio_cod_IBGE", "PID_cod_pid", "valido"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public Endereco_novo() {
		values[0] = new Cell(true, true,Type.NUM, 0, true);
		values[1] = new Cell(Type.STR, null, false);
		values[2] = new Cell(Type.STR, null, false);
		values[3] = new Cell(Type.STR, null, false);
		values[4] = new Cell(Type.STR, null, false);
		values[5] = new Cell(Type.STR, null, false);
		values[6] = new Cell(Type.NUM, 0, true);
		values[7] = new Cell(Type.NUM, 0, true);
		values[8] = new Cell(Type.NUM, 0, false);
	}

	public void setIdEndereco(int idEndereco) {
		this.values[0].setValue(idEndereco);
	}
	public void setDescricao(String descricao) {
		this.values[1].setValue(descricao);
	}
	public void setNumero(String numero) {
		this.values[2].setValue(numero);
	}
	public void setBairro(String bairro) {
		this.values[3].setValue(bairro);
	}
	public void setCep(String cep) {
		this.values[4].setValue(cep);
	}
	public void setComplemento(String complemento) {
		this.values[5].setValue(complemento);
	}
	public void setCodIBGE(int codIBGE) {
		this.values[6].setValue(codIBGE);
	}
	public void setCodPid(int codPid) {
		this.values[7].setValue(codPid);
	}
	public void setValido(int valido) {
		this.values[8].setValue(valido);
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