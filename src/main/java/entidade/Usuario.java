/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;


/**
 *
 * @author Edilson Jr
 */
public class Usuario {
    String nome;
    String login;
    String senha;
    int id;   
    int Perfil_cod_perfil;   

    public int getPerfil_cod_perfil() {
        return Perfil_cod_perfil;
    }

    public void setPerfil_cod_perfil(int Perfil_cod_perfil) {
        this.Perfil_cod_perfil = Perfil_cod_perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
