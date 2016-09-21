/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.consultas;

/**
 *
 * @author Edilson Jr
 */
public class ChamadoConsulta {

    String codPid,
            nomeEstabelecimento,
            nome,
            ddd,
            telefone,
            descricao,
            numero,
            bairro,
            complemento,
            nomeMunicipio,
            uf;

    public ChamadoConsulta() {
    }

    public ChamadoConsulta(String codPid, String nomeEstabelecimento, String nome, String ddd, String telefone, String descricao, String numero, String bairro, String complemento, String nomeMunicipio, String uf) {
        this.codPid = codPid;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.nome = nome;
        this.ddd = ddd;
        this.telefone = telefone;
        this.descricao = descricao;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.nomeMunicipio = nomeMunicipio;
        this.uf = uf;
    }

    public void setCodPid(String codPid) {
        this.codPid = codPid;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCodPid() {
        return codPid;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public String getDdd() {
        return ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public String getUf() {
        return uf;
    }


}
