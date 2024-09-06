package com.estacio.Cadastro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private Integer	numero;
    private String cidade;
    private Integer cep;
    private Boolean principal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Pessoa morador;

    public Endereco(){
    }

    public Endereco(Long id, String logradouro, Integer numero, String cidade, Integer cep, Boolean principal, Pessoa morador) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.cep = cep;
        this.morador = morador;
        setPrincipal(principal);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getMorador() {
        return morador;
    }

    public void setMorador(Pessoa morador) {
        this.morador = morador;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Boolean getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(Boolean principal) {
        List<Endereco> enderecos = this.morador.getEnderecos();
        if (principal) {
            enderecos.stream().forEach((x) -> x.principal = false);
            this.principal = true;
        } else {
            this.principal = false;
        }
    }
}