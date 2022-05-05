package com.example.odsparatodos.services;

import java.util.ArrayList;

import com.example.odsparatodos.dto.DadosProjetoDTO;
import com.example.odsparatodos.dto.ProjetoDTO;
import com.example.odsparatodos.entity.Projeto;

public interface ProjetoService {
    Projeto salvar(ProjetoDTO dto);
    DadosProjetoDTO obterProjetoPorId(Integer id);
    void remover(Integer id);
    void editar(Integer id, ProjetoDTO dto);
    ArrayList<DadosProjetoDTO> obterProjetos();
}
