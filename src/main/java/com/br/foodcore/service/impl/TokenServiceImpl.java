package com.br.foodcore.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.foodcore.infra.exception.NegocioException;
import com.br.foodcore.model.entity.usuario.Usuario;
import com.br.foodcore.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    private final String secret = "b2JkdBz!P9lKb2$q8KqLzJQbP29sWzVg";

    @Override
    public String geraToken(Usuario usuario) {

        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT
                    .create()
                    .withIssuer("Foodcore")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(expiraToken(30))
                    .sign(algoritmo);
        }catch (JWTCreationException e){
            throw new NegocioException("Erro ao gerar JWT.");
        }

    }

    @Override
    public String verificaToken(String token){
        DecodedJWT decodedJWT;
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("Foodcore").build();
            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        }catch (JWTVerificationException e){
            throw new NegocioException("Token inválido.");
        }
    }


    public Instant expiraToken(Integer minutos){
        return LocalDateTime.now().plusMinutes(minutos).toInstant(ZoneOffset.of("-03:00"));
    }
}
