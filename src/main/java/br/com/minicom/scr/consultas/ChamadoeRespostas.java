/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.consultas;

import java.util.List;

/**
 *
 * @author Edilson Jr
 */
public class ChamadoeRespostas {

    public ChamadoeRespostas() {
    }

    public ChamadoeRespostas(String pid, String estabelecimento, String obs, String chamado, List<String> repostas) {
        this.pid = pid;
        this.estabelecimento = estabelecimento;
        this.obs = obs;
        this.chamado = chamado;
        this.repostas = repostas;
    }

    @Override
    public String toString() {
        return "ChamadoeRespostas{" + "pid=" + pid + ", estabelecimento=" + estabelecimento + ", obs=" + obs + ", chamado=" + chamado + ", repostas=" + repostas + '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<String> getRepostas() {
        return repostas;
    }

    public void setRepostas(List<String> repostas) {
        
            this.repostas=repostas;
         
    }

    public String getChamado() {
        return chamado;
    }

    public void setChamado(String chamado) {
        this.chamado = chamado;
    }

    String pid, estabelecimento, obs, chamado;
    List<String> repostas;
}
