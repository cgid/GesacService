/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.consultas;

/**
 *
 * @author karla.mendes
 */
public class ChamadosAtendidos {

    int totalDeChamados, chamadosConcluidos, chamadosRealizados;

    public int getTotalDeChamados() {
        return totalDeChamados;
    }

    public int getChamadosRealizados() {
        return chamadosRealizados;
    }

    public void setChamadosRealizados(int chamadosRealizados) {
        this.chamadosRealizados = chamadosRealizados;
    }

    public void setTotalDeChamados(int totalDeChamados) {
        this.totalDeChamados = totalDeChamados;
    }

    public int getChamadosConcluidos() {
        return chamadosConcluidos;
    }

    public void setChamadosConcluidos(int chamadosConcluidos) {
        this.chamadosConcluidos = chamadosConcluidos;
    }
}
