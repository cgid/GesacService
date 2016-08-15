package br.com.minicom.scr.autoclass;


import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author murilo
 */
public class Main {
    public static void main(String[] args) {
        String[] tabelas;
        tabelas = Dados.getTables();
        
        //System.out.println(ClassPrototype.makeVarPadrao(Dados.getTableData(Dados.getTables()[0]), Dados.getTables()[0]));
        Scanner input = new Scanner(System.in);
        FileWriter arquivo;
        File file;
        try {
            for (int i = 0; i < tabelas.length; i++) {
                file = new File("/home/murilo/NetBeansProjects/GesacService/src/main/java/entities/" + tabelas[i].concat(".java"));
                System.out.println(file.getAbsolutePath());
                arquivo = new FileWriter(file);
                arquivo.write(ClassPrototype.makeVarPadrao(Dados.getTableData(tabelas[i]), tabelas[i]));
                arquivo.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
