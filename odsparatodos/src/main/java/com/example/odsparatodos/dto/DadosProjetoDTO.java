package com.example.odsparatodos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosProjetoDTO {
    private String email;
    private String nome,descricao;
    private PessoaDTO pessoa;
}
