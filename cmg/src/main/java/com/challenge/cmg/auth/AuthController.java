package com.challenge.cmg.auth;

import com.challenge.cmg.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public AuthController(ClientRepository clientRepository, PasswordEncoder passwordEncoder, JwtTokenService tokenService) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) {

        var client = clientRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new RuntimeException("Access Denied"));

        if (!passwordEncoder.matches(credentials.password(), client.getPassword()))
            throw new RuntimeException("Access Denied");

        return tokenService.create(client);
    }

}
