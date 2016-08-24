/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.excell;

import br.com.minicom.scr.entity.Contato;
import br.com.minicom.scr.entity.Endereco;
import br.com.minicom.scr.entity.Municipio;
import br.com.minicom.scr.entity.PID;
import br.com.minicom.scr.entity.Solicitacoes;
import br.com.minicom.scr.entity.Telefone;
import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.query.Queries;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import br.com.minicom.scr.persistence.query.eQuery;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author Edilson Jr
 */
public class ExcellHandler_1 {

    public static void readSheet(Entity entity, File ifile) {
        Workbook workbook;
        Sheet sheet;
        Cell a6;
        PID pid;
        Endereco endereco;
        Contato contato;
        Municipio municipio;
        Telefone telefone;
        Solicitacoes solicitacoes;
        Queries q;
        System.out.println(ifile.getName());
        String[] planilha = ifile.getName().split("x");

        try {

            File diretorio = new File("C:\\Users\\Edilson Jr\\Documents\\GitHub\\GesacService");

            diretorio.mkdir();

            // Mandar o arquivo para o diretório informado
            String nome = ifile.getName();

            String arq[] = nome.split("\\\\");

            nome = arq[arq.length - 1];

            File file = new File(diretorio, nome);

            FileOutputStream output = new FileOutputStream(file);

////            InputStream is = ifile.getInputStream();
//
//            byte[] buffer = new byte[2048];
//
//            int nLidos;
//
//            while ((nLidos = is.read(buffer)) >= 0) {
//
//                output.write(buffer, 0, nLidos);
//
//            }
//
//            output.flush();
//
//            output.close();
            workbook = Workbook.getWorkbook(ifile);
            sheet = workbook.getSheet(0);

            System.out.println("Iniciando a leitura da planilha XLS...");

            for (int i = 2; i <= sheet.getRows(); i++) {
                pid = new PID();
                endereco = new Endereco();
                contato = new Contato();
                municipio = new Municipio();
                telefone = new Telefone();
                q = new SimpleQueries();
                solicitacoes = new Solicitacoes();
                for (int j = 0; j <= sheet.getColumns(); j++) {
                    a6 = sheet.getCell(j, i);

                    System.out.println("NUmero linha:" + a6.getRow() + "\n Numero de coluna: " + a6.getColumn() + "\nValor:" + a6.getContents().trim());
                    switch (j) {
                        case 0:
                            System.out.println("CASE:" + 1);

                            pid.setCodPid(Integer.parseInt(a6.getContents().trim()));

                            endereco.setCodPid(Integer.parseInt(a6.getContents().trim()));
                            contato.setCodPid(Integer.parseInt(a6.getContents().trim()));
                            solicitacoes.setCodPid(Integer.parseInt(a6.getContents().trim()));
                            break;
                        case 1:
                            System.out.println("CASE:" + 2);
                            pid.setCodGesac(Integer.parseInt(a6.getContents().trim()));
                            break;
                        case 2:
                            pid.setNomeEstabelecimento(a6.getContents().trim());
                            System.out.println(pid.toString());
                            eQuery.insert.execute(pid);

                            solicitacoes.setCodServico(eQuery.selectID.execute(entity));
                            break;
                        case 3:
                            endereco.setDescricao(a6.getContents().trim());
                            break;
                        case 4:
                            endereco.setNumero(a6.getContents().trim());
                            break;
                        case 5:
                            endereco.setBairro(a6.getContents().trim());
                            break;
                        case 6:
                            endereco.setCep(a6.getContents().trim());
                            break;
                        case 7:
                            endereco.setComplemento(a6.getContents().trim());
                            break;
                        case 8:
                            municipio.setCodIBGE(Integer.parseInt(a6.getContents().trim()));
                            break;

                        case 9:
                            contato.setNome(a6.getContents().trim());
                            q.insert(contato);

                            break;
                        case 10:
                            telefone.setIdContato(q.select(contato));
                            telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            break;
                        case 11:
                            telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                            q.insert(telefone);
                            break;
                        case 12:
                            if (!a6.equals(null) || !a6.getContents().isEmpty() || a6.getContents().length() < 1) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 13:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 14:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 15:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 18:
                            contato.setNome(a6.getContents().trim());
                            q.insert(contato);
                            break;
                        case 19:
                            telefone.setIdContato(q.select(contato));
                            telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            break;
                        case 20:
                            telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                            q.insert(telefone);
                            break;
                        case 21:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }

                            break;
                        case 22:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 23:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 24:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 27:
                            contato.setNome(a6.getContents().trim());
                            q.insert(contato);
                            break;
                        case 28:
                            telefone.setIdContato(q.select(contato));
                            telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            break;
                        case 29:
                            telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                            q.insert(telefone);
                            break;
                        case 30:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }

                            break;
                        case 31:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 32:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 33:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 36:
                            contato.setNome(a6.getContents().trim());
                            q.insert(contato);
                            break;
                        case 37:
                            telefone.setIdContato(q.select(contato));
                            telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            break;
                        case 38:
                            telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                            q.insert(telefone);
                            break;
                        case 39:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }

                            break;
                        case 40:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 41:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 42:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 45:
                            contato.setNome(a6.getContents().trim());
                            q.insert(contato);
                            break;
                        case 46:
                            telefone.setIdContato(q.select(contato));
                            telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            break;
                        case 47:
                            telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                            q.insert(telefone);
                            break;
                        case 48:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }

                            break;
                        case 49:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                        case 50:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 51:
                            if (!a6.equals(null) || !a6.equals("")) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                            }
                            break;
                    }
                }
            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcellHandler_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
