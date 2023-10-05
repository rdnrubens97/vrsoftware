package com.vrs.projetovrs.model;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private String codigo;
    private String descricao;
    private BigDecimal preco;
    private Integer quandidade;
    private Integer ncm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo.replaceAll("\\s+", " ");
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.replaceAll("\\s+", " ");
    }

    public String getDescricao() {
        return descricao.toUpperCase().replaceAll("\\s+", " ");
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase().replaceAll("\\s+", " ");
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

    public Integer getNcm() {
        return ncm;
    }

    public void setNcm(Integer ncm) {
        this.ncm = ncm;
    }
}
