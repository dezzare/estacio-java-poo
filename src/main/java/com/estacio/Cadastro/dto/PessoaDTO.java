package com.estacio.Cadastro.dto;

import com.estacio.Cadastro.entities.Pessoa;

import java.io.Serializable;

public class PessoaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String dataDeNascimento;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String nome, String dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public PessoaDTO(Pessoa entity) {
        id = entity.getId();
        nome = entity.getNome();
        dataDeNascimento = entity.getDataDeNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
