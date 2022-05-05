package com.example.odsparatodos.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosPessoaDTO {
    private Integer id;
    private String nome,email,senha;
    private ProjetoDTO projeto;
}
