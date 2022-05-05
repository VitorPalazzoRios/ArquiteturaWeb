package com.example.odsparatodos.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import com.example.odsparatodos.Repository.pessoaRepository;
import com.example.odsparatodos.Repository.projetoRepository;
import com.example.odsparatodos.dto.DadosPessoaDTO;
import com.example.odsparatodos.dto.PessoaDTO;
import com.example.odsparatodos.dto.ProjetoDTO;
import com.example.odsparatodos.entity.Pessoa;
import com.example.odsparatodos.entity.Projeto;
import com.example.odsparatodos.exceptions.RegraNegocioException;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaImps implements PessoaService{
    private final pessoaRepository pessoaRepository1;
    private final projetoRepository projetoRepository1;

    @Override
    @Transactional
    public Pessoa salvar(PessoaDTO dto) {
        Projeto projeto = projetoRepository1.findById(dto.getProjeto())
                .orElseThrow(() -> new RegraNegocioException("Código da pessoa não encontrado."));

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setEmail(dto.getEmail());
        pessoa.setProjeto(projeto);

        return pessoaRepository1.save(pessoa);
    }

    @Override
    public DadosPessoaDTO obterPessoaPorId(Integer id) {
        return pessoaRepository1.findById(id).map(u -> {
            return DadosPessoaDTO
                    .builder()
                    .nome(u.getNome())
                    .email(u.getEmail())
                    .senha(u.getSenha())
                    .projeto(ProjetoDTO.builder()
                            .id(u.getProjeto().getId())
                            .nome(u.getProjeto().getNome()).build())
                    .build();
        })
                .orElseThrow(() -> new RegraNegocioException("Pessoa não encontrado"));
    }

    @Override
    @Transactional
    public void remover(Integer id) {
        pessoaRepository1.deleteById(id);
    }

    @Override
    @Transactional
    public void editar(Integer id, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository1.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        Projeto projeto = projetoRepository1.findById(dto.getProjeto())
                .orElseThrow(() -> new RegraNegocioException("Pessoa não existe"));

        
        pessoa.setNome(dto.getNome());
        pessoa.setEmail(dto.getEmail());
        pessoa.setSenha(dto.getSenha());
        pessoa.setProjeto(projeto);

        pessoaRepository1.save(pessoa);

    }

    @Override
    public ArrayList<DadosPessoaDTO> obterPessoas() {
        ArrayList<DadosPessoaDTO> dados = new ArrayList<>();

        java.util.List<Pessoa> pessoas = pessoaRepository1.findAll();
        pessoas.forEach(u -> {
            dados.add(
                    DadosPessoaDTO
                            .builder()
                            .nome(u.getNome())
                            .email(u.getEmail())
                            .senha(u.getSenha())
                            .projeto(ProjetoDTO.builder()
                                    .id(u.getProjeto().getId())
                                    .nome(u.getProjeto().getNome()).build())
                            .build());
        });
        return dados;
}

}
