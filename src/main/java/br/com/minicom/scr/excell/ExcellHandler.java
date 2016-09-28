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
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class ExcellHandler {

    /**
     * Recebe o servico para inserir seu id como chave estrangeira, e insere
     * todas as informações dos PID necessarios para o chamado ser executado.
     *
     * @param entity Servico
     * @param ifile planilha com os PIDs
     */
    public static void readSheet(Entity entity, FileItem ifile) {
        Workbook workbook;
        Sheet sheet;
        Cell a6;

        Endereco endereco;
        Contato contato;
        Municipio municipio;
        Telefone telefone;
        Solicitacoes solicitacoes;
        SimpleQueries q = new SimpleQueries();

        String[] planilha = ifile.getName().split("x");

        try {

            File diretorio = new File("C:\\Users\\Edilson Jr\\Documents\\GitHub\\GesacService\\arquivos\\excell");

            diretorio.mkdir();

            // Mandar o arquivo para o diretório informado
            String nome = ifile.getName();

            String arq[] = nome.split("\\\\");

            nome = arq[arq.length - 1];

            File file = new File(diretorio, nome);

            FileOutputStream output = new FileOutputStream(file);

            InputStream is = ifile.getInputStream();

            byte[] buffer = new byte[2048];

            int nLidos;

            while ((nLidos = is.read(buffer)) >= 0) {

                output.write(buffer, 0, nLidos);

            }

            output.flush();

            output.close();
            workbook = Workbook.getWorkbook(file);
            sheet = workbook.getSheet(0);

            System.out.println("Iniciando a leitura da planilha XLS...");
            System.out.println(sheet.getRows());
            for (int i = 1; i <= sheet.getRows() - 1; i++) {
                PID pid = new PID();
                endereco = new Endereco();
                contato = new Contato();
                municipio = new Municipio();
                telefone = new Telefone();

                solicitacoes = new Solicitacoes();
                for (int j = 0; j <= sheet.getColumns() - 1; j++) {
                    a6 = sheet.getCell(j, i);

//                    System.out.println("NUmero linha : " + a6.getRow() + "\n Numero de coluna : " + a6.getColumn() + "\nValor :" + a6.getContents().trim());
                    switch (j) {
                        case 0:
                            if (a6.getContents().length() > 0) {

                                pid.setCodPid(Integer.parseInt(a6.getContents().trim()));

                                endereco.setCodPid(Integer.parseInt(a6.getContents().trim()));
                                contato.setCodPid(Integer.parseInt(a6.getContents().trim()));
                                solicitacoes.setCodPid(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;

                        case 1:
                            if (a6.getContents().length() > 0) {

                                pid.setCodGesac(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 2:
                            if (a6.getContents().length() > 0) {
                                pid.setNomeEstabelecimento(a6.getContents().trim());
                                System.out.println(pid.toString());
                                q.insert(pid);
//                                System.out.println("NUmero linha : " + a6.getRow() + "\n Numero de coluna : " + a6.getColumn() + "\nValor :" + a6.getContents().trim());
                                solicitacoes.setCodServico(q.select(entity));
                                solicitacoes.setEmChamado(0);
                                solicitacoes.setUltTentativa(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                System.out.println(solicitacoes.toString());
                                q.insert(solicitacoes);
                            }
                            break;
                        case 3:
                            if (a6.getContents().length() > 0) {
                                endereco.setDescricao(a6.getContents().trim());
                            }
                            break;
                        case 4:
                            if (a6.getContents().length() > 0) {
                                endereco.setNumero(a6.getContents().trim());
                            }
                            break;
                        case 5:
                            if (a6.getContents().length() > 0) {
                                endereco.setBairro(a6.getContents().trim());
                            }
                            break;
                        case 6:
                            if (a6.getContents().length() > 0) {
                                endereco.setCep(a6.getContents().trim());
                            }
                            break;
                        case 7:
                            if (a6.getContents().length() > 0) {
                                endereco.setComplemento(a6.getContents().trim());
                                endereco.setDtAtualizacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                            }
                            break;
                        case 8:
                            if (a6.getContents().length() > 0) {
                                municipio.setCodIBGE(Integer.parseInt(a6.getContents().trim()));
                                endereco.setCodIBGE(Integer.parseInt(a6.getContents().trim()));
                                q.insert(municipio);
                                q.insert(endereco);
                            }
                            break;

                        case 9:
                            if (a6.getContents().length() > 0) {
                                contato.setNome(a6.getContents().trim());
                                q.insert(contato);

                            }
                            break;
                        case 10:
                            if (a6.getContents().length() > 0) {
                                telefone.setIdContato(q.select(contato));
                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                System.out.println("CONTATO: " + q.select(contato));
                            }
                            break;
                        case 11:
                            if (a6.getContents().length() > 0) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }
                            break;
                        case 12:
                            if (a6.getContents().length() > 0) {
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                            }
                            break;
                        case 13:
                            if (a6.getContents().length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));

                            }
                            break;

                        case 14:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }
                            break;

                        case 15:
                            if (a6.getContents().length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }
                            break;

                        case 18:
                            if (a6.getContents().length() > 0) {
                                contato.setNome(a6.getContents().trim());
                                q.insert(contato);
                            }
                            break;
                        case 19:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }
                            break;
                        case 20:
                            if (a6.getContents().length() > 0) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }
                            break;
                        case 21:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }
                            break;

                        case 22:
                            if (a6.getContents().length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));

                            }
                            break;

                        case 23:
                            if (a6.getContents()
                                    .length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }

                            break;

                        case 24:
                            if (a6.getContents().length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));

                            }
                            break;

                        case 27:
                            if (a6.getContents()
                                    .length() > 0) {
                                contato.setNome(a6.getContents().trim());
                                q.insert(contato);
                            }

                            break;
                        case 28:
                            if (a6.getContents()
                                    .length() > 0) {
                                telefone.setIdContato(q.select(contato));
                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }

                            break;
                        case 29:
                            if (a6.getContents()
                                    .length() > 0) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }

                            break;
                        case 30:
                            if (a6.getContents()
                                    .length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }

                            break;

                        case 31:
                            if (a6.getContents()
                                    .length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }

                            break;

                        case 32:
                            if (a6.getContents()
                                    .length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;

                        case 33:
                            if (a6.getContents()
                                    .length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));

                            }
                            break;

                        case 36:
                            if (a6.getContents().length() > 0) {
                                contato.setNome(a6.getContents().trim());
                                q.insert(contato);
                            }
                            break;
                        case 37:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }
                            break;
                        case 38:
                            if (a6.getContents().length() > 0) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }
                            break;
                        case 39:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }

                            break;
                        case 40:
                            if (a6.getContents()
                                    .length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }

                            break;
                        case 41:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                            }
                            break;
                        case 42:
                            if (a6.getContents().length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));

                            }
                            break;

                        case 45:
                            if (a6.getContents().length() > 0) {
                                contato.setNome(a6.getContents().trim());
                                q.insert(contato);
                            }
                            break;
                        case 46:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }
                            break;
                        case 47:
                            if (a6.getContents().length() > 0) {
                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }
                            break;
                        case 48:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }
                            break;

                        case 49:
                            if (a6.getContents().length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));

                            }
                            break;
                        case 50:
                            if (a6.getContents().length() > 0) {

                                telefone.setIdContato(q.select(contato));
                                telefone.setDdd(Integer.parseInt(a6.getContents().trim()));

                            }
                            break;

                        case 51:
                            if (a6.getContents().length() > 0) {

                                telefone.setTelefone(Integer.parseInt(a6.getContents().trim()));
                                q.insert(telefone);
                                System.out.println("CONTATO: " + q.select(contato));
                            }

                            break;

                    }

                }
            }
            q.close();

        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcellHandler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
