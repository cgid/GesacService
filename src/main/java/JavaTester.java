
import entidade.Servico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import persistencia.ServicoDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author murilo
 */
public class JavaTester {
    public static void main(String[] args) {
        LocalDateTime dt =   LocalDateTime.now();
        Servico sd = new Servico(1);
        System.out.println(sd.toString());
    }
}
