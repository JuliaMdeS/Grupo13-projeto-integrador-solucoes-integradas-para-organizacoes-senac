package com.hemoclick.api.repository;

import com.hemoclick.api.model.Hemocentro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HemocentroRepository extends JpaRepository<Hemocentro, Long> {
}
