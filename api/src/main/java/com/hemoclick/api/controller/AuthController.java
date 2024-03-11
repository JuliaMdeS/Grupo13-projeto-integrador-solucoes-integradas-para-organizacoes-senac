package com.hemoclick.api.controller;

import com.hemoclick.api.dto.auth.AuthDTO;
import com.hemoclick.api.dto.auth.TokenDTO;
import com.hemoclick.api.model.Usuario;
import com.hemoclick.api.security.JWTService;
import com.hemoclick.api.service.UserAuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager manager;
    private final JWTService jwtService;
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    protected ResponseEntity register(@RequestBody @Valid AuthDTO dto, UriComponentsBuilder uriBuilder) {
        var user = userAuthService.registerUserAuth(dto);
        var uri = uriBuilder.path("api/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    protected ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(),dto.password());
        var authentication = manager.authenticate(authenticationToken);
        var jwtToken = jwtService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(jwtToken));
    }
}
