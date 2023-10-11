package com.vrs.projetovrs.builder;

import com.vrs.projetovrs.model.Servico;

import java.math.BigDecimal;

public class ServicoBuilder {
    private Servico servico;

    private ServicoBuilder() {
    }

    public static ServicoBuilder umServico() {
        ServicoBuilder builder = new ServicoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(ServicoBuilder builder) {
        builder.servico = new Servico();
        Servico elemento = builder.servico;
        elemento.setId(0);
        elemento.setIdVenda(0);
        elemento.setDescricao("");
        elemento.setPreco(null);
    }

    public ServicoBuilder comId(Integer param) {
        servico.setId(param);
        return this;
    }

    public ServicoBuilder comIdVenda(Integer param) {
        servico.setIdVenda(param);
        return this;
    }

    public ServicoBuilder comDescricao(String param) {
        servico.setDescricao(param);
        return this;
    }

    public ServicoBuilder comPreco(BigDecimal param) {
        servico.setPreco(param);
        return this;
    }

    public Servico agora() {
        return servico;
    }
}

