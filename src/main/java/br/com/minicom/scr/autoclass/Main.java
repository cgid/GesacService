package br.com.minicom.scr.autoclass;


import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author murilo
 */
public class Main {
    public static void main(String[] args) {
        String[] tabelas;
        tabelas = Dados.getTables();
        
        //System.out.println(ClassPrototype.buildClass(Dados.getTableData(Dados.getTables()[0]), Dados.getTables()[0]));
    
        FileWriter arquivo;
        File file;
        try {
            for (int i = 0; i < tabelas.length; i++) {
                file = new File("C:\\Users\\karla.mendes\\Documents\\NetBeansProjects\\GesacService\\src\\main\\java\\br\\com\\minicom\\scr\\entity\\" + tabelas[i].toUpperCase().charAt(0)+tabelas[i].substring(1, tabelas[i].length())+".java");
                System.out.println(file.getAbsolutePath());
                arquivo = new FileWriter(file);
                arquivo.write(ClassPrototype.buildClass(Dados.getTableData(tabelas[i]), tabelas[i].toUpperCase().charAt(0)+tabelas[i].substring(1, tabelas[i].length())));
                arquivo.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
