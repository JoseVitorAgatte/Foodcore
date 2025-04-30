package com.br.foodcore.model.dto;

import com.br.foodcore.model.entity.usuario.Perfil;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        EnderecoDTO endereco,
        Perfil perfil
) {
}
