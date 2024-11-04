package com.challenge.cmg.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.challenge.cmg.model.Client;
import com.challenge.cmg.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {
    private final ClientRepository clientRepository;
    private Algorithm algorithm;

    public JwtTokenService(ClientRepository clientRepository, @Value("${jwt.secret}") String secret) {
        this.clientRepository = clientRepository;
        algorithm = Algorithm.HMAC256(secret);
    }

    public Token create(Client client) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

        String token = JWT.create()
                .withIssuer("TPC")
                .withSubject(client.getEmail())
                .withClaim("role", "admin")
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token);
    }

    public Client getUserFromToken(String token) {
        var email = JWT.require(algorithm)
                .withIssuer("TPC")
                .build()
                .verify(token)
                .getSubject();

        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
