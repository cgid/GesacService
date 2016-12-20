/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.relatorios;

/**
 *
 * @author Edilson Jr
 */
public class RelatorioUsuario {

    String lista, PID, chamado, dataAbertura, duracao, obs;

    public RelatorioUsuario(String lista, String PID, String chamado, String dataAbertura, String duracao, String obs) {
        this.lista = lista;
        this.PID = PID;
        this.chamado = chamado;
        this.dataAbertura = dataAbertura;
        this.duracao = duracao;
        this.obs = obs;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getChamado() {
        return chamado;
    }

    public void setChamado(String chamado) {
        this.chamado = chamado;
    }

    public String getDataAbertura() {
        return dataAbertura.trim();
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
