package com.br.foodcore.controller;

import com.br.foodcore.model.dto.LoginRequestDTO;
import com.br.foodcore.model.entity.usuario.Usuario;
import com.br.foodcore.service.TokenService;
import com.br.foodcore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AutenticacaoController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<String> efetuaLogin(@RequestBody LoginRequestDTO request){
        var upat = new UsernamePasswordAuthenticationToken(request.login(), request.senha());
        var authetication = authenticationManager.authenticate(upat);
        String token = tokenService.geraToken((Usuario) authetication.getPrincipal());
        return ResponseEntity.ok(token);
    }
}
