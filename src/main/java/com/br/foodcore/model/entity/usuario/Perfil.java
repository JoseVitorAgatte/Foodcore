package com.br.foodcore.model.entity.usuario;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {

    DONO_RESTAURANTE,
    CLIENTE;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
