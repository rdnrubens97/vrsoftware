package com.vrs.projetovrs.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Servico {
    private Integer id;
    private Integer idVenda;
    private String descricao;
    private BigDecimal preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public String getDescricao() {
        return descricao;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servico servico)) return false;
        return getIdVenda().equals(servico.getIdVenda()) && getDescricao().equals(servico.getDescricao()) && getPreco().equals(servico.getPreco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVenda(), getDescricao(), getPreco());
    }
}
