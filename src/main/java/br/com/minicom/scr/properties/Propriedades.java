package br.com.minicom.scr.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author VictorHugo
 */
public class Propriedades extends Properties {
    private static Propriedades instance;
    
    public static Propriedades getInstance() throws IOException {
        if (instance.equals(null)) instance = new Propriedades();
        return instance;
    }

    private Propriedades() throws IOException {
        String file = System.getenv("cidadesinteligentes.configuration");
        if (null == file || "".equals(file)) {
            Logger.getLogger(Propriedades.class.getName()).severe("A propriendade de ambiente cidadesinteligentes.configuration precisa apontar para o arquivo de configuração.");
            file = "./sid/cgid/gesacservice/dados.properties";
        }
        super.load(new FileInputStream(new File(file)));
    }

}
