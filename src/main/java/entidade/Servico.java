/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import persistencia.ConnectionFactory;

/**
 *
 * @author Edilson Jr
 */
public class Servico {

    private int codServico;

    public int getCodServico() {
        return codServico;
    }
    //public void setCodServico(int codServico) {this.codServico = codServico;}

    private int codUsuario;

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    private LocalDateTime criacaoServico;

    public LocalDateTime getCriacaoServico() {
        return criacaoServico;
    }

    public void setCriacaoServico(LocalDateTime criacaoServico) {
        this.criacaoServico = criacaoServico;
    }

    private LocalDateTime fimServico;

    public LocalDateTime getFimServico() {
        return fimServico;
    }

    public void setFimServico(LocalDateTime fimServico) {
        this.fimServico = fimServico;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private int intervaloLigacoes;

    public int getIntervaloLigacoes() {
        return intervaloLigacoes;
    }

    public void setIntervaloLigacoes(int intervaloLigacoes) {
        this.intervaloLigacoes = intervaloLigacoes;
    }

    /**
     * Contrutores.
     *
     * @param criacaoServico
     * @param descricao
     * @param codUsuario
     */
    public Servico(LocalDateTime criacaoServico, String descricao, int codUsuario) {
        this.criacaoServico = criacaoServico;
        this.descricao = descricao;
        this.codUsuario = codUsuario;
    }

    public Servico(int codServico) {
        this.codServico = codServico;
        init();
    }

    private void init() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Servico WHERE cod_servico = ?;";
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, getCodServico());
            rs = stmt.executeQuery();
            while (rs.next()) {
                setCriacaoServico(LocalDateTime.parse(rs.getString(2).substring(0, rs.getString(2).length() - 2), dt));
                setDescricao(rs.getString(3));
                setFimServico(LocalDateTime.parse(rs.getString(4), dt));
                setIntervaloLigacoes(rs.getInt(5));
                setCodUsuario(rs.getInt(6));
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro init()");
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "ServicoDao{" + "codServico=" + getCodServico() + ", \ncodUsuario=" + getCodUsuario() + ", \ncriacaoServico=" + getCriacaoServico() + ", \nfimServico=" + fimServico + ", \ndescricao=" + descricao + ", \nintervaloLigacoes=" + intervaloLigacoes + '}';
    }
}
