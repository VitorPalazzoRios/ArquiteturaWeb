package com.example.odsparatodos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_Projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(length = 100)
    private String nome,descricao;
    @OneToMany
    @JoinColumn(name = "Pessoa_id")
    private Pessoa pessoa;
    
    
    public Projeto(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    public Projeto() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", nome: " + nome + ", Pessoa: " + 
        (pessoa != null ? pessoa.getNome() : "");  
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
