package com.hemoclick.api.repository;

import com.hemoclick.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByUsername(String username);
}
