package com.br.foodcore.model.dto;


import com.br.foodcore.model.entity.usuario.Perfil;

public record UsuarioFiltroDTO(
        String nome,
        String email,
        String login,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        Perfil perfil
) {
}
