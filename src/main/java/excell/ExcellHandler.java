/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excell;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Edilson Jr
 */
public class ExcellHandler {

    public void readSheet() {
        Workbook workbook;
        try {
            workbook = Workbook.getWorkbook(new File("Itapuranga.xls"));

            Sheet sheet = workbook.getSheet(0);

            int numColunas = sheet.getColumns();
            int numero[] = null;
            String nomedascolunas[] = null;
            System.out.println("Iniciando a leitura da planilha XLS:");
            for (int i = 1; i < numColunas; i++) {
                Cell a6 = sheet.getCell(i, 0);

                numero[i] = a6.getColumn();
                nomedascolunas[i] = a6.getContents();

            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
