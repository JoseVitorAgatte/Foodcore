package com.br.foodcore.controller;

import com.br.foodcore.model.dto.TrocaSenhaRequestDTO;
import com.br.foodcore.model.dto.UsuarioFiltroDTO;
import com.br.foodcore.model.dto.UsuarioRequestDTO;
import com.br.foodcore.model.dto.UsuarioResponseDTO;
import com.br.foodcore.service.UsuarioService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> consultaUsuarios(@ParameterObject Pageable pageable, @ParameterObject UsuarioFiltroDTO request){
        return ResponseEntity.ok(service.consultaUsuarios(pageable, request));
    }

    @PostMapping("/dono-restaurante")
    public ResponseEntity<Void> cadastrarDonoRestaurante(@RequestBody UsuarioRequestDTO request){
        service.cadastraDonoRestaurante(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cliente")
    public ResponseEntity<Void> cadastrarDonoRestauranta(@RequestBody UsuarioRequestDTO request){
        service.cadastraDonoRestaurante(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO request){
        service.editarUsuario(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluiUsuario(@PathVariable Long id){
        service.excluirUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/troca-senha/{id}")
    public ResponseEntity<Void> trocaSenha(@PathVariable Long id, @RequestBody TrocaSenhaRequestDTO request){
        service.trocarSenha(id, request);
        return ResponseEntity.ok().build();
    }
}
