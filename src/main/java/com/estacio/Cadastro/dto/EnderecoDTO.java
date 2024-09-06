package com.estacio.Cadastro.dto;

import com.estacio.Cadastro.entities.Endereco;
import com.estacio.Cadastro.entities.Pessoa;
import com.estacio.Cadastro.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


public class EnderecoDTO implements Serializable {
    @Autowired
    private PessoaRepository pRepository;

    private static final long serialVersionUID = 1L;


    private Long id;
    private String logradouro;
    private Integer	numero;
    private String cidade;
    private Integer cep;
    private Boolean principal;
    private Long moradorId;

    public EnderecoDTO(){
    }

    public EnderecoDTO(Long id, String logradouro, Integer numero, String cidade, Integer cep, Boolean principal, Long moradorId) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.cep = cep;
        this.moradorId = moradorId;
        setPrincipal(principal);
    }

    public EnderecoDTO(Endereco entity){
        id = entity.getId();
        logradouro = entity.getLogradouro();
        numero = entity.getNumero();
        cidade = entity.getCidade();
        cep = entity.getCep();
        moradorId = entity.getMorador().getId();
        principal = entity.getPrincipal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMorador() {
        return moradorId;
    }

    public Pessoa getMoradorPessoa(Long id) {
        @SuppressWarnings("deprecation")

        Pessoa entity = pRepository.getOne(id);
        return entity;
    }

    public void setMorador(Long morador) {
        this.moradorId = morador;
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

//		List<Endereco> enderecos = this.moradorId.getEnderecos();
//		if (principal) {
//			enderecos.stream().forEach((x) -> x.principal = false);
//			this.principal = true;
//		} else {
//			this.principal = false;
//		}
        this.principal = true;
    }




}
