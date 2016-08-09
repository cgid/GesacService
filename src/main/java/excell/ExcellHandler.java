/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excell;


import entities.Contato;
import entities.Endereco;
import entities.Municipio;
import entities.PID;
import entities.Telefone;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import persistence.Queries;
import persistence.SimpleQueries;



/**
 *
 * @author Edilson Jr
 */
public class ExcellHandler {

    public void readSheet() {
        Workbook workbook;
        Sheet sheet;

        try {
            workbook = Workbook.getWorkbook(new File("Itapuranga.xls"));
            sheet = workbook.getSheet(0);

            System.out.println("Iniciando a leitura da planilha XLS...");

            for (int i = 2; i <= sheet.getRows(); i++) {
                for (int j = 1; j <= sheet.getColumns(); j++) {
                    Cell a6 = sheet.getCell(i, j);
                    PID pid = new PID();
                    Endereco endereco = new Endereco();
                    Contato contato = new Contato();
                    Municipio municipio = new Municipio();
                    Telefone telefone = new Telefone();
                    Queries q = new SimpleQueries();

                    switch (j) {
                        case 1:
                            pid.setCodPID(Integer.parseInt(a6.getContents()));
                            endereco.setCodPID(Integer.parseInt(a6.getContents()));
                            contato.setCodPID(Integer.parseInt(a6.getContents()));
                            break;
                        case 2:
                            pid.setCodGesac(Integer.parseInt(a6.getContents()));
                            break;
                        case 3:
                            pid.setNomeEstabelecimento(a6.getContents());

                            break;
                        case 4:
                            endereco.setDescricao(a6.getContents());
                            break;
                        case 5:
                            endereco.setNumero(a6.getContents());
                            break;
                        case 6:
                            endereco.setBairro(a6.getContents());
                            break;
                        case 7:
                            municipio.setCod_IBGE(a6.getContents());
                            break;
                        case 8:
                            contato.setNome(a6.getContents());
                            q.insert(contato);
                            break;
                        case 9:
                            telefone.setCod_contato(q.especificallySelect(contato).getCell(0).getValue());
                            telefone.setDDD(a6.getContents());

                            break;
                        case 10:
                            telefone.setTelefone(a6.getContents());
                            q.insert(telefone);
                            break;
                        case 11:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }

                            break;
                        case 12:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 13:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }
                            break;
                        case 14:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 18:
                            contato.setNome(a6.getContents());
                            q.insert(contato);
                            break;
                        case 19:
                            telefone.setCod_contato(q.especificallySelect(contato).getCell(0).getValue());
                            telefone.setDDD(a6.getContents());

                            break;
                        case 20:
                            telefone.setTelefone(a6.getContents());
                            q.insert(telefone);
                            break;
                        case 21:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }

                            break;
                        case 22:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 23:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }
                            break;
                        case 24:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 27:
                            contato.setNome(a6.getContents());
                            q.insert(contato);
                            break;
                        case 28:
                            telefone.setCod_contato(q.especificallySelect(contato).getCell(0).getValue());
                            telefone.setDDD(a6.getContents());

                            break;
                        case 29:
                            telefone.setTelefone(a6.getContents());
                            q.insert(telefone);
                            break;
                        case 30:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }

                            break;
                        case 31:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 32:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }
                            break;
                        case 33:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 36:
                            contato.setNome(a6.getContents());
                            q.insert(contato);
                            break;
                        case 37:
                            telefone.setCod_contato(q.especificallySelect(contato).getCell(0).getValue());
                            telefone.setDDD(a6.getContents());

                            break;
                        case 38:
                            telefone.setTelefone(a6.getContents());
                            q.insert(telefone);
                            break;
                        case 39:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }

                            break;
                        case 40:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 41:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }
                            break;
                        case 42:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 45:
                            contato.setNome(a6.getContents());
                            q.insert(contato);
                            break;
                        case 46:
                            telefone.setCod_contato(q.especificallySelect(contato).getCell(0).getValue());
                            telefone.setDDD(a6.getContents());

                            break;
                        case 47:
                            telefone.setTelefone(a6.getContents());
                            q.insert(telefone);
                            break;
                        case 48:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }

                            break;
                        case 49:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                        case 50:
                            if (!a6.equals(null)) {
                                telefone.setDDD(a6.getContents());
                            }
                            break;
                        case 51:
                            if (!a6.equals(null)) {
                                telefone.setTelefone(a6.getContents());
                                q.insert(telefone);
                            }
                            break;
                    }
                }
            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
