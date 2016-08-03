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
        Sheet sheet;
        Cell a6;
        try {
            workbook = Workbook.getWorkbook(new File("Itapuranga.xls"));
            sheet = workbook.getSheet(0);            
            
            System.out.println("Iniciando a leitura da planilha XLS:");
            for (int i = 2; i <= sheet.getRows(); i++) {
                for (int j = 1; j <= sheet.getColumns(); j++) {
                    a6 = sheet.getCell(i, j);
                    switch(j) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;    
                        case 9:
                            break;    
                        case 10:
                            break;   
                    }
                }
            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
