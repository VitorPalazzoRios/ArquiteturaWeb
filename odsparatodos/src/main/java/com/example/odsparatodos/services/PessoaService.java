package com.example.odsparatodos.services;

import java.util.ArrayList;

import com.example.odsparatodos.dto.DadosPessoaDTO;
import com.example.odsparatodos.dto.PessoaDTO;
import com.example.odsparatodos.entity.Pessoa;


public interface PessoaService {
    Pessoa salvar(PessoaDTO dto);
    DadosPessoaDTO obterPessoaPorId(Integer id);
    void remover(Integer id);
    void editar(Integer id, PessoaDTO dto);
    ArrayList<DadosPessoaDTO> obterPessoas();
}