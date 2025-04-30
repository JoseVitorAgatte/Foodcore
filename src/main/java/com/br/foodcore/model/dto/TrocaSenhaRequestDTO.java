package com.br.foodcore.model.dto;

import jakarta.validation.constraints.NotBlank;

public record TrocaSenhaRequestDTO(
        @NotBlank(message = "O campo Senha é obrigatório") String novaSenha
) {
}
