package com.vrs.projetovrs.dao;

import com.vrs.projetovrs.databaseconfig.ConnectionFactory;
import com.vrs.projetovrs.model.Produto;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    private Connection conn;

    public ProdutoDao() {
        this.conn = new ConnectionFactory().getConnection();
    }

    /**
     * Cadastra um produto no banco de dados.
     *
     * @param produto O produto a ser cadastrado.
     */
    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO tb_produtos (codigo, descricao, preco, quantidade_estoque, ncm) VALUES (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getDescricao());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getQuandidade());
            stmt.setInt(5, produto.getNcm());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e);
        }
    }

    /**
     * Lista todos os produtos no banco de dados.
     *
     * @return Uma lista de todos os produtos cadastrados.
     */
    public List<Produto> listarProdutos() {
        String sql = "SELECT * FROM tb_produtos";
        try {
            List<Produto> lista = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                Integer quantidade = rs.getInt("quantidade_estoque");
                Integer ncm = rs.getInt("ncm");
                Produto produto = new Produto();
                produto.setId(id);
                produto.setCodigo(codigo);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQuandidade(quantidade);
                produto.setNcm(ncm);
                lista.add(produto);
            }

            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    /**
     * Exclui um produto do banco de dados com base no seu ID.
     *
     * @param id O ID do produto a ser excluído.
     */
    public void excluirProduto(Integer id) {
        String sql = "DELETE FROM tb_produtos WHERE id = (?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e);
        }
    }

    /**
     * Edita um produto no banco de dados.
     *
     * @param produto produto a ser editado.
     */
    public void editarProduto(Produto produto) {
        String sql = "UPDATE tb_produtos SET codigo = ?, descricao = ?, preco = ?, quantidade_estoque = ?, ncm = ? WHERE id = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getDescricao());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getQuandidade());
            stmt.setInt(5, produto.getNcm());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar: " + e);
        }
    }

    /**
     * Lista produtos que correspondem a um nome desejado.
     *
     * @param nomeDesejado O nome desejado para filtrar produtos.
     * @return Uma lista de produtos que correspondem ao nome desejado.
     */
    public List<Produto> listarProdutosPorNome(String nomeDesejado) {
        String sql = "SELECT * FROM tb_produtos WHERE descricao LIKE ?";
        try {
            List<Produto> lista = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeDesejado.toUpperCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                Integer quantidade = rs.getInt("quantidade_estoque");
                Integer ncm = rs.getInt("ncm");
                Produto produto = new Produto();
                produto.setId(id);
                produto.setCodigo(codigo);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQuandidade(quantidade);
                produto.setNcm(ncm);
                lista.add(produto);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    /**
     * Lista produtos que correspondem a um nome ou código desejado.
     *
     * @param nomeOuCodigoDesejado O nome ou código desejado para filtrar produtos.
     * @return Uma lista de produtos que correspondem ao nome ou código desejado.
     */
    public List<Produto> listarProdutosPorNomeOuCodigo(String nomeOuCodigoDesejado) {
        String sql = "SELECT * FROM tb_produtos WHERE codigo LIKE ? OR descricao LIKE ?";
        try {
            List<Produto> lista = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeOuCodigoDesejado.toUpperCase() + "%");
            stmt.setString(2, "%" + nomeOuCodigoDesejado.toUpperCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                Integer quantidade = rs.getInt("quantidade_estoque");
                Integer ncm = rs.getInt("ncm");
                Produto produto = new Produto();
                produto.setId(id);
                produto.setCodigo(codigo);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQuandidade(quantidade);
                produto.setNcm(ncm);
                lista.add(produto);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    /**
     * Busca um produto no banco de dados com base no seu ID.
     *
     * @param id O ID do produto a ser buscado.
     * @return O produto correspondente ao ID especificado ou null se não encontrado.
     */
    public Produto buscarProdutoPorId(String id) {
        String sql = "SELECT * FROM tb_produtos WHERE id = ?";
        try {
            Integer idProduto;
            String codigo;
            Integer quantidade;
            Integer ncm;
            String descricao;
            BigDecimal preco;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idProduto = rs.getInt("id");
                codigo = rs.getString("codigo");
                descricao = rs.getString("descricao");
                preco = rs.getBigDecimal("preco");
                quantidade = rs.getInt("quantidade_estoque");
                ncm = rs.getInt("ncm");

                Produto produto = new Produto();
                produto.setId(idProduto);
                produto.setCodigo(codigo);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQuandidade(quantidade);
                produto.setNcm(ncm);

                return produto;
            }
            else {
                JOptionPane.showMessageDialog(null, "Produto com identificador " + id + " não encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);

        }
        return null;
    }

    /**
     * Atualiza a quantidade em estoque de um produto no banco de dados.
     *
     * @param id             O ID do produto.
     * @param quantidadeNova A nova quantidade em estoque.
     */
    public void baixarEstoque(Integer id, Integer quantidadeNova) {
        String sql = "UPDATE tb_produtos SET quantidade_estoque = ?  WHERE id = ?";
        try {
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setInt(1, quantidadeNova);
             stmt.setInt(2, id);
             stmt.executeUpdate();
             stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    /**
     * Adiciona a quantidade em estoque de um produto no banco de dados.
     *
     * @param id             O ID do produto.
     * @param quantidadeNova A nova quantidade em estoque.
     */
    public void adicionarEstoque(Integer id, Integer quantidadeNova) {
        String sql = "UPDATE tb_produtos SET quantidade_estoque = ?  WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidadeNova);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    /**
     * Retorna a quantidade atual em estoque de um produto com base no seu ID.
     *
     * @param id O ID do produto.
     * @return A quantidade atual em estoque do produto ou null se não encontrado.
     */
    public Integer retornaEstoqueAtual(Integer id) {
        String sql = "SELECT quantidade_estoque FROM tb_produtos WHERE id = ?";
        Integer quantidadeEstoque = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                quantidadeEstoque = rs.getInt("quantidade_estoque");
            }

            return quantidadeEstoque;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
        return null;
    }


    /**
     * Retorna a quantidade atual em estoque de um produto com base no seu codigo.
     *
     * @param codigo O codigo do produto.
     * @return A quantidade atual em estoque do produto ou null se não encontrado.
     */
    public Integer retornaEstoqueAtualPorCodigo(String codigo) {
        String sql = "SELECT quantidade_estoque FROM tb_produtos WHERE codigo = ?";
        Integer quantidadeEstoque = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                quantidadeEstoque = rs.getInt("quantidade_estoque");
            }

            return quantidadeEstoque;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
        return null;
    }

    /**
     * Busca um produto no banco de dados com base no seu código.
     *
     * @param codigoProcurado O código do produto a ser buscado.
     * @return O produto correspondente ao código especificado ou null se não encontrado.
     */
    public Produto buscarProdutoPorCodigo(String codigoProcurado) {
        String sql = "SELECT * FROM tb_produtos WHERE codigo = ?";
        try {
            Integer idProduto;
            String codigo;
            Integer quantidade;
            Integer ncm;
            String descricao;
            BigDecimal preco;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigoProcurado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idProduto = rs.getInt("id");
                codigo = rs.getString("codigo");
                descricao = rs.getString("descricao");
                preco = rs.getBigDecimal("preco");
                quantidade = rs.getInt("quantidade_estoque");
                ncm = rs.getInt("ncm");

                Produto produto = new Produto();
                produto.setId(idProduto);
                produto.setCodigo(codigo);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQuandidade(quantidade);
                produto.setNcm(ncm);

                return produto;
            }
            else {
                JOptionPane.showMessageDialog(null, "Produto com código " + codigoProcurado + " não encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);

        }
        return null;
    }

    public Integer buscarIdPorCodigo(String codigo) {
        String sql = "SELECT id FROM tb_produtos WHERE codigo = ?";
        Integer id = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            return null;
        }
    }



}
