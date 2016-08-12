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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.omg.CORBA.AnySeqHelper;
import persistence.query.Queries;
import persistence.query.SimpleQueries;

/**
 *
 * @author Edilson Jr
 */
public class ExcellHandler {

    public void readSheet() {
        Workbook workbook;
        Sheet sheet;
        Cell a6;
        PID pid;
        Endereco endereco;
        Contato contato;
        Municipio municipio;
        Telefone telefone;
        Queries q;

        try {
            workbook = Workbook.getWorkbook(new File("modelo.xls"));
            sheet = workbook.getSheet(0);

            System.out.println("Iniciando a leitura da planilha XLS...");

            for (int i = 1; i <= 18; i++) {
                System.out.println(sheet.getRows());
                System.out.println(sheet.getColumns());
                pid = new PID();
                endereco = new Endereco();
                contato = new Contato();
                municipio = new Municipio();
                telefone = new Telefone();
                q = new SimpleQueries();
                LocalDateTime dateTime = LocalDateTime.now();
                for (int j = 0; j <= sheet.getColumns() - 1; j++) {

                    a6 = sheet.getCell(j, i);

                    System.out.println("linha:   " + a6.getRow() + "coluna:  " + a6.getColumn() + " valor: " + a6.getContents());

                    switch (j) {
                        case 0:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                System.out.println("case: " + Integer.parseInt(a6.getContents()));
                                pid.setCodPid(Integer.parseInt(a6.getContents()));
                                System.out.println(pid.getCell(0).getValue());
                                endereco.setCodPid(Integer.parseInt(a6.getContents()));
                                contato.setCodPid(Integer.parseInt(a6.getContents()));
                            }
                            break;
                        case 1:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                pid.setCodGesac(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 2:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                pid.setNomeEstabelecimento(a6.getContents());
//                                System.out.println(pid.toString());
//                                System.out.println(contato.toString());
//                                System.out.println(endereco.toString());
                                q.insert(pid);
                            }
                            break;
                        case 3:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                endereco.setDescricao(a6.getContents());
                            }
                            break;
                        case 4:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                endereco.setNumero(a6.getContents());
                            }
                            break;
                        case 5:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                endereco.setBairro(a6.getContents());
                            }
                            break;
                        case 6:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                endereco.setCep(a6.getContents());
                            }
                            break;
                        case 7:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                endereco.setComplemento(a6.getContents());

                            }
                            break;
                        case 8:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                municipio.setCodIBGE(Integer.parseInt(a6.getContents()));
                                q.insert(municipio);
                                endereco.setCodIBGE(Integer.parseInt(a6.getContents()));
                                endereco.setDtAtualizacao(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                q.insert(endereco);

                            }
                            break;
                        case 9:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                contato.setNome(a6.getContents());
//                                System.out.println(contato.toString());
                                q.insert(contato);
                            }
                            break;
                        case 10:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setIdContato(q.especificallySelect(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents()));
                            }
                            break;
                        case 11:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);
                            }
                            break;
                        case 12:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setDdd(Integer.parseInt(a6.getContents()));
                            }
                            break;
                        case 13:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);
                            }
                            break;
                        case 14:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 15:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 18:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                contato.setNome(a6.getContents());
                                q.insert(contato);
                            }
                            break;
                        case 19:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
//                                System.out.println("TAMANHO: " + a6.getContents().length());
                                telefone.setIdContato(q.especificallySelect(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 20:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);
                            }
                            break;
                        case 21:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 22:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 23:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 24:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 27:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                contato.setNome(a6.getContents());
                                q.insert(contato);
                            }
                            break;
                        case 28:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setIdContato(q.especificallySelect(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 29:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);
                            }
                            break;
                        case 30:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 31:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 32:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 33:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 36:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                contato.setNome(a6.getContents());
                                q.insert(contato);
                            }
                            break;
                        case 37:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setIdContato(q.especificallySelect(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 38:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);
                            }
                            break;
                        case 39:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 40:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 41:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 42:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 45:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                contato.setNome(a6.getContents());
                                q.insert(contato);
                            }
                            break;
                        case 46:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setIdContato(q.especificallySelect(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 47:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);
                            }
                            break;
                        case 48:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 49:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
                                q.insert(telefone);

                            }
                            break;
                        case 50:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setDdd(Integer.parseInt(a6.getContents()));

                            }
                            break;
                        case 51:
                            if (!a6.equals(null) && a6.getContents().length() >= 1) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents()));
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
