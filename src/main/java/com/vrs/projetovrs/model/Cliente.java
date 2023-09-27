package com.vrs.projetovrs.model;

public class Cliente {
    private Integer id;
    private String nome;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome.toUpperCase().trim();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
