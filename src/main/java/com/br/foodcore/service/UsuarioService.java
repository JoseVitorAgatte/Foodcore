package com.br.foodcore.service;

import com.br.foodcore.model.dto.TrocaSenhaRequestDTO;
import com.br.foodcore.model.dto.UsuarioFiltroDTO;
import com.br.foodcore.model.dto.UsuarioRequestDTO;
import com.br.foodcore.model.dto.UsuarioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Page<UsuarioResponseDTO> consultaUsuarios(Pageable pageable, UsuarioFiltroDTO filtro);

    void cadastraDonoRestaurante(UsuarioRequestDTO dto);

    void cadastraCliente(UsuarioRequestDTO dto);

    void editarUsuario(Long id, UsuarioRequestDTO dto);

    void excluirUsuario(Long id);

    void trocarSenha(Long id, TrocaSenhaRequestDTO dto);
}
