/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Murilo
 */
public class ServicoDao extends Servico{
    public ServicoDao(LocalDateTime criacaoServico, String descricao, int codUsuario) {
        super(criacaoServico, descricao, codUsuario);
    }

    public ServicoDao() {
        super(0);
    }
    
    public void adicionarServico()  {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmt = null;
        String tst = "insert into Servico(dt_criacao_servico, descricao, Usuario_cod_usuario) values(?, ?, ?);";
        try {
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(tst);

            pstmt.setString(1, getCriacaoServico().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pstmt.setString(2, getDescricao());
            pstmt.setInt(3, getCodUsuario());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir um servico");
            System.out.println(e);
        }
        finally {                
            try {
                if(pstmt != null)
                    pstmt.close();
                conn.setAutoCommit(true);
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar declaracao.");
                System.out.println(e);
            }
        }
    }
    
    public void deletarServico() {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "DELETE FROM Servico WHERE cod_servico = ?;";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, getCodServico());
            pstmt.execute();
            if(conn.equals(null))
                conn.close();
            if(pstmt.equals(null))
                pstmt.close();
        } catch (SQLException e) {
            System.out.println("Nao foi possivel deletar servico: " + toString());
            System.out.println(e);
        }

    }
    
    public void listaServico() { 
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Usuario";
            rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
            System.out.println("cod_usuario: " + rs.getInt(1));
            System.out.println("nome: " + rs.getString(2));
            System.out.println("login: " + rs.getString(3));
            System.out.println("senha: " + rs.getString(4));
            System.out.println("cod_perfil: " + rs.getInt(5));
            System.out.println("");
        }
        } catch (SQLException e) {
            System.out.println("Errosdifubsidubfius");
            System.out.println(e);
        }
        finally{
            try {
                stmt.close();
                rs.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
