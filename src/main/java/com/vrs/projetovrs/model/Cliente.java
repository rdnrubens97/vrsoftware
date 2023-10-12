package com.vrs.projetovrs.model;

import java.util.Objects;

public class Cliente {
    private Integer id;
    private String nome;
    private String documento;
    private String email;
    private String celular;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        if (nome != null) {
            return nome.toUpperCase().replaceAll("\\s+", " ");
        }
        return null;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase().replaceAll("\\s+", " ");
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email.toUpperCase().replaceAll("\\s+", " ");
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase().replaceAll("\\s+", " ");
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep.replaceAll("\\s+", " ");
    }

    public void setCep(String cep) {
        this.cep = cep.toUpperCase().trim().replaceAll("\\s+", " ");
    }

    public String getEndereco() {
        return endereco.toUpperCase().replaceAll("\\s+", " ");
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.toUpperCase().replaceAll("\\s+", " ");
    }

    public String getNumero() {
        return numero.replaceAll("\\s+", " ");
    }

    public void setNumero(String numero) {
        this.numero = numero.replaceAll("\\s+", " ");
    }

    public String getComplemento() {
        return complemento.toUpperCase().replaceAll("\\s+", " ");
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.toUpperCase().replaceAll("\\s+", " ");
    }

    public String getBairro() {
        return bairro.toUpperCase().replaceAll("\\s+", " ");
    }

    public void setBairro(String bairro) {
        this.bairro = bairro.toUpperCase().replaceAll("\\s+", " ");
    }

    public String getCidade() {
        return cidade.toUpperCase().replaceAll("\\s+", " ");
    }

    public void setCidade(String cidade) {
        this.cidade = cidade.toUpperCase().replaceAll("\\s+", " ");
    }

    public String getEstado() {
        return estado.toUpperCase().replaceAll("\\s+", " ");
    }

    public void setEstado(String estado) {
        this.estado = estado.toUpperCase().replaceAll("\\s+", " ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(getId(), cliente.getId()) && getNome().equals(cliente.getNome()) && Objects.equals(getDocumento(), cliente.getDocumento()) && Objects.equals(getEmail(), cliente.getEmail()) && getCelular().equals(cliente.getCelular()) && Objects.equals(getCep(), cliente.getCep()) && Objects.equals(getEndereco(), cliente.getEndereco()) && Objects.equals(getNumero(), cliente.getNumero()) && Objects.equals(getComplemento(), cliente.getComplemento()) && Objects.equals(getBairro(), cliente.getBairro()) && Objects.equals(getCidade(), cliente.getCidade()) && Objects.equals(getEstado(), cliente.getEstado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDocumento(), getEmail(), getCelular(), getCep(), getEndereco(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado());
    }
}
