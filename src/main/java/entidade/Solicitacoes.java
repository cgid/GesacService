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
public class Solicitacoes {

    int id;
    int tentativas;
    int servico_cod_servico;
    int id_gesac;
    
    boolean contato_ok;
    
    enum status {
        EM_USO, DISPONIVEL, ENCERRADO

    };

}
