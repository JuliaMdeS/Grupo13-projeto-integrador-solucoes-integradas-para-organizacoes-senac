package com.hemoclick.api.service;

import com.hemoclick.api.dto.auth.AuthDTO;
import com.hemoclick.api.model.Usuario;
import com.hemoclick.api.repository.UserAuthRepository;
import com.hemoclick.api.security.PasswordEncoderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoderService passwordService;

    @Transactional
    public Usuario registerUserAuth(AuthDTO dto) {
        String encodedPassword = passwordService.encodePassword(dto.password());
        var user = new Usuario(dto.username(), encodedPassword);
        return userAuthRepository.save(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findByUsername(username);
    }
}
