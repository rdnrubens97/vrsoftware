package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ProdutoDao;
import com.vrs.projetovrs.model.Produto;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ProdutoService {
    private ProdutoDao produtoDao;
    public ProdutoService(ProdutoDao dao) {
        this.produtoDao = dao;
    }

    public void cadastrarProduto(Produto produto) {
        try {
            produtoDao.cadastrarProduto(produto);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void editarProduto(Produto produto) {
        try {
            produtoDao.editarProduto(produto);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void excluirProduto(Integer idProduto) {
        try {
            produtoDao.excluirProduto(idProduto);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutos() {
        try {
            return produtoDao.listarProdutos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutosPorNome(String nomeDesejado) {
        try {
            return produtoDao.listarProdutosPorNome(nomeDesejado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutosPorNomeOuCodigo(String nomeOuCodigoDesejado) {
        try {
            return produtoDao.listarProdutosPorNomeOuCodigo(nomeOuCodigoDesejado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public Integer retornaEstoqueAtual(Integer idProduto) {
        try {
            return produtoDao.retornaEstoqueAtual(idProduto);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public Integer retornaEstoqueAtualPorCodigo(String codigo) {
        try {
            return produtoDao.retornaEstoqueAtualPorCodigo(codigo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void adicionarEstoque(Integer idProduto, Integer quantidadeNova) {
        try {
            produtoDao.adicionarEstoque(idProduto, quantidadeNova);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void baixarEstoque(Integer idProduto, Integer quantidadeAtualizada) {
        try {
            produtoDao.baixarEstoque(idProduto, quantidadeAtualizada);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        try {
            return produtoDao.buscarProdutoPorCodigo(codigo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public Integer buscarIdPorCodigo(String codigoPeca) {
        try {
            return produtoDao.buscarIdPorCodigo(codigoPeca);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }


}
