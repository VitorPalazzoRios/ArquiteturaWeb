package com.example.odsparatodos.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_ODS")
public class ODS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(length = 100)
    private String nome,descricao;
    @ManyToOne
    @JoinColumn(name = "Projeto_id")
    private Projeto projeto;
    
    public ODS(int id, String nome, String descricao, Projeto projeto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.projeto = projeto;
    }

    public ODS() {
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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public String toString() {
        return "ODS [descricao=" + descricao + ", id=" + id + ", nome=" + nome + ", projeto=" + projeto + "]";
    }
    
}
