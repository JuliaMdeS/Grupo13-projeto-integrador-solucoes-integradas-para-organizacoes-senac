package com.hemoclick.api.repository;

import com.hemoclick.api.model.Doador;
import com.hemoclick.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {
    boolean existsByUsuario(Usuario usuario);
}
