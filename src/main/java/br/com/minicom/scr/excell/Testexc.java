/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.excell;

import br.com.minicom.scr.entity.Servico;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;

/**
 *
 * @author Edilson Jr
 */
public class Testexc {

    public static void main(String[] args) {
        String pathname = "C:\\Users\\Edilson Jr\\Documents\\GitHub\\GesacService\\arquivos\\excell\\modelo.xls";
        Servico servico = new Servico();
        File item = new File(pathname);

        ExcellHandler.readSheet(servico, item);
    }
}
