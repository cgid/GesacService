/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.relatorios;

/**
 *
 * @author idigital
 */
public class RelatorioPorPid {

    @Override
    public String toString() {
        return "RelatorioPorPid{" + "\n servico=" + servico + ", \ndtAbertura=" + dtAbertura + ",\n obs=" + obs + ", \nduracao=" + duracao + ",\n usuario=" + usuario + '}';
    }

    private Integer servico;
    private String dtAbertura, obs, duracao, usuario;

    public RelatorioPorPid(Integer servico, String usuario, String duracao, String dtAbertura, String obs) {
        this.servico = servico;

        this.usuario = usuario;
        this.duracao = duracao;
        this.dtAbertura = dtAbertura;
        this.obs = obs;
    }

    public Integer getServico() {
        return servico;
    }

    public void setServico(Integer servico) {
        this.servico = servico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(String dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
