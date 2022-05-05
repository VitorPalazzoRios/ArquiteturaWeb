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
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(length = 100)
    private String nome,email,senha;
    @OneToMany
    @JoinColumn(name = "pessoaFisica_id")
    private Projeto projeto;
    
    
    public Pessoa(int id, String nome, String email,String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public Pessoa(){
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public String toString() {
        return "Id: " + id + ", nome: " + nome + ", Projeto: " + 
        (projeto != null ? projeto.getNome() : "");
    }
    public Projeto getProjeto() {
        return projeto;
    }
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
