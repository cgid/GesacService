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
public class ChamadoEnderecos {

    String codPid,
            nomeEstabelecimento,
            descricao,
            numero,
            bairro,
            complemento,
            nomeMunicipio,
            uf,
            ibge, id_solicitacao;

    public ChamadoEnderecos(String codPid, String nomeEstabelecimento, String descricao, String numero, String bairro, String complemento, String nomeMunicipio, String uf, String ibge) {
        this.codPid = codPid;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.descricao = descricao;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.nomeMunicipio = nomeMunicipio;
        this.uf = uf;
        this.ibge = ibge;

    }

    public ChamadoEnderecos(String codPid, String nomeEstabelecimento, String descricao, String numero, String bairro, String complemento, String nomeMunicipio, String uf, String ibge,String solicitacao) {
        this.codPid = codPid;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.descricao = descricao;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.nomeMunicipio = nomeMunicipio;
        this.uf = uf;
        this.ibge = ibge;
        this.id_solicitacao = solicitacao;

    }

    public String getId_solicitacao() {
        return id_solicitacao;
    }

    public void setId_solicitacao(String solicitacao) {
        this.id_solicitacao = solicitacao;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodPid() {
        return codPid;
    }

    public void setCodPid(String codPid) {
        this.codPid = codPid;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
