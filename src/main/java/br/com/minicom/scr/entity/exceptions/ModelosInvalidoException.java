/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.entity.exceptions;

/**
 *
 * @author murilo
 */
public class ModelosInvalidoException extends RuntimeException {

    public ModelosInvalidoException() {
        super("A planilha não está no padrão aceitavel para essa operação.\n Tente outra .");
    }

    public ModelosInvalidoException(String _cod_pid_cod_gesac_cod_tc_cod_cd_cod_crc_) {
        if (_cod_pid_cod_gesac_cod_tc_cod_cd_cod_crc_.equals("/ /COD_PID/ /COD_GESAC/ /COD_TC/ /COD_CD/ /COD_CRC/ /ESTABELECIMENTO/ /ENDEREÇO/ /Nº/ /BAIRRO/ /CEP/ /Complemento/ /COD_IBGE/ /Contato 1/ /ddd1 t1/ /Tel1 Contato1/ /ddd1 t2/ /Tel2 Contato1/ /ddd t3 / /Tel3Contato1/ /E-mail 1 Contato 1/ /Validado recentemente - Contato 2/ /Contato 2/ /ddd t1/ /Tel1Contato2/ /ddd t1/ /Tel2Contato2/ /ddd t1/ /Tel3Contato2/ /E-mail 1 Contato 2/ /Validado recentemente - Contato 3/ /Contato 3/ /ddd t1/ /Tel1Contato3/ /ddd t1/ /Tel2Contato3/ /ddd t1/ /Tel3Contato3/ /E-mail 1 Contato 3/ /Validado recentemente - Contato 4/ /Contato 4/ /ddd t1/ /Tel1Contato4/ /ddd t1/ /Tel2Contato4/ /ddd t1/ /Tel3Contato4/ /E-mail 1 Contato 4/ /Validado recentemente - Contato 5/ /Contato 5/ /ddd t1/ /Tel1Contato5/ /ddd t1/ /Tel2Contato5/ /ddd t1/ /Tel3Contato5")) {

        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
