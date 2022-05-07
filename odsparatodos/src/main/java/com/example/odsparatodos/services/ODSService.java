package com.example.odsparatodos.services;

import java.util.ArrayList;

import com.example.odsparatodos.dto.DadosODSDTO;
import com.example.odsparatodos.dto.ODSDTO;
import com.example.odsparatodos.entity.ODS;

public interface ODSService {
    ODS salvar(ODSDTO dto);
    DadosODSDTO obterODSPorId(Integer id);
    void remover(Integer id);
    void editar(Integer id, ODSDTO dto);
    ArrayList<DadosODSDTO> obterODSs();
}
