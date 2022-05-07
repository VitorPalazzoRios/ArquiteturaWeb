package com.example.odsparatodos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ODSDTO {
  private Integer id;
  private String nome,descricao;  
  private Integer projeto;
}
