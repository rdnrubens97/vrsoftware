package com.vrs.projetovrs.model;

import com.vrs.projetovrs.exception.ValidationException;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private String descricao;
    private BigDecimal preco;
    private Integer quandidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao.toUpperCase().trim();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuandidade() {
        return quandidade;
    }

    public void setQuandidade(Integer quandidade) {
        this.quandidade = quandidade;
    }
}
