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
import br.com.minicom.scr.entity.exceptions.NotIsInsertableEntityException;
import br.com.minicom.scr.persistence.Entity;
import br.com.minicom.scr.persistence.query.SimpleQueries;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
    public static String readSheet(int usuario, Entity entity, FileItem ifile) {
        Workbook workbook;
        Sheet sheet;
        Cell a6 = null;
        int idServ = Integer.parseInt(String.valueOf(entity.getCell(0).getValue()));
        Endereco endereco;
        Contato contato;
        Municipio municipio;
        Telefone telefone;
        Solicitacoes solicitacoes;
        SimpleQueries q = new SimpleQueries();

        String[] planilha = ifile.getName().split("x");

        File diretorio = new File("C:\\Users\\Edilson Jr\\Documents\\GitHub\\GesacService\\arquivos\\excell");

        diretorio.mkdir();

        // Mandar o arquivo para o diretório informado
        String nome = ifile.getName();

        String arq[] = nome.split("\\\\");

        nome = arq[arq.length - 1];

        File file = new File(diretorio, nome);

        FileOutputStream output;
        try {
            output = new FileOutputStream(file);

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
            String titulos = "";
            System.out.println("Iniciando a leitura da planilha XLS...");
            System.out.println(sheet.getRows());
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < sheet.getColumns() - 1; j++) {
                    a6 = sheet.getCell(j, i);
                    titulos = titulos + "/ /" + a6.getContents();
                }
            }
            System.err.println(titulos);
            if (titulos.equals("/ /COD_PID/ /COD_GESAC/ /COD_TC/ /COD_CD/ /COD_CRC/ /ESTABELECIMENTO/ /ENDEREÇO/ /Nº/ /BAIRRO/ /CEP/ /Complemento/ /COD_IBGE/ /Contato 1/ /ddd1 t1/ /Tel1 Contato1/ /ddd1 t2/ /Tel2 Contato1/ /ddd t3 / /Tel3Contato1/ /E-mail 1 Contato 1/ /Validado recentemente - Contato 2/ /Contato 2/ /ddd t1/ /Tel1Contato2/ /ddd t1/ /Tel2Contato2/ /ddd t1/ /Tel3Contato2/ /E-mail 1 Contato 2/ /Validado recentemente - Contato 3/ /Contato 3/ /ddd t1/ /Tel1Contato3/ /ddd t1/ /Tel2Contato3/ /ddd t1/ /Tel3Contato3/ /E-mail 1 Contato 3/ /Validado recentemente - Contato 4/ /Contato 4/ /ddd t1/ /Tel1Contato4/ /ddd t1/ /Tel2Contato4/ /ddd t1/ /Tel3Contato4/ /E-mail 1 Contato 4/ /Validado recentemente - Contato 5/ /Contato 5/ /ddd t1/ /Tel1Contato5/ /ddd t1/ /Tel2Contato5/ /ddd t1/ /Tel3Contato5"
            )) {
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
                            case 5:
                                if (a6.getContents().length() > 0) {
                                    pid.setNomeEstabelecimento(a6.getContents().trim());
                                    q.insert(pid);
                                    System.out.println("NUmero linha : " + a6.getRow() + "\n Numero de coluna : " + a6.getColumn() + "\nValor :" + a6.getContents().trim());
                                    if (idServ == 0) {
                                        solicitacoes.setIdServico(q.select(entity));
                                    } else {
                                        solicitacoes.setIdServico(idServ);
                                    }

                                    solicitacoes.setEmChamado(0);
                                    solicitacoes.setUltTentativa(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                    solicitacoes.setDtAgenda(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                    System.out.println(solicitacoes.toString());
                                    q.insert(solicitacoes);
                                }
                                break;
                            case 6:
                                if (a6.getContents().length() > 0) {
                                    endereco.setDescricao(a6.getContents().trim());
                                }
                                break;
                            case 7:
                                if (a6.getContents().length() > 0) {
                                    endereco.setNumero(a6.getContents().trim());
                                }
                                break;
                            case 8:
                                if (a6.getContents().length() > 0) {
                                    endereco.setBairro(a6.getContents().trim());
                                }
                                break;
                            case 9:
                                if (a6.getContents().length() > 0) {
                                    endereco.setCep(a6.getContents().trim());
                                }
                                break;
                            case 10:
                                if (a6.getContents().length() > 0) {
                                    endereco.setComplemento(a6.getContents().trim());

                                }
                                break;
                            case 11:
                                if (a6.getContents().length() > 0) {
                                    endereco.setDtAtualizacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                    municipio.setCodIBGE(Integer.parseInt(a6.getContents().trim()));
                                    endereco.setCodIBGE(Integer.parseInt(a6.getContents().trim()));
                                    q.insert(municipio);
                                    q.insert(endereco);
                                }
                                break;

                            case 12:
                                if (a6.getContents().length() > 0) {
                                    contato.setSituacao(1);
                                    contato.setIdUsuario(usuario);
                                    contato.setNome(a6.getContents().trim());
                                    contato.setEmail("Não informado");

                                    q.insert(contato);
                                }
                                break;
                            case 13:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));

                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 14:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 15:
                                if (a6.getContents().length() > 0) {
                                    if (a6.getContents().length() > 0) {
                                        telefone.setIdContato(q.select(contato));
                                        telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                    }
                                }
                                break;
                            case 16:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 17:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 18:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 19:
                                if (a6.getContents().length() > 0) {

                                    Contato c = (Contato) q.select(contato, q.select(new Contato()));
                                    System.out.println(c.toString());
                                    c.setEmail(a6.getContents());
                                    System.out.println(c.toString());
                                    q.update(c);

                                }
                                break;
                            case 21:
                                if (a6.getContents().length() > 0) {
                                    contato.setSituacao(1);
                                    contato.setIdUsuario(usuario);
                                    contato.setNome(a6.getContents().trim());
                                    contato.setEmail("Não informado");
                                    System.err.println("LINHA 21");
                                    System.err.println(contato.toString());

                                    q.insert(contato);
                                }

                                break;
                            case 22:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 23:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 24:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 25:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 26:
                                if (a6.getContents()
                                        .length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 27:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 28:
                                if (a6.getContents().length() > 0) {

                                    Contato c = (Contato) q.select(contato, q.select(contato));
                                    c.setEmail(a6.getContents());
                                    System.out.println(c.toString());
                                    q.update(c);

                                }
                                break;
                            case 30:
                                if (a6.getContents().length() > 0) {
                                    contato.setSituacao(1);
                                    contato.setIdUsuario(usuario);
                                    contato.setNome(a6.getContents().trim());
                                    contato.setEmail("Não informado");
                                    q.insert(contato);
                                }
                                break;
                            case 31:
                                if (a6.getContents()
                                        .length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 32:
                                if (a6.getContents()
                                        .length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 33:
                                if (a6.getContents()
                                        .length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 34:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 35:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 36:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 37:
                                if (a6.getContents().length() > 0) {

                                    Contato c = (Contato) q.select(contato, q.select(contato));
                                    c.setEmail(a6.getContents());
                                    System.out.println(c.toString());
                                    q.update(c);

                                }
                                break;
                            case 39:
                                if (a6.getContents().length() > 0) {
                                    contato.setSituacao(1);
                                    contato.setIdUsuario(usuario);
                                    contato.setNome(a6.getContents().trim());
                                    contato.setEmail("Não informado");
                                    q.insert(contato);
                                }
                                break;
                            case 40:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 41:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 42:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 43:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 44:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 45:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 46:
                                if (a6.getContents().length() > 0) {

                                    Contato c = (Contato) q.select(contato, q.select(contato));
                                    c.setEmail(a6.getContents());
                                    System.out.println(c.toString());
                                    q.update(c);

                                }
                                break;
                            case 48:
                                if (a6.getContents().length() > 0) {
                                    contato.setSituacao(1);

                                    contato.setIdUsuario(usuario);
                                    contato.setNome(a6.getContents().trim());
                                    contato.setEmail("Não informado");
                                    q.insert(contato);
                                }
                                break;
                            case 49:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 50:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 51:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 52:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 53:
                                if (a6.getContents().length() > 0) {
                                    telefone.setIdContato(q.select(contato));
                                    telefone.setDdd(Integer.parseInt(a6.getContents().trim()));
                                }
                                break;
                            case 54:
                                if (a6.getContents().length() > 0) {
                                    telefone.setSituacao(1);
                                    telefone.setTelefone(Integer.parseInt(a6.getContents().replaceAll("-", "").trim()));
                                    System.out.println(telefone.toString());
                                    q.insert(telefone);
                                }
                                break;
                            case 55:
                                if (a6.getContents().length() > 0) {

                                    Contato c = (Contato) q.select(contato, q.select(contato));
                                    c.setEmail(a6.getContents());
                                    System.out.println(c.toString());
                                    q.update(c);

                                }
                                break;
                        }
                    }
                }
                q.close();
                return "lista inserida";
            } else {
                System.err.println("NAO ESTÁ NO PADRAO CORRETO");
                return "Erro ao carregar a lista.\n Verifique se o arquivo está em uso ou fora do padrão estabelecido";
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Arquivo não encontrado");
            return "Erro ao carregar a lista.\n Verifique se ele está em uso ou fora do padrão estabelecido";
        } catch (IOException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Arquivo não encontrado");
            return "Erro ao carregar a lista.\n Verifique se ele está em uso ou fora do padrão estabelecido";
        } catch (BiffException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Arquivo não pode ser carregado");
            return "Erro ao carregar a lista.\n Verifique se ele está em uso ou fora do padrão estabelecido";
        } catch (NotIsInsertableEntityException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Arquivo não pode ser carregado");
            return "Erro ao carregar a lista.\n Verifique se ele está em uso ou fora do padrão estabelecido";
        } catch (SQLException ex) {
            Logger.getLogger(ExcellHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Arquivo não pode ser carregado");
            return "Erro ao carregar a lista.\n Verifique se ele está em uso ou fora do padrão estabelecido";
        }
    }
}
