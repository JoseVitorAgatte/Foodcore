package com.br.foodcore.model.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank(message = "O campo Nome do Logradouro é obrigatório")String logradouro,
        @NotBlank(message = "O campo Número é obrigatório")String numero,
        String complemento,
        @NotBlank(message = "O campo Bairro é obrigatório")String bairro,
        @NotBlank(message = "O campo Cidade é obrigatório")String cidade,
        @NotBlank(message = "O campo Estado é obrigatório")String estado,
        @NotBlank(message = "O campo CEP é obrigatório")String cep
) {
}
