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

    public ChamadoeRespostas(String pid, String estabelecimento, String obs, String reposta1, String reposta2, String reposta3, String reposta4, String reposta5, String reposta6, String reposta7, String reposta8) {
        this.pid = pid;
        this.estabelecimento = estabelecimento;
        this.obs = obs;
       
    }

    @Override
    public String toString() {
        return "ChamadoeRespostas{" + "pid=" + pid + ", estabelecimento=" + estabelecimento + ", obs=" + obs + ", repostas=" + repostas.size() + '}';
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
        this.repostas = repostas;
    }

    
    String pid, estabelecimento, obs;
            List<String> repostas;
}
