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
public class ConsultaContatos {

    String nome,
            ddd,
            telefone;

    public ConsultaContatos(String nome, String ddd, String telefone) {
        this.nome = nome;
        this.ddd = ddd;
        this.telefone = telefone;
    }
        
           



    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



  

    public String getDdd() {
        return ddd;
    }

    public String getTelefone() {
        return telefone;
    }

 


}
