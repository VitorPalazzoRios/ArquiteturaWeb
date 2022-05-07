package com.example.odsparatodos.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.odsparatodos.Repository.ODSRepository;
import com.example.odsparatodos.Repository.projetoRepository;
import com.example.odsparatodos.dto.DadosODSDTO;
import com.example.odsparatodos.dto.ODSDTO;
import com.example.odsparatodos.dto.ProjetoDTO;
import com.example.odsparatodos.entity.ODS;
import com.example.odsparatodos.entity.Projeto;
import com.example.odsparatodos.exceptions.RegraNegocioException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ODSImps implements ODSService{
    private final ODSRepository ODSRepository;
    private final projetoRepository projetoRepository1;

    @Override
    @Transactional
    public ODS salvar(ODSDTO dto) {
        Projeto projeto = projetoRepository1.findById(dto.getProjeto())
                .orElseThrow(() -> new RegraNegocioException("Código da ODS não encontrado."));

        ODS ODS = new ODS();
        ODS.setNome(dto.getNome());
        ODS.setDescricao(dto.getDescricao());
        ODS.setProjeto(projeto);

        return ODSRepository.save(ODS);
    }

    @Override
    public DadosODSDTO obterODSPorId(Integer id) {
        return ODSRepository.findById(id).map(u -> {
            return DadosODSDTO
                    .builder()
                    .nome(u.getNome())
                    .descricao(u.getDescricao())
                    .projeto(ProjetoDTO.builder()
                            .id(u.getProjeto().getId())
                            .nome(u.getProjeto().getNome()).build())
                    .build();
        })
                .orElseThrow(() -> new RegraNegocioException("ODS não encontrado"));
    }

    @Override
    @Transactional
    public void remover(Integer id) {
        ODSRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editar(Integer id, ODSDTO dto) {
        ODS ODS = ODSRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        Projeto projeto = projetoRepository1.findById(dto.getProjeto())
                .orElseThrow(() -> new RegraNegocioException("ODS não existe"));

        
        ODS.setNome(dto.getNome());
        ODS.setDescricao(dto.getDescricao());
        ODS.setProjeto(projeto);

        ODSRepository.save(ODS);

    }

    @Override
    public ArrayList<DadosODSDTO> obterODSs() {
        ArrayList<DadosODSDTO> dados = new ArrayList<>();

     List<ODS> ODSs = ODSRepository.findAll();
        ODSs.forEach(u -> {
            dados.add(
                    DadosODSDTO
                            .builder()
                            .nome(u.getNome())
                            .descricao(u.getDescricao())         
                            .projeto(ProjetoDTO.builder()
                                    .id(u.getProjeto().getId())
                                    .nome(u.getProjeto().getNome()).build())
                            .build());
        });
        return dados;
}
}
