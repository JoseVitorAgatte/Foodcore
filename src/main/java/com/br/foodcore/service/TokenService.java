package com.br.foodcore.service;

import com.br.foodcore.model.entity.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    String geraToken(Usuario usuario);

    String verificaToken(String token);
}
