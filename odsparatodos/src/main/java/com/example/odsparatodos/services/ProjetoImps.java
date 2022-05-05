package com.example.odsparatodos.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.odsparatodos.Repository.pessoaRepository;
import com.example.odsparatodos.Repository.projetoRepository;
import com.example.odsparatodos.dto.DadosProjetoDTO;
import com.example.odsparatodos.dto.PessoaDTO;
import com.example.odsparatodos.dto.ProjetoDTO;
import com.example.odsparatodos.entity.Pessoa;
import com.example.odsparatodos.entity.Projeto;
import com.example.odsparatodos.exceptions.RegraNegocioException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoImps implements ProjetoService{
    private final projetoRepository projetoRepository1;
    private final pessoaRepository pessoaRepository1;

    @Override
    @Transactional
    public Projeto salvar(ProjetoDTO dto) {
        Pessoa pessoa = pessoaRepository1.findById(dto.getPessoa())
                .orElseThrow(() -> new RegraNegocioException("Código da pessoa não encontrado."));

        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setPessoa(pessoa);

        return projetoRepository1.save(projeto);
    }

    @Override
    public DadosProjetoDTO obterProjetoPorId(Integer id) {
        return projetoRepository1.findById(id).map(u -> {
            return DadosProjetoDTO
                    .builder()
                    .nome(u.getNome())
                    .descricao(u.getDescricao())
                    .pessoa(PessoaDTO.builder()
                            .id(u.getPessoa().getId())
                            .nome(u.getPessoa().getNome()).build())
                    .build();
        })
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));
    }

    @Override
    @Transactional
    public void remover(Integer id) {
        projetoRepository1.deleteById(id);
    }

    @Override
    @Transactional
    public void editar(Integer id, ProjetoDTO dto) {
        Projeto projeto = projetoRepository1.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        Pessoa pessoa = pessoaRepository1.findById(dto.getPessoa())
                .orElseThrow(() -> new RegraNegocioException("Pessoa não existe"));

        
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setPessoa(pessoa);

        projetoRepository1.save(projeto);

    }

    @Override
    public ArrayList<DadosProjetoDTO> obterProjetos() {
        ArrayList<DadosProjetoDTO> dados = new ArrayList<>();

        List<Projeto> projetos = projetoRepository1.findAll();
        projetos.forEach(u -> {
            dados.add(
                    DadosProjetoDTO
                            .builder()
                            .nome(u.getNome())
                            .descricao(u.getDescricao())
                            .pessoa(PessoaDTO.builder()
                                    .id(u.getPessoa().getId())
                                    .nome(u.getPessoa().getNome()).build())
                            .build());
        });
        return dados;
}




}