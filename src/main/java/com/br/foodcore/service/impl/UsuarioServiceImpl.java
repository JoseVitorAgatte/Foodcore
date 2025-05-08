package com.br.foodcore.service.impl;

import com.br.foodcore.infra.exception.NegocioException;
import com.br.foodcore.model.dto.*;
import com.br.foodcore.model.entity.endereco.Endereco;
import com.br.foodcore.model.entity.usuario.Perfil;
import com.br.foodcore.model.entity.usuario.Usuario;
import com.br.foodcore.repository.UsuarioRepository;
import com.br.foodcore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLoginIgnoreCase(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Override
    public Page<UsuarioResponseDTO> consultaUsuarios(Pageable pageable, UsuarioFiltroDTO filtro) {
        return repository.buscarUsuarios(pageable,
                filtro.nome(),
                filtro.email(),
                filtro.login(),
                filtro.logradouro(),
                filtro.numero(),
                filtro.complemento(),
                filtro.bairro(),
                filtro.cidade(),
                filtro.estado(),
                filtro.cep(),
                filtro.perfil()).map(this::toUsuarioDto);
    }


    @Override
    public void cadastraDonoRestaurante(UsuarioRequestDTO dto) {
        cadastraUsuario(dto, Perfil.DONO_RESTAURANTE);
    }

    @Override
    public void cadastraCliente(UsuarioRequestDTO dto) {
        cadastraUsuario(dto, Perfil.CLIENTE);
    }

    @Override
    public void editarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NegocioException("Usuario não encontrado."));
        verificaEmailDisponivel(dto.email());
        verificaLoginDisponivel(dto.login());

        usuario.atualizar(dto);
        repository.save(usuario);
    }

    @Override
    public void excluirUsuario(Long id) {
        Usuario usuario = obterPorId(id);
        usuario.desativar();
        repository.save(usuario);
    }

    @Override
    public void trocarSenha(Long id, TrocaSenhaRequestDTO dto) {
        Usuario usuario = obterPorId(id);
        usuario.trocaSenha(dto, passwordEncoder);
        repository.save(usuario);
    }

    private Usuario obterPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NegocioException("Usuario não encontrado."));
    }

    private void cadastraUsuario(UsuarioRequestDTO dto, Perfil perfil) {
        verificaEmailDisponivel(dto.email());

        verificaLoginDisponivel(dto.login());

        Endereco endereco = new Endereco(
                dto.endereco().logradouro(),
                dto.endereco().numero(),
                dto.endereco().complemento(),
                dto.endereco().bairro(),
                dto.endereco().cidade(),
                dto.endereco().estado(),
                dto.endereco().cep()
        );

        Usuario usuario = new Usuario(
                dto.nome(),
                dto.email(),
                dto.login(),
                passwordEncoder.encode(dto.senha()),
                LocalDateTime.now(),
                endereco,
                perfil,
                true);

        repository.save(usuario);
    }

    private void verificaEmailDisponivel(String email) {
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new NegocioException("E-mail já em uso.");
        }
    }

    private void verificaLoginDisponivel(String login) {
        if (repository.existsByLoginIgnoreCase(login)) {
            throw new NegocioException("Nome de Usuário já em uso.");
        }
    }


    private UsuarioResponseDTO toUsuarioDto(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                toEnderecoDto(usuario.getEndereco()),
                usuario.getPerfil()
        );
    }

    private EnderecoDTO toEnderecoDto(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

}
