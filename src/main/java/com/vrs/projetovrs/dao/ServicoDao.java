package com.vrs.projetovrs.dao;

import com.vrs.projetovrs.databaseconfig.ConnectionFactory;
import com.vrs.projetovrs.model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDao {
    private Connection conn;

    public ServicoDao() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void salvarServico(Servico servico) throws SQLException {
        String sql = "INSERT INTO tb_servicos (id_venda, descricao, preco) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, servico.getIdVenda());
        stmt.setString(2, servico.getDescricao());
        stmt.setBigDecimal(3, servico.getPreco());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Servico> listarServicosDeUmaVenda(Integer idVenda) throws SQLException {
        String sql = "SELECT * FROM tb_servicos WHERE id_venda = ?";
        List<Servico> servicoList = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idVenda);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Servico servico = new Servico();
            servico.setId(rs.getInt("id"));
            servico.setIdVenda(rs.getInt("id_venda"));
            servico.setDescricao(rs.getString("descricao"));
            servico.setPreco(rs.getBigDecimal("preco"));
            servicoList.add(servico);
        }
        return servicoList;
    }

}
