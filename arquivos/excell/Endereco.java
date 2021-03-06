package br.com.minicom.scr.entity;


import br.com.minicom.scr.cell.Cell;
import br.com.minicom.scr.cell.Type;
import  br.com.minicom.scr.persistence.Entity;

public class Endereco implements Entity {
	private final String DB = "SisCentralRel";
	private final String TABLENAME = "endereco";
	private final String[] COLUMNNAMES = {"id_endereco", "descricao", "numero", "bairro", "cep", "complemento", "Municipio_cod_IBGE", "PID_cod_pid", "valido", "dt_atualizacao"};
	private Cell[] values = new Cell[this.COLUMNNAMES.length];

	public Endereco() {
		values[0] = new Cell(true, true,Type.NUM, 0, true);
		values[1] = new Cell(Type.STR, null, false);
		values[2] = new Cell(Type.STR, null, false);
		values[3] = new Cell(Type.STR, null, false);
		values[4] = new Cell(Type.STR, null, false);
		values[5] = new Cell(Type.STR, null, false);
		values[6] = new Cell(Type.NUM, 0, true);
		values[7] = new Cell(Type.NUM, 0, true);
		values[8] = new Cell(Type.NUM, 0, false);
		values[9] = new Cell(Type.DATE, null, false);
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
	public void setDtAtualizacao(String dtAtualizacao) {
		this.values[9].setValue(dtAtualizacao);
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