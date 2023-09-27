package com.vrs.projetovrs.dao;

import com.vrs.projetovrs.databaseconfig.ConnectionFactory;
import com.vrs.projetovrs.model.ItemVenda;
import com.vrs.projetovrs.model.Produto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemVendaDao {
    private Connection conn;

    public ItemVendaDao() {
        this.conn = new ConnectionFactory().getConnection();
    }

    /**
     * Cadastra um item de venda no banco de dados.
     * @param itemVenda O item de venda a ser cadastrado.
     */
    public void cadastrarItem(ItemVenda itemVenda) {
        String sql = "INSERT INTO tb_itensvendas (id_venda, id_produto, quantidade, preco, valor_total) VALUES (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, itemVenda.getVenda().getId());
            stmt.setInt(2, itemVenda.getProduto().getId());
            stmt.setInt(3, itemVenda.getQuantidade());
            stmt.setBigDecimal(4, itemVenda.getProduto().getPreco());
            stmt.setBigDecimal(5, itemVenda.getValorTotal());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e);
        }
    }

    /**
     * Lista os itens de uma venda específica a partir do seu ID.
     *
     * @param idVenda O ID da venda da qual se deseja listar os itens.
     * @return Uma lista de itens associados à venda especificada.
     */
    public List<ItemVenda> listaItensDeUmaVenda(Integer idVenda) {
        String sql = """
                SELECT i.id, p.descricao, i.quantidade, p.preco, i.valor_total
                FROM tb_itensvendas AS i
                INNER JOIN tb_produtos AS p ON (i.id_produto = p.id)
                WHERE i.id_venda = ? ;                  
                """;
        try {
            List<ItemVenda> lista = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVenda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemVenda itemVenda = new ItemVenda();
                Produto produto = new Produto();

                itemVenda.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                itemVenda.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getBigDecimal("preco"));
                itemVenda.setValorTotal(rs.getBigDecimal("valor_total"));
                itemVenda.setProduto(produto);

                lista.add(itemVenda);
            }

            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return Collections.emptyList();
        }
    }

}
