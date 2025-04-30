package com.br.foodcore.constants;

public class QueryContants {


    public static final String QUERY_USUARIOS_FILTROS = "SELECT u FROM Usuario u " +
            "WHERE " +
            "(:nome IS NULL OR LOWER(FUNCTION('REMOVE_ACENTOS', u.nome)) LIKE LOWER(FUNCTION('REMOVE_ACENTOS', '%' || :nome || '%'))) "+
            "AND (:email IS NULL OR u.email LIKE '%' || :email || '%') " +
            "AND (:login IS NULL OR u.login LIKE '%' || :login || '%') " +
            "AND (:logradouro IS NULL OR LOWER(FUNCTION('REMOVE_ACENTOS', u.endereco.logradouro)) LIKE LOWER(FUNCTION('REMOVE_ACENTOS', '%' || :logradouro || '%'))) " +
            "AND (:numero IS NULL OR u.endereco.numero ILIKE '%' || :numero || '%') " +
            "AND (:complemento IS NULL OR LOWER(FUNCTION('REMOVE_ACENTOS', u.endereco.complemento)) LIKE LOWER(FUNCTION('REMOVE_ACENTOS','%' || :complemento || '%'))) " +
            "AND (:bairro IS NULL OR LOWER(FUNCTION('REMOVE_ACENTOS', u.endereco.bairro)) LIKE LOWER(FUNCTION('REMOVE_ACENTOS','%' || :bairro || '%'))) " +
            "AND (:cidade IS NULL OR LOWER(FUNCTION('REMOVE_ACENTOS', u.endereco.cidade)) LIKE LOWER(FUNCTION('REMOVE_ACENTOS','%' || :cidade || '%'))) " +
            "AND (:estado IS NULL OR u.endereco.estado LIKE '%' || :estado || '%') " +
            "AND (:cep IS NULL OR u.endereco.cep LIKE '%' || :cep || '%') " +
            "AND (:perfil IS NULL OR u.perfil = :perfil)"
            ;


}
