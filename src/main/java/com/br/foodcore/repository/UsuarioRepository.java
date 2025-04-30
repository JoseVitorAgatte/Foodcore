package com.br.foodcore.repository;

import com.br.foodcore.constants.QueryContants;
import com.br.foodcore.model.entity.usuario.Perfil;
import com.br.foodcore.model.entity.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = QueryContants.QUERY_USUARIOS_FILTROS)
    Page<Usuario> buscarUsuarios(Pageable pageable,
                                 @Param("nome") String nome,
                                 @Param("email") String email,
                                 @Param("login") String login,
                                 @Param("logradouro") String logradouro,
                                 @Param("numero") String numero,
                                 @Param("complemento") String complemento,
                                 @Param("bairro") String bairro,
                                 @Param("cidade") String cidade,
                                 @Param("estado") String estado,
                                 @Param("cep") String cep,
                                 @Param("perfil") Perfil perfil);

    Optional<Usuario> findByLoginIgnoreCase(String login);

    Boolean existsByEmail(String email);

    Boolean existsByLogin(String login);
}
