package com.estacio.Cadastro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataDeNascimento;


    @OneToMany(mappedBy = "morador")
    private List<Endereco> enderecos = new ArrayList<>();

    public Pessoa(){
    }

    public Pessoa(Long id, String nome, String dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;

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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

//	@Override
//	public int hashCode() {
//		return Objects.hash(id, nome);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Pessoa other = (Pessoa) obj;
//		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
//	}




}