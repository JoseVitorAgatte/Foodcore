package com.br.foodcore.model.entity.usuario;

import com.br.foodcore.model.dto.TrocaSenhaRequestDTO;
import com.br.foodcore.model.dto.UsuarioRequestDTO;
import com.br.foodcore.model.entity.endereco.Endereco;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_USUARIO")
    @SequenceGenerator(name = "SEQ_TB_USUARIO", sequenceName = "SEQ_TB_USUARIO", allocationSize = 1)
    @Column(name = "CD_USUARIO")
    private Long id;

    @Column(name = "NM_USUARIO")
    private String nome;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "DS_LOGIN")
    private String login;

    @Column(name = "DS_SENHA")
    private String senha;

    @Column(name = "DT_ULTIMA_ALTERACAO")
    private LocalDateTime dataUltimaAlteracao;

    @Column(name = "DT_CRIACAO")
    private LocalDateTime dataCriacao;

    @Embedded
    private Endereco endereco;

    @Column(name = "DS_PERFIL")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @Column(name = "FL_ATIVO")
    private Boolean ativo;


    public Usuario(Long id, String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao, Endereco endereco, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.endereco = endereco;
        this.perfil = perfil;
    }

    public Usuario() {
    }

    public Usuario(String nome, String email, String login, String senha, LocalDateTime dataCriacao, Endereco endereco, Perfil perfil, Boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.endereco = endereco;
        this.perfil = perfil;
        this.ativo = ativo;
    }


    public void desativar() {
        this.ativo = false;
    }

    public void trocaSenha(TrocaSenhaRequestDTO dto, PasswordEncoder passwordEncoder) {
        this.senha = passwordEncoder.encode(dto.novaSenha());
        this.dataUltimaAlteracao = LocalDateTime.now();
    }


    public void atualizar(UsuarioRequestDTO dto) {

        Endereco endereco = new Endereco(
                dto.endereco().logradouro(),
                dto.endereco().numero(),
                dto.endereco().complemento(),
                dto.endereco().bairro(),
                dto.endereco().cidade(),
                dto.endereco().estado(),
                dto.endereco().cep()
        );

        this.nome = dto.nome();
        this.email = dto.email();
        this.login = dto.login();
        this.dataUltimaAlteracao = dto.dataUltimaAlteracao();
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(perfil);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


}
