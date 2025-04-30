package com.br.foodcore.model.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UsuarioRequestDTO(
        @NotBlank(message = "O campo Nome é obrigatório") String nome,
        @NotBlank(message = "O campo Email é obrigatório")String email,
        @NotBlank(message = "O campo Login é obrigatório") String login,
        @NotBlank(message = "O campo Senha é obrigatório")String senha,
        EnderecoDTO endereco,
        LocalDateTime dataUltimaAlteracao
        ) {
}
