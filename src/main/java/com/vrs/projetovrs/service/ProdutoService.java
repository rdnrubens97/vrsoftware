package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ProdutoDao;
import com.vrs.projetovrs.model.Produto;

import java.util.List;

public class ProdutoService {
    private ProdutoDao produtoDao;
    public ProdutoService(ProdutoDao dao) {
        this.produtoDao = dao;
    }

    public void cadastrarProduto(Produto produto) {
        produtoDao.cadastrarProduto(produto);
    }

    public void editarProduto(Produto produto) {
        produtoDao.editarProduto(produto);
    }

    public void excluirProduto(Integer idProduto) {
        produtoDao.excluirProduto(idProduto);
    }

    public List<Produto> listarProdutos() {
        return produtoDao.listarProdutos();
    }

    public List<Produto> listarProdutosPorNome(String nomeDesejado) {
        return produtoDao.listarProdutosPorNome(nomeDesejado);
    }

    public List<Produto> listarProdutosPorNomeOuCodigo(String nomeOuCodigoDesejado) {
        return produtoDao.listarProdutosPorNomeOuCodigo(nomeOuCodigoDesejado);
    }

    public Integer retornaEstoqueAtual(Integer idProduto) {
        return produtoDao.retornaEstoqueAtual(idProduto);
    }

    public Integer retornaEstoqueAtualPorCodigo(String codigo) {
        return produtoDao.retornaEstoqueAtualPorCodigo(codigo);
    }

    public void adicionarEstoque(Integer idProduto, Integer quantidadeNova) {
        produtoDao.adicionarEstoque(idProduto, quantidadeNova);
    }

    public void baixarEstoque(Integer idProduto, Integer quantidadeAtualizada) {
        produtoDao.baixarEstoque(idProduto, quantidadeAtualizada);
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        return produtoDao.buscarProdutoPorCodigo(codigo);
    }

    public Integer buscarIdPorCodigo(String codigoPeca) {
        return produtoDao.buscarIdPorCodigo(codigoPeca);
    }


}
