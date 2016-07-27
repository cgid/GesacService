
import bancodedados.Queries;
import bancodedados.SimpleQueries;
import java.util.ArrayList;
import java.util.List;
import tabelas.GesacTab;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author murilo
 */
public class testeloucura {

    public static void main(String[] args) {
      
        List<GesacTab> gt = new ArrayList<>();
        Queries q = new SimpleQueries();
  
        for (int i = 0; i < 10; i++) {
            gt.add(new GesacTab(i, "teste".concat(String.valueOf(i))));
            q.insert(gt.get(i));
        }
    }
}
